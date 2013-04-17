package webserverStage2;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class BufferOfTasks<T> {

	private final int bufferSize;
	private final Semaphore availableSpaces;
	private final Semaphore availableItems;
	private final Semaphore mutex;


	private LinkedList<T> tasks;

	public BufferOfTasks(int bufferSize) {
		this.bufferSize = bufferSize;
		availableItems = new Semaphore(0, true);
		availableSpaces = new Semaphore(bufferSize, true);
		mutex = new Semaphore(1, true);
		tasks = new LinkedList<>();
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
			mutex.acquire();
			tasks.add(t);
//			mutex.acquire();
//			nbItemIntoBuffer++;
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
			System.out.println("begin readTask");
			mutex.acquire();
			t = tasks.getFirst();
			tasks.removeFirst();
//			mutex.acquire();
//			nbItemIntoBuffer--;
//			mutex.release();
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
