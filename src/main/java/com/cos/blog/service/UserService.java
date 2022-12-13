package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 컴포턴트 스캔을 통해서 bean에 띄어줌 즉 ioc해줌 메모리에 띄어준다는 뜻
public class UserService {

	@Autowired
	private UserRepository userRepository; // UserRepository 연결함
	
	@Autowired
	private BCryptPasswordEncoder encoder; //di 주입됨
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) { //회원을 찾는데 String username 을 찾을거임
		User user = userRepository.findByUsername(username).orElseGet(()->{ //orElseGet은 만약 값이 없으면 빈값을 리턴해라
			return new User();
		});
		return user;
		
	}
	

	@Transactional //회원가입 전체가 하나의 트랜젝션으로 묶임
		public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //1234의 원본임
		String encpassword = encoder.encode(rawPassword); //해쉬(암호화 됬음)
				user.setPassword(encpassword);
				user.setRole(RoleType.USER);
				userRepository.save(user);//userRepository.save 호출함
	}
//	@Transactional (readOnly = true)// Select할때 트랜잭션 시작 ,해당서비스 종료될때 해당 트랜잭션 종료(정합성) , 요즘 이런 트랜잭션 사용안함
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());// 
//	}
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고 영속화된 User 오브젝트를 수정
		//select 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줍니다
		User persistance = userRepository.findById(user.getId()).orElseThrow(()-> {
			return new IllegalArgumentException("회원찾기 실패");
		
		});
		// validate 체크 카카오 로그인 이용자는 이 함수 사용 못함
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword(); //사용자로부터 패스워드 받는다
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());		
		}
		
		
		
		//회원수정 함수 종료시 = 서비스 종료뜻함 = 트램잭션 종료 = commit 이 자동으로 된다
		//영속화된 persistance 객체의 변화가 감지되면(더티체킹이) 되어 update문을 날려줌
	

	
	}

}
