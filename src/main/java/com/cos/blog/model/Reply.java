package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply { //답변 테이블
	
	@Id // primary key 라는걸 알려줌
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db 넘버링 전략을 따라간다는 뜻임 ex) 오라클이면 시퀸스 , mysql이면 auto_inclement임
	private int id; //시퀸스 ,auto_inclement
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne //1개의 게시글에는 여러개의 답변글이 있을 수 있다. ex) OneToMany 는 여러개의(board) 게시글에 1개의 답변(Reply)만 올수 있다는  말이안됨..
	@JoinColumn(name="boardId") //컬럼 이름이 boardId로 만들어짐
	private Board board; //Board객체는 Board 테이블을 참조함
	
	@ManyToOne //1명의 유저는 여러개의 답변을 달수 있다
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp //답변을 적은 시간 알수 있음
	private Timestamp createDate;
}
