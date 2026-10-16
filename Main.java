
public class Main {

	public static void main(String[] args) {
		int size = 10_000_000;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
		
        long startTime = System.nanoTime();
        
        int third = size / 3;
        
		ArrayThread arrayThread1 = new ArrayThread(arr, 0, third);
		ArrayThread arrayThread2 = new ArrayThread(arr, third, third*2);
		ArrayThread arrayThread3 = new ArrayThread(arr, third*2, size);
	
		Thread t1 = new Thread(arrayThread1);
		Thread t2 = new Thread(arrayThread2);
		Thread t3 = new Thread(arrayThread3);
		
		
		
		t1.start();
		t2.start();
		t3.start();
		
		
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		
		long multiThreadSum = arrayThread1.getSum() + arrayThread2.getSum() + arrayThread3.getSum();
		
        long endTime = System.nanoTime();
		
		long executionTime = endTime - startTime;
		
		System.out.println("Sum of array elements, multi thread: " + multiThreadSum + " Execution time: " + executionTime + " ms");
		
		long singleThreadSum = 0;
		
		long startTimeSingleThread = System.nanoTime();
		for(int i =0; i<arr.length; i++) {
			singleThreadSum += arr[i];
		}
		
		long endTimeSingleThread = System.nanoTime();
		long executionTimeSingleThread = endTimeSingleThread - startTimeSingleThread;
		
		System.out.println("Sum of array elements, one thread: " + singleThreadSum + " Execution time: " + executionTimeSingleThread + " ms");
		
		

		//for(int i = 0; i <= 100; i++) //{
		//Thread thread = new Thread(new ThreadFactorial());
		//thread.start();
		//}
	}

}
