package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.UserService;

@Controller
public class UserController {

	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}
