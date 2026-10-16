import java.math.BigInteger;

public class ThreadFactorial implements Runnable {

	@Override
	public void run() {
		BigInteger factorial = BigInteger.ONE;
		for(int i = 1; i <= Thread.currentThread().threadId(); i++) {
	       factorial = factorial.multiply(BigInteger.valueOf(i));
	       
		}
		
		System.out.println("Factorial of " + Thread.currentThread().threadId() + " is: " + factorial);
	}

	
	
}
