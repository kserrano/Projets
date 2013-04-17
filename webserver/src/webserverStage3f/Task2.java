package webserverStage3f;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.net.Socket;
import java.net.URISyntaxException;

import lsr.concurrence.http.HttpRequest;

import lsr.concurrence.http.HttpResponse;

import lsr.concurrence.webserver.StaticSite;

// TODO: Auto-generated Javadoc
/**
 * The Class Task2.
 */
public class Task2 {
	
	/** The s. */
	private Socket s;
	
	/** The is. */
	private InputStream is;
	
	/** The os. */
	private OutputStream os;
	
	/** The r. */
	private HttpRequest r;
	
	/** The c. */
	private Counter c;
	
	/** The k. */
	private int k;

	/**
	 * Instantiates a new task2.
	 *
	 * @param s the s
	 * @param r the r
	 * @param c the c
	 * @param k the k
	 */
	public Task2(Socket s, HttpRequest r, Counter c, int k) {
		this.s = s;
		this.r = r;
		try {
			this.os = s.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.c = c;
		this.k = k;
	}

	/**
	 * Run.
	 */
	public void run() {
		PrintStream writer = new PrintStream(os);


		// Step 2: generate a matching response


		try {
			StaticSite staticS = new StaticSite();

			HttpResponse response = staticS.respondTo(r);

			c.await(k); 
			response.writeTo(writer);
			c.increment();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}


	}

}
