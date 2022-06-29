package spring.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class ExeTimeAspect01 { //공통의 기능을 담당하는 Aspect 객체
	public Object m(ProceedingJoinPoint jp) throws Throwable {//ProceedingJoinPoint : 핵심 기능에 관련된 정보
		long start = System.nanoTime();
		
		//------------------------------

		Object result = jp.proceed(); //핵심기능 수행 메서드

		//------------------------------
		
		long end = System.nanoTime();
		
		System.out.println("실행 시간 : " + (end - start));
		
		Signature sig = jp.getSignature(); //핵심기능을 가진 메서드의 정보를 꺼내오는 기능
		String methodName = sig.getName(); //메서드의 이름
		String methodAllInfo = sig.toLongString(); //매개정보, 반환정보
		String ClassName = jp.getTarget().getClass().getSimpleName(); //대상 객체의 이름 정보
		String argName = Arrays.toString(jp.getArgs()); //매개값의 정보(배열 형태)
		
		System.out.println("핵심기능 메서드 : " + methodName);
		System.out.println("핵심기능 메서드 정보 : " + methodAllInfo);
		System.out.println("대상 객체의 이름 : " + ClassName);
		System.out.println("매개값 정보 : " + argName);
		
		return result;
	}
}