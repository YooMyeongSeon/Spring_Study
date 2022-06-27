package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.printer.MemberInfoPrinter;
import spring.printer.MemberPrinter;

@Configuration
public class JavaConf02 {
	@Autowired
	private JavaConf01 javaConf01; //다른 설정파일을 주입 받는다.
	
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter seter = new MemberInfoPrinter();
		seter.setDao(javaConf01.memberDao());
		return seter;
	}
}
