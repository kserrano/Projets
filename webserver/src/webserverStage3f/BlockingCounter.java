package webserverStage3f;



// TODO: Auto-generated Javadoc
/**
 * The Interface BlockingCounter.
 */
public interface BlockingCounter {
	
	/**
	 * Await.
	 *
	 * @param number the number
	 */
	void await(int number);
	
	/**
	 * Increment.
	 */
	void increment();
}
