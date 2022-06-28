package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect //공통 기능을 수행하는 클래스로 선언
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