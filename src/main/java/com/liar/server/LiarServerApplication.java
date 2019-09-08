package com.liar.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liar.server.mapper")
public class LiarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiarServerApplication.class, args);
	}

}
