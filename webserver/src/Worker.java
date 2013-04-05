import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;
import lsr.concurrence.http.HttpResponse;
import lsr.concurrence.webserver.StaticSite;


public class Worker extends Thread{
	HttpRequestStream requestStream;

	public Worker(HttpRequestStream requestStream) {
		super();
		this.requestStream = requestStream;
	} 	
	
	public void start(){
		System.out.println("Worker started");
	}
	
//	HttpRequest req;
//	HttpRequestStream rStream;
//	HttpResponse rep;
//	Socket socket;
//	public Worker(Socket sock) {
//		socket = sock;
//		// TODO Auto-generated constructor stub 
//		try {
//
//			StaticSite staticS = new StaticSite();
//			rStream = new HttpRequestStream(socket.getInputStream());
//			req = rStream.readRequest();
//			rep = staticS.respondTo(req);
//			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//	        out.write(rep.toString());
//	        out.flush();
//						
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
	
}
