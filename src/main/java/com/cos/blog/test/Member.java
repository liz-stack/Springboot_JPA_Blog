package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	//@AllArgsConstructor //모든 필드를 다 쓰는 생성자
	//@NoArgsconstructor //파라미터가 없는 기본 생성자(빈생성자)
	//@RequiredArgsConstructor //final한 필드에 대한 생성자 만들어줌
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	} 
	//@Builder사용시 빌더 패턴을 따로 만들 필요없게됨.& 인자 순서 상관x

	
}
