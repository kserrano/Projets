package webserverStage3f;


import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;
import java.net.URISyntaxException;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;

// TODO: Auto-generated Javadoc
/**
 * The Class Task1.
 */
public class Task1 {
	
	/** The socket. */
	private Socket s;

	/** The inputStream. */
	private InputStream is;
	
	/** The buffer2. */
	private BufferOfTasks2<Task2> buffer2;

	/**
	 * Instantiates a new task1.
	 *
	 * @param s the socket
	 * @param buffer2 the buffer2
	 */
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

	/**
	 * Run.
	 */
	public void run() {
		try {
		HttpRequestStream requestStream = new HttpRequestStream(is);
		int k = 0;

		Counter counter = new Counter();
		while (true) {
			// Step 1 : read a request
			
				HttpRequest request = requestStream.readRequest();

				
				// put the Request into the buffer2 for process...
				Task2 task2 = new Task2(s, request, counter,k); 

				buffer2.putIntoBuffer(task2);
				
				k++;
		}
				// ???
			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}
	

}
