package ex01;

public class Main01_Java {

	public static void main(String[] args) { //스프링을 사용하지 않는 자바 예제
		Greeter gr = new Greeter(); //자바 코드는 new 연산을 이용하여 객체를 생성
		gr.setFormat("%s, 반갑습니다.");
		
		String msg = gr.greet("홍길동");
		
		System.out.println(msg);
	}
}