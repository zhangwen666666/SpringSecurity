package com.zw.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    // 秘钥，不能让别人知道，这个秘钥放在服务器上
    private static final String secret = "0s/SJ0=;fjas123FH";

    // 怎么生成JWT
    public static String createToken(String userJson) {
        //组装头数据
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return JWT.create()
                //头
                .withHeader(header)
                //负载
                .withClaim("user", userJson)
                .withClaim("phone", "13934774623")
                .withClaim("email", "17829084238@163.com")
                .withClaim("birthday", new Date())
                //签名
                .sign(Algorithm.HMAC256(secret));
    }

    // 验证jwt是否有效 验证jwt有没有被篡改
    public static Boolean verifyToken(String token) {
        try {
            // 使用秘钥创建一个jwt验证对象
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            // 使用验证器对象验证JWT
            jwtVerifier.verify(token);
            // 如果验证没有抛出异常，返回true表示验证通过
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 怎么解析出jwt里面的负载数据
    public static String parseToken(String token) {
        try {
            // 使用秘钥创建一个jwt验证对象
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            // 验证JWT, 得到一个解码后的jwt对象
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            // 通过解码后的jwt对象获取负载的对象
            Claim user = decodedJWT.getClaim("user");
            System.out.println(user.asString());
            Claim phone = decodedJWT.getClaim("phone");
            System.out.println(phone.asString());
            Claim email = decodedJWT.getClaim("email");
            System.out.println(email.asString());
            Claim birthday = decodedJWT.getClaim("birthday");
            System.out.println(birthday.asDate());

            // 如果是自定义的类型 使用xxx.as(Xxx.Class)

            // 返回解析出的user对象
            return user.asString();
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
