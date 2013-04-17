package webserverStage3f;



// TODO: Auto-generated Javadoc
/**
 * The Class WorkerProcess.
 */
public class WorkerProcess implements Runnable {
	
	/** The buffer2. */
	private BufferOfTasks2<Task2> buffer2;

	/**
	 * Instantiates a new worker process.
	 *
	 * @param buffer2 the buffer2
	 */
	public WorkerProcess(BufferOfTasks2<Task2> buffer2){
		this.buffer2 = buffer2;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while(true){
			Task2 t2 = buffer2.readTask();
			t2.run();
		}
	}
}
