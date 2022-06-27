package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.dao.MemberDao;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberPrinter;

@Configuration
public class JavaConfPartSub {
	@Autowired
	private MemberDao dao;
	//설정파일을 하나로 사용할 것이므로, 설정 파일 클래스를 자동주입 하지 않고 필요한 빈만 주입받아서 사용
	
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter seter = new MemberInfoPrinter();
		seter.setDao(dao);
		return seter;
	}
}