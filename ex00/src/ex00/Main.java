package ex00;

public class Main {

	public static void main(String[] args) {
		//일반적으로 메서드를 사용하는 방법
		TestCls tc = new TestCls();
		tc.mo();
		tc.mo2();
		
		//다형성에 의한 객체 형성
		TestInter ti = new TestCls();
		ti.mo();
		//mo2는 사용할 수 없음
		
		//클래스에 매개변수로 사용
		mo3(new TestCls());
		
		//익명구현클래스 & 익명구현객체(즉석에서 만들어 사용)
		TestInter ti2 = new TestInter() {
			@Override
			public void mo() {
				System.out.println("익명구현객체로 구현된 메서드 실행");
			}
		};
		ti2.mo();
	}

	private static void mo3(TestInter ti) {
		ti.mo();
	}
}