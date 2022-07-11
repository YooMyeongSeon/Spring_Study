package spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.vo.RegisterRequest;

public class RegisterRequestValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz);
	}
	
	private static final String emailExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	
	private Pattern pattern = Pattern.compile(emailExp);
	
	@Override
	public void validate(Object target, Errors errors) {
		//검사 대상 객체 : 검사 결과 에러 코드
		//검사 대상 객체의 특정 프로퍼티의 값이나 상태 등이 올바른지 체크하고, 값이 올바르지 않다면 errors에 에러코드를 저장
		RegisterRequest regReq = (RegisterRequest)target;
		
//		if (target instanceof RegisterRequest) { //객체 형변환 가능 여부 체크
//			RegisterRequest regReq = (RegisterRequest)target;
//		}
		
		if (regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else { //이메일 형식 체크
			Matcher matcher = pattern.matcher(regReq.getEmail());
			
			if (!matcher.matches()) { //참이면 정규식 일치, 거짓이면 정규식 불일치
				errors.rejectValue("email", "badMatch");
			}
		}
		
		//ValidationUtils 객체는 자주 사용되는 값 검증 코드를 메서드화 한 것
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		
		//비밀번호화 비밀번호 확인이 일치 하는지
		if (!regReq.getPassword().isEmpty()) {
			if (!regReq.isPasswordEqualToConfirmPassword()) { //일치하지 않는다면
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}
}