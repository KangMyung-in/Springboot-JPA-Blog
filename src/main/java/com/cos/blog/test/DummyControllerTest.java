package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController //html 아니라 데이터만 리턴해주는 ex) 회원가입 됬다 안됬다 응답만해줌
public class DummyControllerTest {
	
	@Autowired //(의존성 주입임) 스프링이 @RestController 읽어서 DummyControllerTest 메모리에 띄우떄 UserRepository같이 띄어짐
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제실패했습니다. 해당id는 db에 없습니다."; 
		}
		
		return "삭제되었습니다. id :"+id;
	}
	
	@Transactional //더티체킹임
	@PutMapping("/dummy/user/{id}") //update는 putmapping쓴다 //조소창 끝에숫자가 {id} 들어감 ex) 1
	public User updateuser(@PathVariable int id, @RequestBody User requestuser) { //json 데이터를 요청 > java object( MessageConverter의 jackson 라이브러리 호출해서) 변환해서 받아줌 이떄 필요한 어노테이션은  @RequestBody임
		System.out.println("id :"+id);
		System.out.println("password :"+requestuser.getPassword());
		System.out.println("email :"+requestuser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->  {			
			return new IllegalArgumentException("수정에 실패하였습니다.");
			});// findById 해서 못찾으면 orElseThrow(이걸실행해라) 뜻임 
		user.setPassword(requestuser.getPassword());
		user.setEmail(requestuser.getEmail());
		
//		userRepository.save(user); 
		return user;
		// save 함수는 id를 전달하지 않으면 insert를 해주고
		//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
		//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
	}
	
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() { //User 에서 여러건 데이터 받을거니깐 List 타입 씀
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴받을 예정임
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2,sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> PagingUser = userRepository.findAll(pageable);
		
		List<User> users = PagingUser.getContent();
		return  PagingUser;
	}
	
	
	

	//suppier 은 인터페이스고  get함수를 가지고 있음 인터페이스는 new 못하므로 할려면 익명 클래스 만들어야됨
	//{id} 주소로 파라미터 전달 받을 수 있음
	//http://localhost:8000/blog/dummy/user/3 //3이 {id} 들어감
	//but 만약 3이아닌 4를 찾으면 내가 데이터베이스에서 못찾으면 user이 null이 되잖아? 그럼 return 값이 null이 되서 문제가 생기잖아?
	//Optonal 으로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해 그방법이 orElseGet임
	//but IllegalArgumentException 잘못된 인수가 들어온걸 알려주는 이걸 더 많이 씀
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id)  {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.");
			}
		
	});
		//요청 : 웹브라우저 //자바스크립트 or html만 이해함
		//user 객체 = 자바오브젝트
		//그래서 ()웹브라우저가 이해할 수 있는 데이터로 변환함 그게 json임
		//스프링부트는 MessageConverter 라는 애가 응답시에 자동 작동함
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 jackson 라이브러리 호출해서
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져준다
		return user;		
	}
	//http://localhost:8000/blog/dummy/join 요청함
	//http의 body에 username,password,email 데이터를 가지고 요청
	@PostMapping("/dummy/join") //insert 할거니깐 post맵핑 해야됨
	public String join(User user) { //key=value 형태임(약속된 규칙) * 오브젝트(User)로도 받아서 처리할 수 있음 
		// id,role create 부분은 전송받지 않으므로 null 뜰것이다
		System.out.println("username : " +user.getUsername());
		System.out.println("password : " +user.getPassword());
		System.out.println("email : " +user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		
	}
}
