
public class Worker implements Runnable{
	
	BufferOfTasks<Task> buffer;


	public Worker(BufferOfTasks<Task> buffer) {
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
