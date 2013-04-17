package webserverStage3f;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class BufferOfTasks2<T> {

	private final int bufferSize;
	private final Semaphore availableSpaces;
	private final Semaphore availableItems;
	private final Semaphore mutex;

	private int nbItemIntoBuffer = 0;
	private LinkedList<T> tasks;

	public BufferOfTasks2(int bufferSize) {
		this.bufferSize = bufferSize;
		availableItems = new Semaphore(0, true);
		availableSpaces = new Semaphore(bufferSize, true);
		mutex = new Semaphore(1, true);
		tasks = new LinkedList<T>();
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public boolean isEmpty() {
		if (availableItems.availablePermits() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFull() {
		if (availableSpaces.availablePermits() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void putIntoBuffer(T t) {

		try {
			availableSpaces.acquire();
			System.out.println("Start deposit");
			tasks.add(t);
			mutex.acquire();
			nbItemIntoBuffer++;
			mutex.release();
			availableItems.release();
			System.out.println("End deposit");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public T readTask() {
		T t = null;

		try {
			availableItems.acquire();
			t = tasks.getFirst();
			tasks.removeFirst();
			System.out.println("begin readTask");
			mutex.acquire();
			nbItemIntoBuffer--;
			mutex.release();
			availableSpaces.release();
			System.out.println("end readTask");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
