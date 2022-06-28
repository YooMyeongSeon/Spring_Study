package spring.calc;

public class ImpeCalculator implements Calculator {
	//재귀 없이 팩토리얼을 구하는 방법
	
	//동작시간 측정 코드
	//기능
	//핵심기능 : 팩토리얼을 구하는 기능
	//공통기능 : 시간을 구하는 기능
	@Override
	public long factorial(long num) {
		//long start = System.currentTimeMillis();
		
		long result = 1;
		
		for (long i=1; i<=num; i++) {
			result *= i;
		}
		
		//long end = System.currentTimeMillis();
		
		//System.out.printf("ImpeCalculator.factorial(%d) 실행시간 : %d\n", num, (end-start));
		
		return result;
	}
}