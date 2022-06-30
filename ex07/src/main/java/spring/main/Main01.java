package spring.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.dao.MemberDao;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberListPrinter;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class Main01 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx01.xml");
		
		//selectAll
		MemberListPrinter listPrt = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrt.printAll();
		
		System.out.println("------------------------------");
		
		//selectByEmail
		MemberInfoPrinter infoPrt = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrt.printMemberInfo("park@naver.com");
		
		//InsertMember
//		MemberRegisterService regSvc = ctx.getBean("regSvc", MemberRegisterService.class);
//		insert(regSvc);
		
		System.out.println("------------------------------");
		
		//UpdateMember
		MemberDao dao = ctx.getBean("dao", MemberDao.class);
		
		ChangePasswordService changeSvc = ctx.getBean("changeSvc", ChangePasswordService.class);
		update(changeSvc, "park@naver.com", dao);
		
		//DeleteMember
		dao.deleteMember("Yoo@naver.com");
	}
	
//	private static void insert(MemberRegisterService regSvc) {
//		RegisterRequest rr = new RegisterRequest();
//		
//		//매번 달라지는 이름과 이메일을 저장하기
//		SimpleDateFormat dateFmt = new SimpleDateFormat("YYYYMMdd-HHmmss");
//		String name = dateFmt.format(new Date());
//		
//		rr.setEmail(name + "@naver.com");
//		rr.setPassword("1234");
//		rr.setName(name);
//		rr.setConfirmPassword("1234");
//		
//		regSvc.regist(rr);
//	}
	
	private static void update(ChangePasswordService changeSvc, String email, MemberDao dao) {
		String oldPwd = dao.selectByEmail(email).getPassword(); //이메일로 기존 비밀번호를 가져오기
		String newPwd = "";
		
		if (oldPwd.equals("1234")) {
			newPwd = "4321";
		} else if (oldPwd.equals("4321")) {
			newPwd = "1234";
		}

		changeSvc.changePassword(email, oldPwd, newPwd);
	}
}