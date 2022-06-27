package spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client1 implements InitializingBean, DisposableBean{
	private String host;

	public void setHost(String host) { //문자열 객체 주입
		this.host = host;
		System.out.println("Client.setHost() 실행");
	}
	
	public void send() { //실제 사용할 메서드
		System.out.println("Client1.send() to +" + host);
	}

	@Override
	public void destroy() throws Exception { //빈 소멸시 작동할 메서드
		System.out.println("빈 소멸 : Client.destory() 실행");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception { //빈 초기화시 작동할 메서드
		System.out.println("빈 초기화 : Client.afterPropertiesSet() 실행");
	}
}
