package webserverStage3f;


import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;
import java.net.URISyntaxException;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;

public class Task1 {
	private Socket s;

	private InputStream is;
	private BufferOfTasks2<Task2> buffer2;

	public Task1(Socket s, BufferOfTasks2<Task2> buffer2) {
		this.s= s;
		this.buffer2 = buffer2;
		try {
			this.is=s.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void run() {
		try {
		HttpRequestStream requestStream = new HttpRequestStream(is);
		int k = 0;

		Counter counter = new Counter();
		while (true) {
			// Step 1 : read a request
			
				HttpRequest request = requestStream.readRequest();
				System.out.println("Read request");
				
				// put the Request into the buffer2 for process...
				Task2 task2 = new Task2(s, request, counter,k); 
				System.out.println("K = "+k);
				// pipeline (3ligne) + BlockingCouter

				buffer2.putIntoBuffer(task2);
				k++;
		}
				// ???
			} catch (IOException e) {
				// TODO Auto-generated catch block

				// e.printStackTrace();
			}
		}
	

	public void readRequests() {

	}

}
