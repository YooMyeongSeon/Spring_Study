package spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.calc.Calculator;
import spring.config.JavaConfig;

public class Main05 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
		
		Calculator impeCalc = ctx.getBean("impeCalc", Calculator.class);
		long impeResult = impeCalc.factorial(7);
		System.out.println("impeCalc.factorial(7) : " + impeResult);
		
		Calculator recCalc = ctx.getBean("recCalc", Calculator.class);
		long recResult = recCalc.factorial(7);
		System.out.println("recCalc.factorial(7) : " + recResult);
	}
}