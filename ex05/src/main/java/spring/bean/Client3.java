package spring.bean;

public class Client3 {
	private String host;

	public void setHost(String host) {
		this.host = host;
		System.out.println("Client.setHost() 실행");
	}
	
	public void send() {
		System.out.println("Client2.send() to +" + host);
	}
	
	public void open() throws Exception { //초기화시 사용할 메서드
		System.out.println("빈 초기화 : Client2.open() 실행");
	}
	
	public void close() throws Exception { //소멸시 사용할 메서드
		System.out.println("빈 소멸 : Client2.close() 실행");
	}
}
