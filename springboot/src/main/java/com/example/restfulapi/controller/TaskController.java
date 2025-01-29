package com.example.restfulapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulapi.model.TaskModel;

// java:HTTP 요청을 처리하는 Controller
/** TaskController의 역할
 *  @RestController, 
 *  @RequestMapping, 
 *  @GetMapping, 
 *  @PostMapping, 
 *  @PutMapping, 
 *  @DeleteMapping 등
 *  의 어노테이션을 사용하여 RESTful API 엔드포인트를 정의
 * 
 * -> 클라이언트의 HTTP 요청을 처리하고, Task 모델을 사용하여 데이터를 관리하고, HTTP 응답을 생성 
 **/


// 컨트롤러 클래스: HTTP 요청을 처리하고 응답을 반환
//@RestController : <dependency>spring-boot-starter-web</dependecy>
//@RequestMapping : pom.xml build/plugin/configuration/annotationProcessorPaths/path/spring-boot-starter-web 추가
@RestController				// @RestController: RESTful API 컨트롤러임을 명시
@RequestMapping("/tasks")	// @RequestMapping: "/tasks" 경로로 시작하는 모든 요청 처리
public class TaskController {
	
	private List<TaskModel> tasks = new ArrayList<>();	 // 할 일 목록을 저장할 리스트
	private AtomicInteger nextId = new AtomicInteger(1); // 할 일 ID 자동 생성
	
	
}
