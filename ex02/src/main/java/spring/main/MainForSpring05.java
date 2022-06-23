package spring.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.exception.AlreadyExistingMemberException;
import spring.exception.IdPasswordNotMatchingException;
import spring.exception.MemberNotFoundException;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberListPrinter;
import spring.printer.VersionPrinter;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class MainForSpring05 {

	static Scanner scan = new Scanner(System.in);
	private static ApplicationContext ctx = null;

	public static void main(String[] args) {
		//ctx = new GenericXmlApplicationContext("classpath:Ctx01.xml");
		// Ctx01.xml에 Ctx02.xml에 포함되어 있음

		String[] conf = {"classpath:Conf01.xml", "classpath:Conf02.xml"}; //ctx = new GenericXmlApplicationContext({"classpath:Conf01.xml", "classpath:Conf02.xml"}); 과 같은 기능
		ctx = new GenericXmlApplicationContext(conf);
		//두개 이상의 설정 파일을 배열로 읽어와서 사용
		
		//회원 가입기능을 만들어 봅시다.
		
		while(true) {
			menu();
			String selectMenu = scan.nextLine();
			
			if(selectMenu.equals("0")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}else if(selectMenu.equals("1")) { // 회원 가입 기능
				precessNewCommand();
				continue;
			}else if(selectMenu.equals("2")) { // 비밀번호 변경 기능
				precessChangeCommand();
				continue;
			}else if(selectMenu.equals("3")) { // 회원 목록 보기
				precessListCommand();
				continue;
			}else if(selectMenu.equals("4")) { // 특정 회원 정보 보기
				precessInfoCommand();
				continue;
			}else if(selectMenu.equals("5")) { // 프로그램 버전 보기
				precessVersionCommand();
				continue;
			}else {
				System.out.println("잘 못 입력했습니다. \n다시 확인하세요");
			}
		}
	}
	
	private static void precessVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter",VersionPrinter.class);
		
		versionPrinter.print();
	}

	private static void precessInfoCommand() {
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		
		System.out.println("조회 하고자 하는 계정(이메일)을 입력하세요");
		String email = scan.nextLine();
		
		infoPrinter.printMemberInfo(email);
	}

	private static void precessListCommand() { //회원 목록 보기
		MemberListPrinter listPrinter = 
				ctx.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void precessNewCommand() { //회원 가입 기능
		System.out.println("회원 가입을 진행합니다.");
		System.out.println("이메일");
		String email = scan.nextLine();
		System.out.println("이름");
		String name = scan.nextLine();
		System.out.println("비밀번호");
		String password = scan.nextLine();
		System.out.println("비밀번호 확인");
		String confirmPassword = scan.nextLine();
		
		//입력 받은 정보를 한꺼번에 묶어서 처리하기 위한 VO객체 : RegisterRequest
		RegisterRequest reg = new RegisterRequest();
		reg.setEmail(email);
		reg.setName(name);
		reg.setPassword(password);
		reg.setConfirmPassword(confirmPassword);
		
		if(!reg.isPasswordEqualToConfirmPassword()) { //입력 비밀번호와 확인 비밀번호를 체크
			System.out.println("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
			return;
		}
		
		//실제적으로 회원 가입 처리
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);
				
		try {
			regSvc.regist(reg);
			System.out.println("가입이 완료되었습니다.");
		} catch(AlreadyExistingMemberException e) {
			System.out.println("이미 존재하는 이메일 입니다.");
		}
	}
	
	private static void precessChangeCommand() { //비밀번호 변경 기능
		System.out.println("비밀번호를 변경합니다.");
		System.out.println("비밀번호를 변경할 게정(이메일)을 입력하세요.");
		String email = scan.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		String oldPassword = scan.nextLine();
		System.out.println("바꿀 비밀번호를 입력하세요.");
		String newPassword = scan.nextLine();
		
		//실제 비밀번호를 변경할 기능을 가진 객체를 불러와야 한다.
		ChangePasswordService changeSvc = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		
		try {
			changeSvc.changePassword(email, oldPassword, newPassword);
			System.out.println("비밀번호가 변경되었습니다.");
		} catch(MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.");
		} catch(IdPasswordNotMatchingException e) {
			System.out.println("이메일과 비밀번호가 일치하지 않습니다.");
		}
	}
	
	private static void menu() {
		System.out.println("메뉴를 선택하세요.");
		System.out.println("1. 회원 가입");
		System.out.println("2. 비밀번호 변경");
		System.out.println("3. 회원 목록 보기");
		System.out.println("4. 회원 정보 보기");
		System.out.println("5. 프로그램 버전 보기");
		System.out.println("0. 프로그램 종료");
	}
}