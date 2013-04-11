import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;
import lsr.concurrence.http.HttpResponse;
import lsr.concurrence.http.HttpResponseStream;
import lsr.concurrence.webserver.StaticSite;


public class Task {
	private Socket s;
	public Task(Socket s){
		this.s = s;
	}
	
	public Socket getSocket(){
		return s;
	}

public void run(){
	Socket socket  = this.getSocket();
	InputStream is = null;		

	OutputStream os = null;

	try {
		is = socket.getInputStream();
		os = socket.getOutputStream();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	HttpRequestStream requestStream = new HttpRequestStream(is);
	HttpResponseStream responseStream = new HttpResponseStream(is); // check here for Response Stream
	System.out.println("Worker started to process");
	while(true)
	//Step 1 : read a request
	try {
		HttpRequest request = requestStream.readRequest();
		System.out.println("Read request");
		
		// Step 2: generate a matching response
		StaticSite staticS = new StaticSite();

		try {
			HttpResponse response = staticS.respondTo(request);
			// Step 3 : write the response into the socket
			PrintWriter printOut = new PrintWriter(os, true);
	        printOut.write(response.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
