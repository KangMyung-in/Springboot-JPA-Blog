package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

@Service // 스프링이 컴포턴트 스캔을 통해서 bean에 띄어줌 즉 ioc해줌 메모리에 띄어준다는 뜻
public class BoardService {

	@Autowired
	private BoardRepository boardRepository; // UserRepository 연결함
	

	@Transactional //회원가입 전체가 하나의 트랜젝션으로 묶임
		public void 글쓰기(Board board, User user) { // Board board는 title content 두개를 받는다
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional (readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
public void 글삭제하기(int id) {
	System.out.println("글삭제하기 :"+id);
		boardRepository.deleteById(id);
		
	}
}

