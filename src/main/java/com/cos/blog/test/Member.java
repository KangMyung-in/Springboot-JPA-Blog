package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
//@Getter getter/setter 합친건 Data
//@NoArgsConstructor 빈생성자
//@AllArgsConstructor 모든필드를 다쓰는 생성자
// 데이터가 변경되지않게 final 붙여주자 // 변경할일 없는 데이터
//@Setter
@Data 
@AllArgsConstructor 
@NoArgsConstructor
//@RequiredArgsConstructor //final 붙은 애들에대한 생성자 만들어줌
public class Member {
	
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	
}
