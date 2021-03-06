package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiContorller {

	@Autowired
	private UserService userService;
	

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { //username, password, email
		user.setRole(RoleType.USER);
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // object Object 리턴
		// 리턴시 자바오브젝트가 리턴되면 json으로 변환해서 리턴(Jackson lib가 실행)
	}

}
