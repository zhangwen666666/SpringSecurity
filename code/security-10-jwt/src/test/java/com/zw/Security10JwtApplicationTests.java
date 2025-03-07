package com.zw;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.zw.entity.User;
import com.zw.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class Security10JwtApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(1020);
        user.setLoginAct("cat");
        user.setName("小猫");
        String userJson = JSONUtil.toJsonStr(user);
        String token = JwtUtil.createToken(userJson);
        System.out.println(token);

        System.out.println(JwtUtil.verifyToken(token));
        System.out.println(JwtUtil.verifyToken(token + 111));

        System.out.println(JwtUtil.parseToken(token));
    }

    @Test
    void testHuTool(){
        // 以下的JWTUtil是糊涂工具包给我们提供的
        String secret = "0fujsajfsa;=!f312jkads1873"; // 秘钥
        // 组装负载
        HashMap<String, Object> payLoad = new HashMap<>();
        payLoad.put("id", 1234);
        payLoad.put("phone", "137289012563");
        payLoad.put("birthday", new Date());

        // 1.生成JWT
        String token = JWTUtil.createToken(payLoad, secret.getBytes());
        // 也可以自定义签名算法 使用hs384算法
        String token1 = JWTUtil.createToken(payLoad, JWTSignerUtil.hs384(secret.getBytes()));
        System.out.println(token);
        System.out.println(token1);

        // 2.验证JWT
        boolean verify = JWTUtil.verify(token, secret.getBytes());
        boolean verify1 = JWTUtil.verify(token1, JWTSignerUtil.hs384(secret.getBytes()));
        System.out.println(verify);
        System.out.println(verify1);

        // 3.解析JWT负载中的数据
        JWT jwt = JWTUtil.parseToken(token);
        JWTPayload jwtPayload = jwt.getPayload();
        Object id = jwtPayload.getClaim("id");
        Object phone = jwtPayload.getClaim("phone");
        Object birthday = jwtPayload.getClaim("birthday");
        System.out.println(id);
        System.out.println(phone);
        System.out.println(birthday);

        // 采用以下方式解析可以转型
        JSONObject payloads = jwt.getPayloads();
        Integer id1 = payloads.get("id", Integer.class);
        String phone1 = payloads.get("phone", String.class);
        Date birthday1 = payloads.get("birthday", Date.class);
        System.out.println(id1 + "-----------" + phone1 + "-----------" + birthday1);
    }

}
