package ex00;

public class Main {

	public static void main(String[] args) {
		//�Ϲ������� �޼��带 ����ϴ� ���
		TestCls tc = new TestCls();
		tc.mo();
		tc.mo2();
		
		//�������� ���� ��ü ����
		TestInter ti = new TestCls();
		ti.mo();
		//mo2�� ����� �� ����
		
		//Ŭ������ �Ű������� ���
		mo3(new TestCls());
		
		//�͸���Ŭ���� & �͸�����ü(�Ｎ���� ����� ���)
		TestInter ti2 = new TestInter() {
			@Override
			public void mo() {
				System.out.println("�͸�����ü�� ������ �޼��� ����");
			}
		};
		ti2.mo();
	}

	private static void mo3(TestInter ti) {
		ti.mo();
	}
}