package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//SELECT* FROM user WHERE username = 1?; //naming 쿼리임 username파라미터가 ? 들어가면서 해당 쿼리가 실행됨 WHERE 다음값 첫번째는 대문자로 해줘야됨
	Optional<User> findByUsername(String username);
	
//제네릭에 User과 integer을 적는다
//JpaRepository 는 User테이블이 관리하는 JpaRepository다뜻임  User테이블에 primary key는 Integer(숫자)야
//자동으로 bean이 등록이 된다
	//@Repository 생략이 가능하다 //예전에는 @Repository 붙여줘야 스프링이 컴포넌트할때 밑에  UserRepository 띄어줬음
//기본적인 crud 형식은 JpaRepository 모든함수 가지고 있음

//jpa naming 쿼리
	//SELECT*FROM user WHERE username = ?1 AND password = ?2;
//	User findByUsernameAndPassword(String username, String password); //리턴은 User 객체로 해준다 ,요즘 이런 방식 사용 x

	//@Query(value="SELECT *FROM user WHERE username=?1 AND password =2? , nativeQuery = true")
	//User login(String username, String password);
}
