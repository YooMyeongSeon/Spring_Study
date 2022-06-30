package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.exception.IdPasswordNotMatchingException;
import spring.exception.MemberNotFoundException;
import spring.service.ChangePasswordService;

public class Main02 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx01.xml");
		
		ChangePasswordService changeSvc = ctx.getBean("changeSvc", ChangePasswordService.class);
		
		try {
			changeSvc.changePassword("kim@naver.com", "1234", "1111");
			System.out.println("암호가 변경되었습니다.");
		} catch(MemberNotFoundException e) {
			System.out.println("회원이 존재하지 않습니다.");
		} catch(IdPasswordNotMatchingException e) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
	}
}