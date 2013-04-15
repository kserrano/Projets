
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


public class Task2 extends Task {
	private Socket s;
	private InputStream is;
	private OutputStream os;
	private HttpRequest r;
	public Task2(Socket s,HttpRequest r){
		super(s,r);
		
	}
	

	public void run(){
	    PrintStream writer = new PrintStream(os);
		System.out.println("Worker started to process");
		boolean closeconnection = false;
		while(!closeconnection)
		//Step 1 : read a request
		try {
			// recuperation de la requete
			HttpRequest request = this.getRequest();
			
			// Step 2: generate a matching response
			// Need to be synchronized (using monitor)
			
		    HttpResponse response = creatResponse(request);
			response.writeTo(writer);
	        System.out.println(response.toString()); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeconnection = true;
			//e.printStackTrace();
		}
	}
	public HttpResponse creatResponse(HttpRequest r){
		StaticSite staticS;
		HttpResponse response = null;
		try {
			staticS = new StaticSite();
			System.out.println("staticSite created");
			response = staticS.respondTo(r);
			System.out.println("response");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		return response;

		

	}


}
