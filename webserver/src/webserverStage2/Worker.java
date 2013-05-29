package webserverStage2;


// TODO: Auto-generated Javadoc
/**
 * The Class Worker.
 */
public class Worker implements Runnable{
	
	/** The buffer. */
	BufferOfTasks<Task> buffer;


	/**
	 * Instantiates a new worker.
	 *
	 * @param buffer the buffer
	 */
	public Worker(BufferOfTasks<Task> buffer) {
		this.buffer = buffer;
	
	} 	
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){

		while(true){
				Task task = buffer.readTask();
				task.run();
		}
	}
	

	
}
