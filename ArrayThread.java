
public class ArrayThread implements Runnable{

	int array[];
	int start;
	int end;
	long sum = 0;
	
	public ArrayThread(int array[], int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		for(int i = start; i < end; i++) {
			sum += array[i];
		}
		
	}
	
	public long getSum() {
		return sum;
	}

}
