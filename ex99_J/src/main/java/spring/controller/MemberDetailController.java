package spring.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

@Controller
public class MemberDetailController {
	private MemberDao dao;

	public void setDao(MemberDao dao) {
		this.dao = dao;
	}

	@GetMapping("member/detail/{id}")
	public String detail(@PathVariable("id") Long memberId, Model model) { //특정 회원의 정보 조회 기능
		Member member = dao.selectById(memberId);
		
		if (member == null) {
			throw new MemberNotFoundException();
		}
		
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handlerTypeMismatchException(TypeMismatchException err) {
		return "member/ivalidid";
	}
	
//	@ExceptionHandler(MemberNotFoundException.class)
//	public String handlerMemberNotFoundException(MemberNotFoundException err) {
//		return "member/noMember";
//	}
}