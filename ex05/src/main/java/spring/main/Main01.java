package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.bean.Client1;

public class Main01 {
	
	public static void main(String[] args) {
		//스프링 빈을 생성하지 않고 자바 자체에서 객체를 생성하여 처리
		Client1 c0 = new Client1();
		c0.setHost("웹서버1");
		c0.send();
		
		System.out.println("------------------------------");
		//스프링 빈으로 등록하고 생성
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		
		Client1 c1 = ctx.getBean("c1", Client1.class);
		
		c1.send();
		
		ctx.close(); //빈 소멸		
	}
}