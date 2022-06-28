package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.calc.Calculator;

public class Main04 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx02.xml");
		
		Calculator impeCalc = ctx.getBean("impeCalc", Calculator.class);
		long impeResult = impeCalc.factorial(7);
		System.out.println("impeCalc.factorial(7) : " + impeResult);
		
		Calculator recCalc = ctx.getBean("recCalc", Calculator.class);
		long recResult = recCalc.factorial(7);
		System.out.println("recCalc.factorial(7) : " + recResult);
	}
}