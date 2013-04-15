

import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;
import java.net.URISyntaxException;



import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;



public class Task1 extends Task {
	private Socket s;
	private HttpRequest r;
	private InputStream is;
	private BufferOfTasks buffer2;
	public Task1(Socket s,HttpRequest r,BufferOfTasks buffer2){
		super(s,r);	
		this.buffer2 = buffer2;
	}
	
	public void run(){
		readRequests();
	}
	public void readRequests(){
	    HttpRequestStream requestStream = new HttpRequestStream(is);
		boolean closeconnection = false;
		while(!closeconnection)
		//Step 1 : read a request
		try {
			HttpRequest request = requestStream.readRequest();
			System.out.println("Read request");
			
			// put the Request into the buffer2 for process...
			Task2 task2 = new Task2(s,request);
			buffer2.putIntoBuffer(task2);
           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeconnection = true;
			//e.printStackTrace();
		}
	}

}
