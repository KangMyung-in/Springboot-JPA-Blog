package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //메모리에 뜰려면 bean 등록해줘야함 
public class PrincipalDetailService implements UserDetailsService{// 타입은 UserDetailsService 임
	
	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌때 username, password 변수 2개를 가로채는데
	//password 부분 처리는 알아서 함.
	//username이 DB에 있는지만 확인해주면 됨 그 확인을  loadUserByUsername 에서 해준다
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username) // 로그인이 진행돌때 loadUserByUsername 함수가 자동으로 실행됨
		.orElseThrow(()->{
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : "+username);
		});
				return new PrincipalDetail(principal); // 이때 시큐리티 세션에 유저 정보가 저장됨
	} 

	
}
