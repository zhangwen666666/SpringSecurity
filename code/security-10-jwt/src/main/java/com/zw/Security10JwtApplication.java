package com.zw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@MapperScan("com.zw.mapper")
@SpringBootApplication
public class Security10JwtApplication implements CommandLineRunner {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public static void main(String[] args){
		SpringApplication.run(Security10JwtApplication.class, args);
	}

	/**
	 * 该run方法在springboot项目启动的时候会执行一次（只执行一次）
	 * 在上面的main方法执行之后，就会执行该run方法
	 * 所以在项目中，经常用来对项目做一些初始化的工作
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		redisTemplate.setKeySerializer(RedisSerializer.string());
		redisTemplate.setValueSerializer(RedisSerializer.string());
		redisTemplate.setHashKeySerializer(RedisSerializer.string());
		redisTemplate.setHashValueSerializer(RedisSerializer.string());
	}
}
