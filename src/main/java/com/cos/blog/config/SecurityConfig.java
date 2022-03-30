package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//c.f.Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration //빈등록(IOC관리)
@EnableWebSecurity //시큐리티 필터 등록; 모든 리퀘스트 요청을 가로챔(함수 실행 전에 필터링)
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	//1. Bean 어노테이션은 메서드에 붙여서 객체 생성시 사용
	@Bean //IOC가 된다(함수가 리턴하는 값을 스프링이 관리_
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); 
	}
	
	// 2. 시큐리티가 로그인할 때 어떤 암호화로 인코딩해서 비번을 비교할지 알려줘야 함.
	// 시큐리티가 password를 가로채는데 해당 password가 무엇으로 해시가 되어 회원가입이 되었는지 알아야 
	// 같은 해시로 암호화해서 db에 있는 해시와 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	// 3. 필터링
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http 
			.csrf().disable() //csrf토큰 비활성화(테스트시 걸어두는게 좋음)
			.authorizeRequests() //리퀘스트가 들어올때
				.antMatchers("/", "/auth/**", "/js/**", "/css/**")
				.permitAll() //auth 로 들어오는 건 모두 허용
				.anyRequest() //아닌 다른요청은
				.authenticated() //인증필요
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") //인증 되지않은 요청은 로그인페이지로
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해준다
				.defaultSuccessUrl("/") //로그인 후에 홈으로(실패: failureUrl() )
				;
	}
}
