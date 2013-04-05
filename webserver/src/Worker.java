import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;
import lsr.concurrence.http.HttpResponse;
import lsr.concurrence.http.HttpResponseStream;
import lsr.concurrence.webserver.StaticSite;


public class Worker extends Thread{
	InputStream is;
	OutputStream os;

	public Worker(InputStream is, OutputStream os) {
		super();
		this.is=is;
		this.os = os;

	} 	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	public void start(){
		HttpRequestStream requestStream = new HttpRequestStream(is);
		HttpResponseStream responseStream = new HttpResponseStream(is);
		System.out.println("Worker started");
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
