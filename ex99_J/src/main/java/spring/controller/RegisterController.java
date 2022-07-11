package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.exception.AlreadyExistingMemberException;
import spring.service.MemberRegisterService;
import spring.validator.RegisterRequestValidator;
import spring.vo.RegisterRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	@RequestMapping("/step1") //실제 주소창 주소는 "localhost:8085/ex99/register/step1" 으로 호출
	public String handlerStep1() {
		return "register/step1"; //WEB-INF/views/register/step1.jsp 로 응답
	}
	
	//1. 클라이언트에서 값을 얻어오는 방법 A
	//@RequestMapping(value="/register/step2", method=RequestMethod.POST) 으로 작성하는 문법을 아래 @PostMapping으로 대체
//	@PostMapping("/register/step2")
//	public String handlerStep2(HttpServletRequest request) {
//		String agreeParam = request.getParameter("agree");
//		
//		if (agreeParam == null || !agreeParam.equals("true")) {
//			return "register/step1";
//		}
//		
//		return "register/step2";
//	}
	
	//1. 클라이언트에서 값을 얻어오는 방법 B
	//@RequestMapping(value="/register/step2", method=RequestMethod.POST) 으로 작성하는 문법을 아래 @PostMapping으로 대체
	@PostMapping("/step2")
	public String handlerStep2(@RequestParam(value="agree", required = false, defaultValue = "false")boolean agree, Model model) {
		if (!agree) {
			return "redirect:step1";
		}
		model.addAttribute("formData", new RegisterRequest());
		return "register/step2";
	}
	
	@GetMapping("/step2")
	public String handlerStep2Get() {
		//return "register/step1"; //주소창에는 step2로 보여지게됨, 실제 보여지는 페이지는 step1
		return "redirect:step1";
	}
	
//	@PostMapping("/step3")
//	public String handlerStep3(HttpServletRequest request) { 값 가져오기 A
//		String email = request.getParameter("email");
//		return "register/step3";
//	}
	
//	@PostMapping("/step3") 
//	public String handlerStep3(@RequestParam("email")String email, 값 가져오기 B
//		@RequestParam("name")String name,
//		@RequestParam("password")String password,
//		@RequestParam("confirmPassword")String confirmPassword) {
//		return "register/step3";
//	}
	
	@PostMapping("/step3")
	public String handlerStep3(@ModelAttribute("formData")RegisterRequest regReq, Errors err) { //커맨드 객체
//		System.out.println("이름 : " + regReq.getName());
//		System.out.println("이메일 : " + regReq.getEmail());
//		System.out.println("암호 : " + regReq.getPassword());
//		System.out.println("확인 암호 : " + regReq.getConfirmPassword());
		
		new RegisterRequestValidator().validate(regReq, err);
		
		if (err.hasErrors()) {
			return "register/step2";
		}
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch(AlreadyExistingMemberException e) {
			err.rejectValue("email", "duplicate"); //중복 에러
			return "register/step2";
		}
	}
}