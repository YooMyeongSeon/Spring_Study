package spring.service;

import org.springframework.transaction.annotation.Transactional;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

public class ChangePasswordService {
//	private MemberDao dao = new MemberDao();
	
	private MemberDao dao;
	
	
	public ChangePasswordService(MemberDao memberDao) { //생성자로 의존 객체를 주입
		this.dao = memberDao;
	}
	
	@Transactional
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member member = dao.selectByEmail(email);
		
		if (member == null) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword(oldPassword, newPassword);
		
		dao.updateMember(member);
	}
}