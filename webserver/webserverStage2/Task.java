
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


public class Task {
	private Socket s;
	private InputStream is;
	private OutputStream os;

	public Task(Socket s){
		this.s = s;
		try {
			this.is = s.getInputStream();
			this.os = s.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Socket getSocket(){
		return s;
	}


	public void run(){

		HttpRequestStream requestStream = new HttpRequestStream(is);
	    PrintStream writer = new PrintStream(os);
		System.out.println("Worker started to process");
		boolean closeconnection = false;
		while(!closeconnection)
		//Step 1 : read a request
		try {
			HttpRequest request = requestStream.readRequest();
			System.out.println("Read request");
			
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
