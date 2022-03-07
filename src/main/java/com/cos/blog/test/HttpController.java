package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML파일)
//@Controller 사용

//사용자가 요청 -> 응답(DATA)
@RestController
public class HttpController {
	
	// 인터넷 브라우저 요청은 get방식만 가능!
	//http://localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) {	
	//public String getTest(@RequestParam int id, @RequestParam String username) {
		return "get 요청: "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}

	//http://localhost:8080/http/post
	@PostMapping("/http/post")
	public String postTest() {
		return "post요청";
	}
	
	//http://localhost:8080/http/put
	@PutMapping("http/put")
	public String putTest() {
		return "put요청";
	}
	
	//http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}

}
