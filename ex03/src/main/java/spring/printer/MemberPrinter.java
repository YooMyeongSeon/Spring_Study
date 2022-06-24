package spring.printer;

import spring.vo.Member;

public class MemberPrinter {
	
	private String test;

	public void setTest(String test) {
		this.test = test;
	}

	public String getTest() {
		return test;
	}

	public void print(Member member) {
		System.out.printf("회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF\n",
			member.getId(), member.getEmail(), member.getName(), member.getRegisterDate());
	}
}