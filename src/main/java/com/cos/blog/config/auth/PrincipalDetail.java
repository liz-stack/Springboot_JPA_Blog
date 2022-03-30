package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장해준다.
//principal(접근 주체) = 세션처럼 사용 = Spring Security Context에 보관됨
public class PrincipalDetail implements UserDetails{
	
	//private static final long serialVersionUID = 7645618956884452156L;
	private User user; //Composition

	public PrincipalDetail(User user) {
		this.user=user;
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

	@Override
	public boolean isAccountNonExpired() { //계정이 만료되지 않았는지를 리턴(true: 만료안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정이 잠겨있지 않은지 리턴(true: 잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //비밀번호가 만료되지 않았는지 리턴(true: 만료안됨)
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 활성화 여부(true:활성화)
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		//계정이 가지고있는 권한 리턴
		
		Collection<SimpleGrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
		
		return collectors;
	}
	
}
