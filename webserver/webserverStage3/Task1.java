
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
		readRequests();
	}

	public void readRequests() {
		HttpRequestStream requestStream = new HttpRequestStream(is);
		int k = 0;
		boolean closeconnection = false;
		while (!closeconnection) {
			// Step 1 : read a request
			try {
				HttpRequest request = requestStream.readRequest();
				System.out.println("Read request");
				Counter counter = new Counter(1);
				// put the Request into the buffer2 for process...
				Task2 task2 = new Task2(s, request, counter,k++); 
				System.out.println("K = "+k);
				// pipeline (3ligne) + BlockingCouter

				buffer2.putIntoBuffer(task2);
				counter.await(k); // ???
			} catch (IOException e) {
				// TODO Auto-generated catch block
				closeconnection = true;
				// e.printStackTrace();
			}
		}
	}

}
