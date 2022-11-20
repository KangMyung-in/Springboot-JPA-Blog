package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	//컨트롤러에서 세션은 어떻게 찾는지?
	@GetMapping({"","/"}) // http://localhost:8000/blog/ 요청해도 가능 ,http://localhost:8000/blog 요청해도 가능
	public String index(Model model, @PageableDefault(size=3,sort="id", direction = Sort.Direction.DESC) Pageable pageable) { 
		model.addAttribute("boards", boardService.글목록(pageable)); //누가 /(슬러쉬 요청하면) model에 글 목록을 다가져온다
		// /WEB_INF/views/index(붙고).jsp 붙음
		return "index"; //index.jsp로  boards 가 넘어간다 viewResolve 작동 그리고 viewResolve 는 리턴값 index 앞뒤로 prefix를 붙여줌
	}
	@GetMapping("/board/{id}")
		public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail"; //이젠 detail.jsp 가 필요하다
		
	}
	//User 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
		
	}
	
}
