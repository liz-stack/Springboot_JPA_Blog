package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML파일)
//@Controller 사용

//사용자가 요청 -> 응답(DATA)
@RestController
public class HttpController {
	
	private static final String TAG = "HttpController Test: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member(1, "liz", "1234", "liz@naver.com");
		Member m = Member.builder().username("liz").password("1234").email("liz@naver.com").build();
		System.out.println(TAG+"getter: "+ m.getUsername());
		m.setUsername("liz1");
		System.out.println(TAG+"getter: "+ m.getUsername());
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 get방식만 가능!
	//http://localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) {//id=1&username=ssar&password=1234&email=ssar@naver.com
	//public String getTest(@RequestParam int id, @RequestParam String username) {
		return "get 요청: "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}

	//http://localhost:8080/http/post
	@PostMapping("/http/post") //text/plain, application/json
	public String postTest(@RequestBody Member m) { //MessageConverter가 자동으로 파싱해서 오브젝트에 넣어줌
		//parsing : 다른 언어로 작성된 문서를 디코딩(문법적으로 해체)
		return "post 요청: "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//http://localhost:8080/http/put
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청"+m.getId()+", "+m.getPassword()+", ";
	}
	
	//http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}

}
