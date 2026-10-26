package def;

public class Action {
	
	
    private byte[] buffer = new byte[1024];
    private int bytesRead;
	private boolean hasData;
	public boolean stop = false;
	public int counter = 0;
	private long totalBytesWritten = 0;
	private long totalFileSize = 0;
	
	
	public Action() {
		hasData = false;
	}
	
	
	public synchronized void readData(byte buffer[], int bytesRead) {
		for(;hasData==true;) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
		this.buffer = buffer;
		this.bytesRead = bytesRead;
		this.hasData = true;
		this.counter++;
		notify();
	}
	
	public synchronized DataPack getData() {
		for(;hasData==false;) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
		DataPack data = new DataPack(this.buffer, this.bytesRead);
		this.hasData = false;
		notify();
		this.counter++;
		return data;
		
	}
	
	public synchronized void setTotalFileSize(long size) {
	    this.totalFileSize = size;
	}

	public synchronized void addBytesWritten(int bytesRead) {
	    if (bytesRead > 0) {
	        this.totalBytesWritten += bytesRead;
	    }
	}

	public synchronized int getProgressPercentage() {
	    if (totalFileSize == 0) return 0;
	    return (int) ((double) totalBytesWritten / totalFileSize * 100);
	}
	
	public static class DataPack {
		
		public final byte[] buffer;
		public final int bytesRead;
		
		public DataPack(byte[] buffer, int bytesRead) {
			super();
			this.buffer = buffer;
			this.bytesRead = bytesRead;
		}
		
		
		
	}
	
	


	
}
