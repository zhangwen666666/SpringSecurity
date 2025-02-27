package com.zw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zw.mapper")
@SpringBootApplication
public class Security07CodePermissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Security07CodePermissionApplication.class, args);
	}

}
