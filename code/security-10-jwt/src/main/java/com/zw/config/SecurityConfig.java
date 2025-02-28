package com.zw.config;

import com.zw.handler.MyAuthenticationFailureHandler;
import com.zw.handler.MyAuthenticationSuccessHandler;
import com.zw.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 配置跨域
    public CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // 跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*")); // 允许任何来源 也可以指定只允许http://localhost:8888访问
        corsConfiguration.setAllowedMethods(Arrays.asList("*")); // 允许任何请求方式
        corsConfiguration.setAllowedHeaders(Arrays.asList("*")); // 允许任何的请求头

        // 注册跨域配置
        // 以下的/**表示任何路径，即后端的所有路径都使用corsConfiguration配置
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CorsConfigurationSource configurationSource) throws Exception {
        return httpSecurity
                .formLogin(t -> {
                    //
                    t.loginProcessingUrl("/login")
                            // 页面和后端服务不在同一个服务器中，无法跳转页面
                            // 后端只负责相应数据，前端负责页面跳转
                            .successHandler(myAuthenticationSuccessHandler) // 登录成功之后执行Handler
                            // 登录失败执行Handler，否则会给前端相应一段html，这段html是默认的登录页
                            .failureHandler(myAuthenticationFailureHandler);
                })
                .logout(t -> {
                    t.logoutUrl("/logout") // 退出请求提交到那个地址
                            .logoutSuccessHandler(myLogoutSuccessHandler);// 退出成功后执行该handler
                })
                .authorizeHttpRequests(t -> {
                    t.anyRequest().authenticated();
                })
                // 禁用csrf跨站请求伪造，禁用之后就不安全了，后续可以加入jwt防御
                .csrf(t -> {
                    t.disable();
                })
                // 允许前端跨域访问
                .cors(t -> {
                    t.configurationSource(configurationSource); // 使用自定义的跨域配置
                })
                // 禁用session
                .sessionManagement(t -> {
                    // 创建session采用无状态策略，即不创建session
                    t.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .build();
    }
}
