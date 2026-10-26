package def;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FinderThread implements Runnable{
    
	private Path directory;
	private String targetFile;
	private ExecutorService executor;
	private ConcurrentLinkedQueue<Path> foundFiles;
	private AtomicInteger activeTasks;
	

	public FinderThread(Path directory, String targetFile, ExecutorService executor, ConcurrentLinkedQueue<Path> foundFiles, AtomicInteger activeTasks ) {
		super();
		this.directory = directory;
		this.targetFile = targetFile;
		this.executor = executor;
		this.foundFiles = foundFiles;
		this.activeTasks = activeTasks;
	}



	@Override
	public void run() {
		
		try( Stream<Path> stream = Files.list(directory)){
			
			stream.forEach(path ->{
				if(Files.isDirectory(path)) {
					activeTasks.incrementAndGet();
					executor.submit(new FinderThread(path,targetFile,executor,foundFiles,activeTasks));
				}
				else if(Files.isRegularFile(path)) {
					if(path.getFileName().toString().equalsIgnoreCase(targetFile)) {
						foundFiles.add(path);
					}
				
				}
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			activeTasks.decrementAndGet();
		}
	}

}
