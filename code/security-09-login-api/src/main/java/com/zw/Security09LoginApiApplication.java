package com.zw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zw.mapper")
@SpringBootApplication
public class Security09LoginApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Security09LoginApiApplication.class, args);
	}

}
