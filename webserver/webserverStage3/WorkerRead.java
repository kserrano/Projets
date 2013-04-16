
public class WorkerRead implements Runnable{
	
	BufferOfTasks2<Task1> buffer1;

	

	
	public WorkerRead(BufferOfTasks2<Task1> buffer1) {
		this.buffer1 = buffer1;


	} 	
	

	public void run(){
		System.out.println("workerRead run");
		while(true){
				Task1 t1 = buffer1.readTask();
				t1.run();
		}
	}
	

	
}
