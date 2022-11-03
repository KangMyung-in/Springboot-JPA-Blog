package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java(다른언어도 가능) Object를 -> 테이블로 매핑해주는 기술
@Data //getter/setter 역할
@NoArgsConstructor //빈 생성자
@AllArgsConstructor// 전체 생성자
@Builder //객체 값 순서를 헷갈려서 객체의 값을 잘못 넣는 실수하는 것을 방지한다.
@Entity //user 클래스가 MySQL에 테이블이 생성됨
public class User {
	@Id // primary key 라는걸 알려줌
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db 넘버링 전략을 따라간다는 뜻임 ex) 오라클이면 시퀸스 , mysql이면 auto_inclement임
	private int id; //시퀸스 ,auto_inclement
	
	@Column(nullable = false, length = 30,unique = true) //아아디가 빈공간 안된다는뜻 ,길이는 30글자 미만 ,아이디 중복방지 위해 unique 씀
	private String username; //아이디
	
	@Column(nullable = false, length = 100) //넉넉하게 준 이유는 1234 > 해쉬로 바꿀거임 (암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	
//	@ColumnDefault("'user'") //회원가입할때 디폴트값은 user다. 이떄 ' (홉따옴표  붙여야됨 문자라는걸 알려줘야 하니깐)
	//DB는 RoleType이 없으므로 
	@Enumerated(EnumType.STRING) //@Enumerated 붙여줘서 string이란걸 알려줘야됨
	private RoleType role; //Enum 을 쓰면 사이트에  도메인(범위)설정 가능하다  또한 RoleType 쓰면 ex) ADMIN,,USER 타입이 2개밖에 못넣게 강제됨 
	
	@CreationTimestamp //데이터가 insert or update 될떄 값이 자동으로 현재시간 들어감
	private Timestamp createdTime;

}
