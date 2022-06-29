package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.calc.Calculator;

public class Main07 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx03.xml");
		
		Calculator calc = ctx.getBean("impeCalc", Calculator.class);
		
		calc.factorial(10);
		
		calc.factorial(10);
	}
}