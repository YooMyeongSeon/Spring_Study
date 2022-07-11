package spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.ChangePasswordService;
import spring.validator.ChangePwdValidator;
import spring.vo.AuthInfo;
import spring.vo.ChangePwdCommand;

@Controller
@RequestMapping("/edit")
public class ChangePwdController {
	private ChangePasswordService changePasswordService;
	
	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}
	
	//1.Model 객체를 이용하는 방법
//	@GetMapping("/changePassword")
//	public String form(Model model) {
//		model.addAttribute("changePwdCommand", new ChangePwdCommand());
//		return "edit/changePwdForm";
//	}
	
	//2.ModelAttribute 어노테이션 이용 방법
//	@GetMapping("/changePassword")
//	public String form(@ModelAttribute("changePwdCommand")ChangePwdCommand changePwdCommand) {
//		return "edit/changePwdForm";
//	}

	//3.Post하고 같은 커맨드 객체를 이용하는 방법(ModelAttribute 생략 가능)
	@GetMapping("/changePassword")
	public String form(ChangePwdCommand changePwdCommand, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		if (authInfo == null) {
			return "redirect:login";
		}

		return "edit/changePwdForm";
	}
	
	@PostMapping("/changePassword")
	public String submit(ChangePwdCommand changePwdCommand, Errors error, HttpSession session) {
		new ChangePwdValidator().validate(changePwdCommand, error);
		
		if (error.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		try {
			changePasswordService.changePassword(authInfo.getEmail(),
					changePwdCommand.getCurrentPassword(),
					changePwdCommand.getNewPassword());
			
			return "edit/changedPwd";
		} catch(IdPasswordNotMatchingException err) {
			error.rejectValue("currentPassword", "notmatching");
			return "edit/changePwdForm";
		}
	}
}