package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성주입(DI)
	private UserRepository userRepository;
	
	@Transactional //더티체킹 : 함수 종료시에 자동 커밋
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		//json 데이터를 요청 => (Messageconverter의 Jackson라이브러리가) Java Object로 변환해서 받아줌
		
		User user = userRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//requestUser.setId(id);
		//requestUser.setUsername("ssal");
		
		//userRepository.save(user);
		/*
		 * save함수는 id를 전달하지 않으면 insert를 해주고,
		 * id 전달시 해당 id에 데이터 있으면 -> update
		 * 		   해당 id에 데이터 없으면 -> insert
		 */		
		return null;
	}
	
	
	@GetMapping("dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터를 리턴
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser=userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")	//{id}주소로 파라미터를 전달 받을 수 있음
	public User detail(@PathVariable int id) {
		//user/4을 찾는데 db에서 못찾아오게 되면 user가 null이 됨
		//-> return null이 리턴되서 오류
		//Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id: "+id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// => 변환 : json(Gson 라이브러리)
		// 스프링부트는 MessageConverter가 응답 시에 자동으로 작동
		// 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해 
		// user 오브젝트를 json 으로 변환하여 브라우저에게 던져준다.
		return user; 
	}
	
	//http://localhost:8000/b log/dummy/join (요청)
	//http의 Body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value(약속된 규칙)
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getId());
		System.out.println(user.getRole());
		System.out.println(user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
