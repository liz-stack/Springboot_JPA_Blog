package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.UserService;

//인증이 안된 사용자들이 출입할 수 있는 경로로 /auth/** 허용
//그냥 주소가 /이면 index.jsp 허용
//static 이하에 있는 /js/**, /css/**, /image/** 허용


@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}
