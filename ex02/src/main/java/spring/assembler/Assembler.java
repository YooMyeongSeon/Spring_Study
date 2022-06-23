package spring.assembler;

import spring.dao.MemberDao;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

public class Assembler {
	private MemberDao dao;
	private MemberRegisterService memberRegisterService;
	private ChangePasswordService changePasswordService;
	
	public Assembler() {
		dao = new MemberDao();
		memberRegisterService = new MemberRegisterService(dao);
		changePasswordService = new ChangePasswordService(dao);
	}

	public MemberDao getDao() {
		return dao;
	}
	public MemberRegisterService getMemberRegisterService() {
		return memberRegisterService;
	}
	public ChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}
}