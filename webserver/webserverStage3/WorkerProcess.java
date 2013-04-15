

public class WorkerProcess implements Runnable {
	private BufferOfTasks buffer2;
	//Need the BlockingCounter
	public WorkerProcess(BufferOfTasks buffer2){
		this.buffer2 = buffer2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("WorkerProcess run");
		while(true){
			Task t = buffer2.readTask();
			Task2 t2 =  new Task2(t.getSocket(),t.getRequest());
			t2.run();
		}
	}
}
