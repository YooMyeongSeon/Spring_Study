package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class ExeTimeAspect01 { //공통의 기능을 담당하는 Aspect 객체
	public Object m(ProceedingJoinPoint jp) throws Throwable {//ProceedingJoinPoint : 핵심 기능에 관련된 정보
		long start = System.nanoTime();
		
		//------------------------------

		Object result = jp.proceed(); //핵심기능 수행 메서드

		//------------------------------
		
		long end = System.nanoTime();
		
		System.out.println("실행 시간 : " + (end - start));
		return result;
	}
}