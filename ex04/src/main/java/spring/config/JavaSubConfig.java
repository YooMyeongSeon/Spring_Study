package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import spring.dao.MemberDao;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberPrinter;

public class JavaSubConfig {
	@Autowired
	private MemberDao dao;
	
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter seter = new MemberInfoPrinter();
		seter.setDao(dao);
		seter.setPrinter(printer());
		return seter;
	}
}
