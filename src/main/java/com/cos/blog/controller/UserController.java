package com.cos.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KaKaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//인증이 안된 사용자들이 출입할 수 있는 경로는/auth 붙일거임
//그냥 주소가 / 이면 index.jpsp 허용
//static 폴더 이하에 있는 js/css/image
@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm"; //user 폴더안에 joinForm을 찾아감
		
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm"; //user 폴더안에 joinForm을 찾아감
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { // @ResponseBody 붙이면 Data 를 리턴해주는 컨트롤러 함수
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders(); //httpHeader 오브젝트 생성
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8"); //Content-type 타압에 담는다는것은 내가 전송할 body http 데이터가 KEY= value 형태라는 것을 알려주는 것 임
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // body 데이터를 담을 오브젝트 만듦
		params.add("grant_type","authorization_code");
		params.add("client_id","d03461f22ad12b9b260133dca86a8a1d");
		params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
		params.add("code",code);
		
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = //kakaoTokenRequest 가 4개의 body 데이터와 headers 값을 1개의 오브젝트에 담아버림
				new HttpEntity<>(params,headers);
		
		//http 요청하기 -POST방식으로 그리고 response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", //토큰 요청주소
				HttpMethod.POST, //요청방식
				 kakaoTokenRequest,
				 String.class //응답은 string 으로 받는다
				);
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			 oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰:"+oauthToken.getAccess_token());
	RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders(); //httpHeader 오브젝트 생성
		headers2.add("Authorization","Bearer $"+oauthToken.getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8"); //Content-type 타압에 담는다는것은 내가 전송할 body http 데이터가 KEY= value 형태라는 것을 알려주는 것 임
		
	
		
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = //kakaoTokenRequest 가 4개의 body 데이터와 headers 값을 1개의 오브젝트에 담아버림
				new HttpEntity<>(headers2);
		
		//http 요청하기 -POST방식으로 그리고 response 변수의 응답 받음
		ResponseEntity<String>  response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", //토큰 요청주소
				HttpMethod.POST, //요청방식
				 kakaoProfileRequest2,
				 String.class //응답은 string 으로 받는다
				);
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KaKaoProfile kaKaoProfile = null;
		try {
			kaKaoProfile = objectMapper2.readValue(response2.getBody(), KaKaoProfile .class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 아아디(번호) :"+ kaKaoProfile.getId());
		System.out.println("카카오 이메일 :"+ kaKaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그 유저네임"+kaKaoProfile.getKakao_account().getEmail()+"_"+kaKaoProfile.getId());
		System.out.println("블로그서버 이메일"+kaKaoProfile.getKakao_account().getEmail());
//		UUID garbagePassword = UUID.randomUUID(); //UUID는 중복되지 않는 어떤 특정한 값을 만들어내는 알고리즘
		System.out.println("블로그서버 패스워드 : "+cosKey);
		
		User kakaoUser = User.builder()
				.username(kaKaoProfile.getKakao_account().getEmail()+"_"+kaKaoProfile.getId())
				.password(cosKey.toString())
				.email(kaKaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
	
		// 가입자 or 비가입자 체크해서 처리		
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		
		if(originUser.getUsername() == null) { 
			System.out.print("기존 회원이 아니므로 자동 회원가입을 진행 합니다 !");
			userService.회원가입(kakaoUser); //회원가입  호출함
			
		}
		
		System.out.println("자동 로그인 진행합니다");
		// 로그인 처리	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		System.out.println(response2.getBody());
		return "redirect:/";
						
		//POST 방식으로 key=value 데이터를 요청할것임(카카오 쪽으로) 그러나 post방식은 a태그로(하이퍼방식) 요청못함		
	}
	
		@GetMapping("/user/updateForm")
		public String updateForm() {
			return "user/updateForm";
	
	}

}
