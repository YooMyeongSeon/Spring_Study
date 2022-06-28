package spring.proxy;

import spring.calc.Calculator;

public class ExeTimeCalculator implements Calculator {
	//공통의 기능(시간)을 구현하는 프록시 객체

	private Calculator calc;

	public ExeTimeCalculator(Calculator calc) {
		this.calc = calc;
	}

	@Override
	public long factorial(long num) {
		//long start = System.currentTimeMillis();
		long start = System.nanoTime();
		
		//------------------------------
		
		//핵심코드 : 각 클래스(Calculator를 구현받은 객체)의 핵심 기능
		long result = calc.factorial(num);
		
		//------------------------------
		
		//long end = System.currentTimeMillis();
		long end = System.nanoTime();
		
		System.out.printf("%s.factorial(%d) 실행시간 : %d\n",
				calc.getClass().getSimpleName(), num, (end-start));
		
		return result;
	}
}