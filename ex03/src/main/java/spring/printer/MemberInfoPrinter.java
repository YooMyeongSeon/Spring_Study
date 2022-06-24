package spring.printer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import spring.dao.MemberDao;
import spring.vo.Member;

public class MemberInfoPrinter {
	
	private MemberDao dao;
	
	@Autowired
	@Qualifier("chk01") //없으면 이름이 같은 객체를 다음으로 찾고, 그것 마저 없다면 예외처리
	private MemberPrinter printer;
	
	@Autowired
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

//	public void setPrinter(MemberPrinter printer) {
//		this.printer = printer;
//	}
//	
	public void printMemberInfo(String email) {
		System.out.println("객체 : " + printer.getTest());
		
		Member member = dao.selectByEmail(email);
		
		if (member == null) {
			System.out.println("데이터 없음");
			return;
		}
		
		printer.print(member);
		System.out.println();
	}
	
	@Autowired
	public MemberInfoPrinter(MemberDao dao, @Qualifier("chk01")MemberPrinter printer) {
		this.dao = dao;
		this.printer = printer;
	}
}