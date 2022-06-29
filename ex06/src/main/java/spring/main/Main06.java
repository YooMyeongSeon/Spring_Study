package spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.calc.Calculator;
import spring.config.JavaConfig;

public class Main06 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
		
		Calculator calc = ctx.getBean("impeCalc", Calculator.class);
		
		calc.factorial(10);
		
		calc.factorial(10);
	}
}
