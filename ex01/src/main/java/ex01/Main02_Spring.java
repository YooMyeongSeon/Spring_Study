package ex01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main02_Spring {

	public static void main(String[] args) { //스프링으로 자바 객체 제어하기
		//1. 스프링 설정 파일 가져오기
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appContext.xml");
		
		//2. t프링이 만든 객체 읽어오기
		Greeter gr = ctx.getBean("greeter", Greeter.class);
		
		//3. 가져온 객체 이용하기
		String msg = gr.greet("스프링");
		System.out.println(msg);
		
		ctx.close();
	}
}	