package def;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class ReadThread implements Runnable {
	
private int bytesRead;
private byte[] buffer = new byte[1024];
private File srcFile;
private Action ac;
boolean turn;

public ReadThread(File srcFile, Action ac) {
	super();
	this.srcFile = srcFile;
	this.ac = ac;
}

@Override
public void run() {
	try (InputStream in = new FileInputStream(srcFile)) {
		
		while((bytesRead = in.read(buffer)) != -1) {
			ac.readData(buffer, bytesRead);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ac.stop = true;
		ac.readData(buffer, -1);
		System.out.println("Data read from file");
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
}
		
		
		

	

}
