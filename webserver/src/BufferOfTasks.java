import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class BufferOfTasks {

	private int bufferSize;
	
	public BufferOfTasks(int bufferSize){
		this.bufferSize = bufferSize;
	}
	
	public int getBufferSize(){
		return bufferSize;
	}
	// rendre ces deux methodes blocantes (semaphores)
	public void putIntoBuffer(Task t){

		
	}
	
	public Task readTask(){
		Task t = null;
		return t;
	}

}
