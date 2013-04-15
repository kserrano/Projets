package webserverStage3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import javax.xml.ws.Response;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;
import lsr.concurrence.http.HttpResponse;
import lsr.concurrence.http.HttpResponseStream;
import lsr.concurrence.webserver.StaticSite;


public class Task1 extends Task {
	private static Socket s;
	private InputStream is;
	private OutputStream os;
	private BufferOfTasks buffer2;
	public Task1(BufferOfTasks buffer2){
		super(s);	
		this.buffer2 = buffer2;
	}
	
	public Socket getSocket(){
		return s;
	}

	public void run(){
		readRequests();
	}
	public void readRequests(){
	    HttpRequestStream requestStream = new HttpRequestStream(is);
	    PrintStream writer = new PrintStream(os);
		boolean closeconnection = false;
		while(!closeconnection)
		//Step 1 : read a request
		try {
			HttpRequest request = requestStream.readRequest();
			System.out.println("Read request");
			
			// put the Request into the buffer2 for process...
			Task2 task2 = buffer2.putIntoBuffer(t)
			
			// Step 2: generate a matching response
			StaticSite staticS = new StaticSite();
			System.out.println("staticSite created");
			HttpResponse response = staticS.respondTo(request);
			System.out.println("response");
			
			response.writeTo(writer);
	        System.out.println(response.toString());
		        
		       
		} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeconnection = true;
			//e.printStackTrace();
		}
	}

}
