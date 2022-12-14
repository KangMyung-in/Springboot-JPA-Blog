package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장을 해준다. (PrincipalDetail) 여기에 저장해준다 
@Data
public class PrincipalDetail implements UserDetails { //타입은  UserDetails 임
	private User user; //PrincipalDetail은 User user 를 품고있다 이걸 콤포지션 이라고한다.

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //계정이 만료되지 않앗는지 리턴한다.(true: 만료안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정이 잠겨잇는지 안잠겨 있는지 true : 안잠겨있음
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //비밀번호가 만료됬는지 안됬는지 true: 만료안됨
		return true;
	}

	@Override
	public boolean isEnabled() { //계정이 활성화 됫는지 안됬는지 true 가 활성화됨
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //계정이 갖고 있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}


}
