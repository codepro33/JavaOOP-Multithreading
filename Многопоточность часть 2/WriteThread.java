package def;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteThread implements Runnable {

	Action ac;
	File destFile;

	public WriteThread(File destFile, Action ac) {
		super();
		this.ac = ac;
		this.destFile = destFile;
	}

	@Override
	public void run() {

		try (OutputStream out = new FileOutputStream(destFile)) {
			while (true) {

				Action.DataPack data = ac.getData();

				if (data.bytesRead == -1) {
					break;
				}

				out.write(data.buffer, 0, data.bytesRead);
				ac.addBytesWritten(data.bytesRead);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("Data written to file");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
