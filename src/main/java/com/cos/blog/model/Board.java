package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board { //게시글 테이블
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_inclement 쓴다는 말임
	private int id; //아아디는 자동으로 만들어짐

	@Column(nullable = false,length = 100) //제목이 빈공간이거나 100글자 제한
	private String title; //게시판 제목부분
	
	@Lob //대용량 데이터 쓸때
	private String content; //섬머노트 라이브러기 사용하면 <html>태그가 섞여서 디자인이 되는데 그래서 글자의 용량이 굉장히 커짐
	
	private int count; //조회수
	
	@ManyToOne(fetch =  FetchType.EAGER) //many= board ,user =One //한명의 유저는 여러개의 게시글을 쓸수 있다는 뜻임 ex) OneTOOne 이면 1명의 유저는 1개의 게시글 밖에 못쓴다 뜻임
	// eager 전략은 1개만 가져오는게 아니라 여러개 ? 가져온다?
	@JoinColumn(name = "userId") //데이터베이스로 만들어 질때는 필드값은 userId로 만들어 지고 연관관계는 manyToone 로 만들어줌
	private User user; //DB는 오브젝트를 저장할 수 없다.(orm 사용하면 가능) 그래서 FK를 사용하는데 자바는 오브젝트 저장가능 but 자바와 데이터베이스는 충돌이남
	// User 객체는 User.java 의 User 테이블을 참조할 것이다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //1개의 게시글은 여러개의 답변을 가질수 있다 // mappby가 적혀있는면 난 연관관계의 주인이 아니다 (즉 난 FK 가 아니다 DB에 컬럼을 만들지 마세요)//reply테이블에 있는 Board 필드명 넣어준다
	//cascade는 특정 Entity의 영속성 상태가 변경되었을 때 이를 연관된 Entity에도 *전파*시킬 지 선택하는 옵션이다.
	//쉽게 말해, 연관된 Entity를 어떻게 처리할지 정해주는 것을 말한다.
	@JsonIgnoreProperties({"board"}) // reply 호출할떄 그안에있는 "board" 무시하고 호출함 무한참조 방지
	@OrderBy("id desc") //내림차순 기능
	private List<Reply> replys; //다변을 1개만 가져오는게 아니므로 여러개니깐 List 씀 
	
	@CreationTimestamp //데이터가 insert or update 될떄 값이 자동으로 현재시간 들어감
	private Timestamp createDate;
}
