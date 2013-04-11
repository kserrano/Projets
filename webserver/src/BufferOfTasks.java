
import java.util.concurrent.Semaphore;


public class BufferOfTasks {

	private final int bufferSize;
	private final Semaphore availableSpaces;
	private final Semaphore availableItems;
	private final Semaphore mutex;
	private int putPosition =0;
	private int getPosition = 0;
	private int nbItemIntoBuffer = 0;
	private Task[] tasks;
	public BufferOfTasks(int bufferSize){
		this.bufferSize = bufferSize;
		 availableItems = new Semaphore(0,true);
		 availableSpaces = new Semaphore(bufferSize,true);
		mutex = new Semaphore(1,true);
		 tasks = new Task[bufferSize];
	}
	
	public int getBufferSize(){
		return bufferSize;
	}
	public boolean isEmpty(){
		if(availableItems.availablePermits()==0){
			return true;
		}else{
			return false;
		}
	}
	public boolean isFull(){
		if(availableSpaces.availablePermits() ==0){
			return true;
		}else{
			return false;
		}
	}
	// rendre ces deux methodes blocantes (semaphores)
	public void putIntoBuffer(Task t){
		System.out.println("Start deposit");
		try {
			availableSpaces.acquire();
//			mutex.acquire();
			//insert(t);
			tasks[putPosition]=t;
			putPosition = (putPosition+1)% bufferSize;
			mutex.acquire();
			nbItemIntoBuffer++;
			mutex.release();
			//tasks[putPosition] = t;
			//putPosition = putPosition+1;
			availableItems.release();
//			mutex.release();
			System.out.println("End deposit");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Task readTask(){
		Task t=null;
		System.out.println("begin readTask");
		try {
			availableItems.acquire();
//			mutex.acquire();
//			t = getNextTask();
//			mutex.release();
			t = tasks[getPosition];
			getPosition = (getPosition+1)% bufferSize;
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
