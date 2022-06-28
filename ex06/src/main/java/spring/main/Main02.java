package spring.main;

import spring.calc.ImpeCalculator;
import spring.calc.RecCalculator;
import spring.proxy.ExeTimeCalculator;

public class Main02 {

	public static void main(String[] args) {
		//프록시 객체를 거쳐 팩토리얼을 구한다.
		ExeTimeCalculator impeCal = new ExeTimeCalculator(new ImpeCalculator());
		long impeResult = impeCal.factorial(7);	
		
		ExeTimeCalculator recCal = new ExeTimeCalculator(new RecCalculator());
		long recResult = recCal.factorial(7);
		
		System.out.println("impeResult의 계산 결과 : " + impeResult);
		System.out.println("recResult의 계산 결과 : " + recResult);
	}
}