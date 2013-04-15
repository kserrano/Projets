
public class Worker implements Runnable{
	
	BufferOfTasks buffer;


	public Worker(BufferOfTasks buffer) {
		this.buffer = buffer;
	
	} 	
	

	public void run(){
		System.out.println("worker run");
		while(true){
				Task task = buffer.readTask();
				task.run();
		}
	}
	

	
}
