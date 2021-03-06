package ex01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main03 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appContext.xml");
		
		Greeter gr01 = ctx.getBean("greeter", Greeter.class);
		Greeter gr02 = ctx.getBean("greeter", Greeter.class);
		
		System.out.println("(gr01 == gr02) : " + (gr01 == gr02)); //equals가 아닌 == 연산으로, 값이 아닌 주소값을 비교하게 되는데 주소가 같다.
		
		Greeter gr03 = ctx.getBean("greeter02", Greeter.class);
		
		System.out.println("(gr01 == gr03) : " + (gr01 == gr03)); //다른 객체이므로 주소값이 다르다.
	}
}