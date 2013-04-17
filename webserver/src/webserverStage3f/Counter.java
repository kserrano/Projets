package webserverStage3f;


// TODO: Auto-generated Javadoc
/**
 * The Class Counter.
 */
public class Counter implements BlockingCounter{
	
	/** The value. */
	int value;

/**
 * Instantiates a new counter.
 */
public Counter() {
	// TODO Auto-generated constructor stub
	this.value = 0;
}	
	
	/* (non-Javadoc)
	 * @see webserverStage3f.BlockingCounter#await(int)
	 */
	@Override
	public synchronized void await(int number)  {
		// TODO Auto-generated method stub
		while(value != number){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see webserverStage3f.BlockingCounter#increment()
	 */
	@Override
	public synchronized void increment() {
		// TODO Auto-generated method stub
		value++;
		notifyAll();
	}


}
