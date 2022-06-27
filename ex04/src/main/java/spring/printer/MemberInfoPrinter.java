package spring.printer;

import org.springframework.beans.factory.annotation.Autowired;

import spring.dao.MemberDao;
import spring.vo.Member;

public class MemberInfoPrinter {
	
	private MemberDao dao;
	private MemberPrinter printer;
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String email) {
		Member member = dao.selectByEmail(email);
		
		if (member == null) {
			System.out.println("데이터 없음");
			return;
		}
		
		printer.print(member);
		System.out.println();
	}
}