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

import com.example.restfulapi.model.ProductModel;

@RestController 				// @RestController: REST 컨트롤러임을 명시
@RequestMapping("/products")	// "/products" 경로로 시작하는 모든 요청 처리
public class ProductController {

	// 상품 목록을 저장할 리스트(메모리)
	private List<ProductModel> productsList = new ArrayList<ProductModel>(); 
	// 상품 ID 자동생성
	private AtomicInteger nextId = new AtomicInteger(1);
	
	// @GetMapping: HTTP GET 요청을 처리
	// -> /products 경로로 GET 요청이 오면 이 메서드가 실행됨
	@GetMapping
	public List<ProductModel> getProductAllList() {
		// 서버에 저장된 모든 상품 목록을 반환
		return productsList;
	}
	
	// @GetMapping("/{id}"): HTTP GET 요청을 처리, 경로 변수 id를 사용
	// -> /products/123 과 같이 id 값으로 요청이 올 때 이 메서드가 실행됨
	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getProduct(@PathVariable int id) {
		// @PathVariable: URI 경로의 변수 id를 int 타입 파라미터 id에 바인딩
		// PostMan으로 http://localhost:8080/products/id(요 id값이 파라미터 id로 바인딩됨)
		
		for(ProductModel product : productsList) {
			if(product.getId() == id) {
				
				// 상품을 찾으면 HTTP 상태 코드 200 OK 응답과 함께 상품 객체를 반환
				return ResponseEntity.ok(product);
			}
		}
		
		// 상품을 찾지 못하면 HTTP 상태 코드 404 Not Found 응답을 반환 
		return ResponseEntity.notFound().build(); 
	}
	
	// @PostMapping: HTTP POST 요청을 처리
	// -> /products 경로로 POST 요청이 오면 이 메서드가 실행
	@PostMapping
	public ResponseEntity<ProductModel> addProduct(@RequestBody ProductModel productModel){
		
		// 새로운 상품 ID 할당
		productModel.setId(nextId.getAndIncrement());
		
		// 상품 목록에 새로운 상품 추가
		productsList.add(productModel);
		
		// HTTP 상태 코드 201 Created 응답과 함께 추가된 상품 객체를 반환
		return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
	}
	
	// @PutMapping("/{id}"): HTTP PUT 요청을 처리, 경로 변수 id를 사용
	// -> /products/123 과 같이 id 값으로 요청이 올 때 이 메서드가 실행
	@PutMapping("/{id}")
	public ResponseEntity<ProductModel> updateProduct(@PathVariable int id, @RequestBody ProductModel updateProduct) {
		
		// @PathVariable: URI 경로의 변수 id를 int 타입 파라미터 id에 바인딩
		// @RequestBody: HTTP 요청 본문(JSON 영역)의 데이터를 Product 객체로 바인딩
		
		for(ProductModel product : productsList) {
			
			if(product.getId() == id) {
				// 상품 수정
				product.setName(updateProduct.getName());
				product.setPrice(updateProduct.getPrice());
			
				// 상품을 찾으면 HTTP 상태 코드 200 OK 응답과 함께 수정된 상품 객체를 반환
				return ResponseEntity.ok(product);
			}
		}
		
		// 상품을 찾지 못하면 HTTP 상태 코드 404 Not Found 응답을 반환
		return ResponseEntity.notFound().build();
	}
	
	
	// @DeleteMapping("/{id}"): HTTP DELETE 요청을 처리, 경로 변수 id 사용
	// -> /products/123 과 같이 id 값으로 요청이 올 때 이 메서드가 실행
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductModel> deleteProduct(@PathVariable int id) {
		// 상품 목록에서 해당 ID의 상품을 삭제
		productsList.removeIf(product -> product.getId() == id);
		// HTTP 상태 코드 204 No Content 응답을 반환
		return ResponseEntity.noContent().build();
	}
}
