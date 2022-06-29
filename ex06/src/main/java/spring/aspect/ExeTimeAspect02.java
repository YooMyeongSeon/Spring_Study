package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect //공통 기능을 수행하는 클래스로 선언
@Order(0) //여러개의 Aspect 객체에서 우선순위를 정하는 방법
public class ExeTimeAspect02 {
	
	@Pointcut("execution(public * spring.calc..*(..))")
	public void targetMethod() {} //핵심 기능
	
	@Around("targetMethod()")
	public Object m(ProceedingJoinPoint jp) throws Throwable {
		long start = System.nanoTime();

		Object result = jp.proceed();
		
		long end = System.nanoTime();
		
		System.out.println("실행 시간 : " + (end - start));
		return result;
	}
}