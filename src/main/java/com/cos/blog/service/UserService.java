package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포턴트 스캔을 통해서 Bean에 등록을 해준다.IoC를 해줌(메모리에 대신 띄워준다는 뜻)
@Service
public class UserService {
		
		@Autowired
		private BCryptPasswordEncoder encoder;
	
		@Autowired
		private UserRepository userRepository;
		
		
		@Transactional
		public void join(User user) {
			String rawPassword = user.getPassword(); // 비밀번호 원본(1234)
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
			
		}
}
