package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice  //예외가 발생했을떄 어디서든 이 함수를 발생 시킬려면  @ControllerAdvice  필요함
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class) //IllegalArgumentException 발생하면 그에러를 e 담아줌
	public ResponseDto<String>  handleArgumentException(Exception e) { //모든 exceptin 받을려면  exception 씀
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()); //500이라는 에러가 생김
	}

}
