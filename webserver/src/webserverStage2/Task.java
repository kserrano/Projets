package webserverStage2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.net.Socket;
import java.net.URISyntaxException;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;
import lsr.concurrence.http.HttpResponse;
import lsr.concurrence.webserver.StaticSite;

// TODO: Auto-generated Javadoc
/**
 * The Class Task.
 */
public class Task {
	
	/** The s. */
	private Socket s;
	
	/** The is. */
	private InputStream is;
	
	/** The os. */
	private OutputStream os;

	/**
	 * Instantiates a new task.
	 *
	 * @param s the s
	 */
	public Task(Socket s) {
		this.s = s;
		try {
			this.is = s.getInputStream();
			this.os = s.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Gets the socket.
	 *
	 * @return the socket
	 */
	public Socket getSocket() {
		return s;
	}

	/**
	 * Run.
	 */
	public void run() {
		try {
			HttpRequestStream requestStream = new HttpRequestStream(is);
			PrintStream writer = new PrintStream(os);

			StaticSite staticS = new StaticSite();
			while (true) {
				// Step 1 : read a request

				HttpRequest request = requestStream.readRequest();
				System.out.println("Read request");

				// Step 2: generate a matching response

				HttpResponse response = staticS.respondTo(request);
				
				// write response
				response.writeTo(writer);


			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
}
