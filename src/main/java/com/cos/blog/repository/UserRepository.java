package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//jsp로 치면 DAO
//자동으로 bean 등록이 됨 => @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> { // User테이블이 관리하는 jpa레파지토리, pk:Int
	//SELECT * FROM user WHERE username =1?
	Optional<User> findByUsername(String username);
}

//JPA Naming 쿼리
// SELECT * FROM user WHERE username =?1 AND password =?2;
// User findByUsernameAndPassword(String username, String password);

/*
 * @Query(value="SELECT * FROM user WHERE username =?1 AND password =?2",
 * nativeQuery = true) User login(String username, String password);
 */