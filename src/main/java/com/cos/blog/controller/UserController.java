package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로는/auth 붙일거임
//그냥 주소가 / 이면 index.jpsp 허용
//static 폴더 이하에 있는 js/css/image
@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm"; //user 폴더안에 joinForm을 찾아감
		
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm"; //user 폴더안에 joinForm을 찾아감
		
	}

}
