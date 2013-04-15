
public class WorkerRead implements Runnable{
	
	BufferOfTasks buffer1;
	BufferOfTasks buffer2;
	

	
	public WorkerRead(BufferOfTasks buffer1,BufferOfTasks buffer2) {
		this.buffer1 = buffer1;
		this.buffer2 = buffer2;

	} 	
	

	public void run(){
		System.out.println("workerRead run");
		while(true){
				Task task = buffer1.readTask();
				Task1 task1 = new Task1(task.getSocket(),null,buffer2);
				task1.run();
		}
	}
	

	
}
