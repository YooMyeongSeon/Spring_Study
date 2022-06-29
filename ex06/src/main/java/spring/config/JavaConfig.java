package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import spring.aspect.CacheAspect02;
import spring.aspect.ExeTimeAspect02;
import spring.calc.ImpeCalculator;
import spring.calc.RecCalculator;

@Configuration
@EnableAspectJAutoProxy //aop:aspectj-autoproxy 기능
public class JavaConfig { //appCtx02와 같은 기능의 클래스
	@Bean
	public ExeTimeAspect02 exeTimeAspect() {
		return new ExeTimeAspect02();
	}
	
	@Bean
	public CacheAspect02 cacheAspect() {
		return new CacheAspect02();
	}
	
	@Bean
	public ImpeCalculator impeCalc() {
		return new ImpeCalculator();
	}
	
	@Bean
	public RecCalculator recCalc() {
		return new RecCalculator();
	}
}