package com.zw;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Security04CaptchaLoginApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test01(){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "aaa111";
		String encodedPassword = passwordEncoder.encode(password);// 加密方法
		System.out.println(encodedPassword);
		// 匹配方法, 第一个参数是明文密码，第二个参数是密文密码
		boolean match = passwordEncoder.matches(password, encodedPassword);
		System.out.println(match);

		// 对同一个密码再次加密和匹配
		encodedPassword = passwordEncoder.encode(password);
		match = passwordEncoder.matches(password, encodedPassword);
		System.out.println(encodedPassword);
		System.out.println(match);

		encodedPassword = passwordEncoder.encode(password);
		match = passwordEncoder.matches(password, encodedPassword);
		System.out.println(encodedPassword);
		System.out.println(match);
	}
}
