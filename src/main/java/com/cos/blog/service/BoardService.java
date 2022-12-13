package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplytRepository;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;

@Service // 스프링이 컴포턴트 스캔을 통해서 bean에 띄어줌 즉 ioc해줌 메모리에 띄어준다는 뜻
@RequiredArgsConstructor // 초기화되지않은 생성자를  호출할떄 초기화 해준다
public class BoardService {

	
	private final BoardRepository boardRepository; // UserRepository 연결함
	
	
	private final ReplytRepository replytRepository; //ReplytRepository 연결함
	
	@Autowired
	private UserRepository userRepository; //userRepository 연결함

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
		@Transactional
	public void 글수정하기(int id, Board requestBoard) { //요청받은 id requestBoard 받아준다
			Board board = boardRepository.findById(id) //영속화 먼저 시키기
					.orElseThrow(()->{
						return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
					});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent()); //해당함수 종료시(service가 종료될때 ) 트랙잭션이 종료됨 이때  더티체킹 - 자동업데이트(db flash)가 됨
	
		}
		
		@Transactional
		public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) { //User user, int boardId, Reply requestReply 받는다
			
//			User user  =userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{//boardRepository에서 boardId 를  board에 넘겨줌
//				return new IllegalArgumentException("댓글 쓰기 실패 : 유저id를 찾을 수 없습니다.");
//			
//			}); //영속화 완료
//		Board board=	boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{//boardRepository에서 boardId 를  board에 넘겨줌
//			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
//		}); //영속화 완료
		
//		Reply reply = Reply.builder() //reply 객체 빌더패턴으로 만들어서 넣어준다
//				.user(user)
//				.board(board)
//				.content(replySaveRequestDto.getContent())
//				.build();
				
		replytRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent()); //저장하면 boardApi로 돌아감
		}
		
		@Transactional
		public void 댓글삭제(int replyId) {
			replytRepository.deleteById(replyId);
		} 
	}
//private Object user(User user) {
//	return null;
//}



