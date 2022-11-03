package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// @RestController 는 사용자가 요청하면 > 응답해줌(data)
//@Controller는 사용자가 요청하면 >응답해줌(html파일)
@RestController 
public class HttpControllerTest { 
	
	private static final String TAG= " HttpControllerTest " ;
	//인터넷 브라우저 요청은 무조건 get 요청밖에 안된다 ex)post x
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { //Member 객체가 물음표 뒤에 값을 넣어줌 ?id=1&username=ssar&password=1234&email=ssar@nate.com
		System.out.println("getter");
		return "get 요청 : "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //mine타입이 text/plain
		return "post 요청 : "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest( ) {
		return "put 요청";
	}
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest( ) {
		return "delete 요청";
	}
}
