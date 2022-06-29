package ex00;

public class TestCls implements TestInter {

	@Override
	public void mo() {
		System.out.println("구현된 메서드 실행");
	}
	
	public void mo2() {
		System.out.println("자체 메서드 실행");
	}
}