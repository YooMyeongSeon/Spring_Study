package spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.exception.MemberNotFoundException;

@ControllerAdvice("spring.controller")
public class CommonExceptionHandler { //AOP
	@ExceptionHandler(MemberNotFoundException.class)
	public String handlerMemberNotFoundException(MemberNotFoundException err) {
		return "member/noMember";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handlerRuntimeException(RuntimeException err) {
		return "error/commonException";
	}
}