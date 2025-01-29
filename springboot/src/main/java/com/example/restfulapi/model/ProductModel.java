package com.example.restfulapi.model;

public class ProductModel {

	private int id;			// 상품 일련번호
	private String name;	// 상품 명
	private double price;   // 상품 가격
	
	
	// 기본 생성자 호출할 때
	public ProductModel() {};
	
	// 사용자 생성자 호출할 때 new 연산
	public ProductModel(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	// Getter Method()
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	
	// Setter Method() {
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	// TO_String( 디버깅 할 때 용이 )
	@Override
	public String toString() {
		return "Product = { id = " + id + ", name = " + name + ", price = " + price + " }"; 
	}
	
}
