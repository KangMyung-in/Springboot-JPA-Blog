package com.cos.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) { 
	boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.글삭제하기(id); //이렇게 하고  BoardService 에서 글 삭제하기 만들어 줘야됨
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PutMapping("/api/board/{id}") 
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) { //id랑 board를 받는다
		boardService.글수정하기(id,board);	//boardService.글수정하기 호출하고 여기로 다시돌아옴 (id,board) 넘긴다	,글 수정하기 만들어야함
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave( @RequestBody ReplySaveRequestDto replySaveRequestDto) { 
		
		//데이터를 받을때 컨트롤러에서 dto 만들어서 받는게 좋다 why? 내가 필요한 객체를 한번에 받을 수 있음
		boardService.댓글쓰기(replySaveRequestDto); //boardService의 댓글쓰기에 메서드에 replySaveRequestDto 넣어줌 그리고 다시 여기로 옴 , 도 넘겨줌
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //board.js에 resp에 응답을 해준다

	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}") ///api/board/{boardId}/reply/{replyId} 삭제할 것이다
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
		boardService.댓글삭제(replyId); //댓글삭제 함수도 boardService 에 만들어준다
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
		
}
