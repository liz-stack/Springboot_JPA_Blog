package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포턴트 스캔을 통해서 Bean에 등록을 해준다.IoC를 해줌(메모리에 대신 띄워준다는 뜻)
@Service
public class UserService {
		
		@Autowired
		private UserRepository userRepository;
		
		@Transactional
		public void join(User user) {
			userRepository.save(user);
		}
}
