package com.example.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: Spring Boot 애플리케이션임을 명시
@SpringBootApplication
public class RestfulApiExampleApplication {

	public static void main(String[] args) {
		System.out.println("프로그램 시작...");
		
		// Spring Boot 애플리케이션 실행..( API 서버 실행 )
		SpringApplication.run(RestfulApiExampleApplication.class, args);
	}
}
