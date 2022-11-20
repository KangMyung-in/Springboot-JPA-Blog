package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//빈 등록이란? 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것  @Configuration 쓰면됨 (ioc관리)
// 이 시큐리티가 모든 요청을 가로챔 즉 요청받으면 controller로 가는데 그떄 어떤 함수가 실행되는데 그 함수가 실행되기전에 이게 동작됨
@Configuration // 빈등록 역할
@EnableWebSecurity // 시큐리티 필터링역할 등록되는 뜻임
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크한다는 뜻임.
//@Configuration, @EnableWebSecurity, @EnableGlobalMethodSecurity 3개 어노테이션은 세트라고 생각한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // ioc가 되요 !!
	public BCryptPasswordEncoder encodePWD() { //encodePWD() 함수 호출하면, BCryptPasswordEncoder(); 객체를 리턴받음
		return new BCryptPasswordEncoder();
		
	}
	
//	시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데 해당
//	password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
//	같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() //csrf 토큰 비활성화 뜻함 (테스트시 걸어두는 게 좋음)
		.authorizeRequests()
		.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**") //auth js,css ,image  폴더 쪽으로 들어오는건 누구나 들어올 수 있다 회원가입후 /로 이동하게끔 했으므로 / 도 요청허락해야 메인페이지 나옴.
		.permitAll()
		.anyRequest() //이게 아닌 다른 모든 요청은
		.authenticated() //인증을 해야돼 뜻임
	.and()
		.formLogin()
		.loginPage("/auth/loginForm") // 인증이 되지않은 것들은 로그인 폼으로 온다.
		.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 loadUserByUsername 에서 대신 로그인 해준다.
		.defaultSuccessUrl("/"); //정상적으로 대신 로그인 된것은 여기로 이동해 준다.
	}
}
