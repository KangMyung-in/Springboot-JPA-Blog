package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping({"","/"}) // http://localhost:8000/blog/ 요청해도 가능 ,http://localhost:8000/blog 요청해도 가능
	public String index() {
		// /WEB_INF/views/index(붙고).jsp 붙음
		return "index";
	}

}
