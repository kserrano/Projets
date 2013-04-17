package webserverStage3f;

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

public class Task2 {
	private Socket s;
	private InputStream is;
	private OutputStream os;
	private HttpRequest r;
	private Counter c;
	private int k;

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

	public void run() {
		PrintStream writer = new PrintStream(os);
		System.out.println("Worker started to process");

		// Step 2: generate a matching response
		// Need to be synchronized (using monitor)

		try {
			StaticSite staticS = new StaticSite();
			System.out.println("staticSite created");
			HttpResponse response = staticS.respondTo(r);
			System.out.println("response");
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

		System.out.println(r.toString());

	}

}
