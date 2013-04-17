package webserverStage2;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

// TODO: Auto-generated Javadoc
/**
 * The Class BufferOfTasks.
 *
 * @param <T> the generic type
 */
public class BufferOfTasks<T> {

	/** The buffer size. */
	private final int bufferSize;
	
	/** The available spaces. */
	private final Semaphore availableSpaces;
	
	/** The available items. */
	private final Semaphore availableItems;
	
	/** The mutex. */
	private final Semaphore mutex;


	/** The tasks. */
	private LinkedList<T> tasks;

	/**
	 * Instantiates a new buffer of tasks.
	 *
	 * @param bufferSize the buffer size
	 */
	public BufferOfTasks(int bufferSize) {
		this.bufferSize = bufferSize;
		availableItems = new Semaphore(0, true);
		availableSpaces = new Semaphore(bufferSize, true);
		mutex = new Semaphore(1, true);
		tasks = new LinkedList<T>();
	}

	/**
	 * Gets the buffer size.
	 *
	 * @return the buffer size
	 */
	public int getBufferSize() {
		return bufferSize;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if (availableItems.availablePermits() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is full.
	 *
	 * @return true, if is full
	 */
	public boolean isFull() {
		if (availableSpaces.availablePermits() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Put into buffer.
	 *
	 * @param t the t
	 */
	public void putIntoBuffer(T t) {

		try {
			availableSpaces.acquire();

			mutex.acquire();
			tasks.add(t);
			mutex.release();
			
			availableItems.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read task.
	 *
	 * @return the t
	 */
	public T readTask() {
		T t = null;

		try {
			availableItems.acquire();

			mutex.acquire();
			t = tasks.getFirst();
			tasks.removeFirst();
			mutex.release();
			
			availableSpaces.release();
			System.out.println("end readTask");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return t;
	}

}
