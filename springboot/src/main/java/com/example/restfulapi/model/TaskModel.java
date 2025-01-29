package com.example.restfulapi.model;

// 할 일 정보를 나타내는 Task 클래스
/** TaskModel 역할
 *  ㅇ id, title, completed 속성을 가지는 자바 객체로 할 일 정보를 나타냅니다.
 *  ㅇ 데이터를 저장하고 이동하는 데 사용
 */
public class TaskModel {

	private int id;				// 할 일 고유 식별자
	private String title;		// 할 일 제목
	private boolean completed;	// 할 일 완료 여부
	
	// 기본 생성자: String Boot가 객체를 생성할 때 사용
	public TaskModel() {};
	
	// 생성자 객체 초기화
	public TaskModel(int id, String title, boolean completed) {
		this.id = id;
		this.title = title;
		this.completed = completed;
	}
	
	// getter 메서드
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public boolean getCompleted() {
		return completed;
	}
	
	// setter 메서드
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	// toString(디버깅 용이)
	@Override
	public String toString() {
		return "TaskModel {id = "+ id + ", title = " + title + ", completed = " + completed + "}";
	}
}
