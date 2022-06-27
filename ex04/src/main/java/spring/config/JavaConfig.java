package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.dao.MemberDao;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberPrinter;
import spring.service.MemberRegisterService;

@Configuration //스프링 설정 파일임을 선언하는 어노테이션
public class JavaConfig {
	//<bean id="memberDao" class="spring.dao.MemberDao"></bean> 와 같은 기능 생성
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter seter = new MemberInfoPrinter();
		seter.setDao(memberDao()); //수동 주입
//		seter.setPrinter(printer()); //클래스 자체에서 자동주입
		return seter;
	}
	
	
}