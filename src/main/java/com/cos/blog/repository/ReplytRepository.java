package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;

public interface ReplytRepository extends JpaRepository<Reply, Integer> { //타입은 reply

		//어떤쿼리가 작동할지 알려줘야됌
	@Modifying
	@Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())" ,nativeQuery = true) //replySaveRequestDto 가 들고있는 값 3개가 순서대로 들어감
	int mSave(int userId, int boardId, String content);
}
