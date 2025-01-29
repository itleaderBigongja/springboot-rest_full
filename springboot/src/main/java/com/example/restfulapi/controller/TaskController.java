package com.example.restfulapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	private List<TaskModel> taskModelList = new ArrayList<>();	 // 할 일 목록을 저장할 리스트
	private AtomicInteger nextId = new AtomicInteger(1); // 할 일 ID 자동 생성
	
	
	// 할 일 목록 조회: GET /tasks
	@GetMapping // @GetMapping: GET 요청 처리
	public List<TaskModel> getAllTask() {
		// 사용 이유: 서버에 저장된 모든 할 일 목록을 조회하기 위해 사용
		return taskModelList;
	}
	
	// 특정 할 일 조회: GET /tasks/{id}
	@GetMapping("/{id}") // @GetMapping{"/id"}: "/tasks/{id}" 경로의 GET 요청 처리
	public ResponseEntity<TaskModel> getTaskById(@PathVariable int id) {
		//@PathVariable: URI 경로의 변수 id를 int 타입 파라미터 id에 바인딩
		
		// 사용 이유: 특정 ID를 가진 할 일 정보를 조회하기 위해 사용
		for(TaskModel task : taskModelList) {
			if(task.getId() == id) {
				return ResponseEntity.ok(task); // 할 일을 찾으면 200 OK 응답과 함께 할 일 반환
			}
		}
		
		// 할 일을 찾지 못하면 404 Not Found 응답 반환
		return ResponseEntity.notFound().build();
	}
	
	// @PostMapping: HTTP POST 요청을 처리
	// -> /tasks 경로로 POST 요청이 오면, 이 메서드가 실행
	@PostMapping
	public ResponseEntity<TaskModel> addTask(@RequestBody TaskModel taskModel) {
		
		// 새로운 할 일 ID 할당
		taskModel.setId(nextId.getAndIncrement());
		
		// 할 일 목록에 새로운 할 일 추가
		taskModelList.add(taskModel);
		
		// HTTP 상태 코드 201 Created 응답과 함께 추가된 할 일 객체를 반환
		return ResponseEntity.status(HttpStatus.CREATED).body(taskModel);
	}
	
	// @PutMapping("/{id}"): HTTP PUT 요청을 처리
	// -> /tasks/123과 같이 id 값으로 요청이 올 때 이 메서드가 실행
	@PutMapping("/{id}")
	public ResponseEntity<TaskModel> updateTaskModel(
			@PathVariable int id,	// @PathVariable: URI 경로의 변수 id를 int 타입 파라미터 id에 바인딩 
			@RequestBody TaskModel updateTaskModel) { // @RequestBody: HTTP 요청 본문의 데이터를 Task 객체로 바인딩
		
		for(TaskModel model : taskModelList) {
			if(model.getId() == id) {
				// 할 일 수정
				model.setTitle(updateTaskModel.getTitle());
				model.setCompleted(updateTaskModel.getCompleted());
				
				// 할 일을 찾으면 HTTP 상태 코드 200 OK 응답과 함께 할 일 객체를 반환
				return ResponseEntity.ok(model);
			}
		}
		
		// 할 일을 찾지 못하면 HTTP 상태 코드 404 Not Found 응답을 반환
		return ResponseEntity.notFound().build();
	}
	
	// @DeleteMapping("/{id}"): HTTP DELETE 요청을 처리
	// -> /tasks/123 과 같이 id 값으로 요청이 올 때, 이 메서드가 실행
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTaskModel(
			@PathVariable int id) // @PathVariable: URI 경로의 변수 id를 int 타입 파라미터 id에 바인딩
	{ 
		// 할 일 목록에서 해당 ID의 할 일 삭제
		taskModelList.removeIf(taskModel -> taskModel.getId() == id);
		
		// HTTP 상태 코드 204 No Content 응답을 반환
		return ResponseEntity.noContent().build();
	}
}
