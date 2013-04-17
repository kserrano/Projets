package webserverStage3f;



public class WorkerProcess implements Runnable {
	private BufferOfTasks2<Task2> buffer2;
	//Need the BlockingCounter
	public WorkerProcess(BufferOfTasks2<Task2> buffer2){
		this.buffer2 = buffer2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("WorkerProcess run");
		while(true){
			Task2 t2 = buffer2.readTask();
			System.err.println("Task2 read");
			t2.run();
		}
	}
}
