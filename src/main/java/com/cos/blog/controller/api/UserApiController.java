package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import lombok.Value;
import lombok.val;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username , password , email 가지고 있음
		System.out.println(" UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 된다.
//		user.setRole(RoleType.USER); //롤타입 값 넣음
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //타입은 인티져 첫번쨰값은 200 두번쨰는 1
	}

//	@PostMapping("/api/user/login") //이건 전통적인 로그인 방식임 요즘 시큐리티 사용함
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) { //응답은 인티져 User의 user 을 받음
//		System.out.println(" UserApiController : login 호출됨");
//		User principal= userService.로그인(user); //principer 은 (접근주체 뜻)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal); 
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //로그인 정상적으로 됬을시 응답
//	}

}
