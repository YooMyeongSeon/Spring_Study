package spring.calc;

public class RecCalculator implements Calculator {
	//재귀함수를 통해서 팩토리얼을 구하는 방법
	@Override
	public long factorial(long num) {
		//long start = System.currentTimeMillis();
		
		//try {
			if (num == 0) {
				return 1;
			} else {
				return num * factorial(num-1);
			}
		//} finally {
			//long end = System.currentTimeMillis();
			
			//System.out.printf("ImpeCalculator.factorial(%d) 실행시간 : %d\n", num, (end-start));
		//}
	}
}