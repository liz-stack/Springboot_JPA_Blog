package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장해준다.
//principal(접근 주체) = 세션처럼 사용 = Spring Security Context에 보관됨
@Getter
public class PrincipalDetail implements UserDetails{


	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	private User user; //컴포지션
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	
	//계정이 만료되지 않았는지를 리턴(true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() { 
		return true;
	}

	//계정이 잠겨있지 않은지 리턴(true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() { 
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴(true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() { 
		return true;
	}

	//계정 활성화 여부(true:활성화)
	@Override
	public boolean isEnabled() { 
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		
		//계정이 가지고있는 권한 리턴
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{ return "ROLE_"+user.getRole();});
		return collectors;
	}
	
}
