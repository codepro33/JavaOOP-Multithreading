package def;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {
		// 1 First TASK HERE
		//            |
		//            |
		//CargoUnload cargo1 = new CargoUnload(1);
		//CargoUnload cargo2 = new CargoUnload(2);
		//CargoUnload cargo3 = new CargoUnload(3);
		
		//Thread t1 = new Thread(cargo1);
		//Thread t2 = new Thread(cargo2);
		//Thread t3 = new Thread(cargo3);
		
		
		//ExecutorService executor  = Executors.newFixedThreadPool(2);
		
		//executor.submit(t1);
		//executor.submit(t2);
		//executor.submit(t3);
		
		//executor.shutdown();
		
		
		
		
		
		
		
		//---------------------------------------------------------------------
		
		
		
		
		
		
		
		
		
		
		// 2 Second TASK HERE
		//			|
		//			|
		//Action ac = new Action();
		//File srcFile = new File("src/def/srcFile.txt");
		//File destFile = new File("src/def/destFile.txt");
		
		//ReadThread rt = new ReadThread(srcFile, ac);
		//WriteThread wt = new WriteThread(destFile, ac);
		//LoadingThread lt = new LoadingThread(ac,srcFile);
		
		//Thread t1 = new Thread(rt);
		//Thread t2 = new Thread(wt);
		//Thread t3 = new Thread(lt);
		
		//t1.start();
		//t2.start();
		//t3.start();
		
		
		
		
		
		
		//--------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		
		
		// 3 Third task here
		//
		//
		
		 ConcurrentLinkedQueue<Path> foundFiles = new ConcurrentLinkedQueue();
		 AtomicInteger activeTasks = new AtomicInteger(0);
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.println("Please enter starting directory");
		 String startPath = sc.nextLine();
		 
		 System.out.println("Please enter file name that you are looking for");
		 String targetFile = sc.nextLine();
		 
		 Path start = Paths.get(startPath);
		 
		 int threadPoolSize = Runtime.getRuntime().availableProcessors();
		 ExecutorService executor  = Executors.newFixedThreadPool(threadPoolSize);
		 

		 activeTasks.incrementAndGet();
		 executor.submit(new FinderThread(start,targetFile,executor, foundFiles, activeTasks));
		
		 for(;activeTasks.get() > 0;) {
			 
			 try {
				Thread.sleep(100);
			 } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			 
			 
		 }
		
		 executor.close();
		 
		 System.out.println("Надено : "+ foundFiles.size()+" файлов");
		 
		 for(Path path: foundFiles ) {
			 System.out.println("Найденные файлы :" + path.toAbsolutePath());
		 }
		 
		 
		
	}

}
