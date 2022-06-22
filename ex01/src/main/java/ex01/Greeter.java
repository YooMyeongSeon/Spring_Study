package ex01;

public class Greeter { //자바에서 클래스를 사용하는 구조
	private String format; //사용할 필드

	public void setFormat(String format) {
		this.format = format;
	}
	
	public String greet(String name) { //사용할 메서드
		return String.format(format, name);
	}
}