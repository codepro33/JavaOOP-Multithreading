package def;

public class CargoUnload implements Runnable{

	int shipNum;
	
	
	
	
	public CargoUnload(int shipNum) {
		super();
		this.shipNum = shipNum;
	}




	@Override
	public void run() {
		for(int i = 1; i<=10; i++) {
			
			System.out.println("Ship number "+shipNum+" unloading box number "+i+"...");
			try {
				Thread.sleep(500*shipNum);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
	
}
