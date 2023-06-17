package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 스프링com.cos.blog 이하의 패키지들을 스캔해서 모든 파일의 메모리에 new 하는것은 아니고
// 특정 어노테이션 붙어있는 클래스 파일들을 new해서 (ioc) 스프링 컨테이너에 관리해준다. 
public class BlogControllerTest { 
		// http://localhost:8080/test/hello
		@GetMapping("/test/hello")
		public String hello() {
			
			return"<h1>hello spring boot<h1>";
		}
}
