package spring.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class CacheAspect02 {
	
	private Map<Long,Object> cache = new HashMap<>();
	
	@Pointcut("execution(public * spring.calc..*(..))")
	public void targetMethod() {}
	
	@Around("targetMethod()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long)joinPoint.getArgs()[0];
		
		if (cache.containsKey(num)) {
			System.out.println("캐시 Aspect에서 구함 : " + num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed();
		cache.put(num, result); //핵심기능의 결과를 캐시에 저장
		System.out.println("캐시 Aspect에 저장 : " + num);
		
		return result;
	}
}
