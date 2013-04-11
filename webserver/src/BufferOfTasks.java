
import java.util.concurrent.Semaphore;


public class BufferOfTasks {

	private final int bufferSize;
	private final Semaphore availableSpaces;
	private final Semaphore availableItems;
	private int putPosition =0;
	private int getPosition = 0;
	private Task[] tasks;
	public BufferOfTasks(int bufferSize){
		this.bufferSize = bufferSize;
		 availableItems = new Semaphore(0,true);
		 availableSpaces = new Semaphore(bufferSize,true);
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
		try {
			availableSpaces.acquire();
			insert(t);
			availableItems.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Task readTask(){
		Task t=null;
		try {
			availableItems.acquire();
			t = getNextTask();
			availableSpaces.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public void insert(Task t){
		tasks[putPosition] = t;
		if(putPosition+1 == tasks.length){
			putPosition = 0;   // ici il faut bloquer sinon il efface les vieilles tache
		}else{
			putPosition = putPosition++;
		}
	}

	public Task getNextTask(){
		Task t = tasks[getPosition];
		tasks[getPosition]=null;
		if(getPosition+1 == tasks.length){
			getPosition = 0;
		}else{
			getPosition = getPosition++;
		}
		return t;
	}

}
