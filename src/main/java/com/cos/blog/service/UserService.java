package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
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
}
