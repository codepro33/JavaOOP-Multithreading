package def;
import java.io.File;

public class LoadingThread implements Runnable {
    private File srcFile;
    private Action ac;

    public LoadingThread(Action ac, File srcFile) {
        this.ac = ac;
        this.srcFile = srcFile;
       
        
        this.ac.setTotalFileSize(srcFile.length());
    }

    @Override
    public void run() {
        int lastPrinted = -1;

        while (true) {
            int progress = ac.getProgressPercentage();

            
            if (progress != lastPrinted) {
                System.out.println("Loading " + progress + "%");
                lastPrinted = progress;
            }

            if (progress >= 100) {
                System.out.println("Loading Complete!");
                break;
            }

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
