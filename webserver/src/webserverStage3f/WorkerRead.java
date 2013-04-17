package webserverStage3f;


// TODO: Auto-generated Javadoc
/**
 * The Class WorkerRead.
 */
public class WorkerRead implements Runnable{
	
	/** The buffer1. */
	BufferOfTasks2<Task1> buffer1;

	

	
	/**
	 * Instantiates a new worker read.
	 *
	 * @param buffer1 the buffer1
	 */
	public WorkerRead(BufferOfTasks2<Task1> buffer1) {
		this.buffer1 = buffer1;


	} 	
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){

		while(true){
				Task1 t1 = buffer1.readTask();

				t1.run();
		}
	}
	

	
}
