package spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.AuthService;
import spring.validator.LoginCommandValidator;
import spring.vo.AuthInfo;
import spring.vo.LoginCommand;

@Controller
public class LoginController {
	private AuthService authService;
	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

//	@GetMapping("/login")
//	public String form(Model model) {
//		model.addAttribute("LoginCommand", new LoginCommand());
//		return "login/loginForm";
//	}
	
	@GetMapping("/login")
	public String form(LoginCommand loginCommand, HttpServletRequest req, @CookieValue(value="remember", required = false) Cookie rememberEmail) {
		HttpSession session = req.getSession();
		
		if (session.getAttribute("authInfo") != null) {
			return "main";
		}
		
		if (rememberEmail != null) {
			loginCommand.setEmail(rememberEmail.getValue());
			loginCommand.setRememberEmail(true);
		}
		
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String submit(@ModelAttribute() LoginCommand loginCommand, Errors err, HttpSession session, HttpServletResponse res) {
		//로그인 성공 여부와 상관없이 이메일 저장

		new LoginCommandValidator().validate(loginCommand, err);
		
		if (err.hasErrors()) {
			return "login/loginForm";
		}
		
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			
			//세션 로그인 처리
			session.setAttribute("authInfo", authInfo);
			
			//로그인 성공한 사람만 이메일 저장
			Cookie rememberCookie = new Cookie("remember", loginCommand.getEmail());
			
			if (loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60*60*24*30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			
			res.addCookie(rememberCookie);
			
			return "login/loginSuccess";
		} catch(IdPasswordNotMatchingException e) {
			err.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}