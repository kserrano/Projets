import java.io.IOException;
import java.net.Socket;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;
import lsr.concurrence.http.HttpResponse;
import lsr.concurrence.webserver.StaticSite;


public class Worker extends Thread{
	HttpRequest req;
	HttpRequestStream rStream;
	HttpResponse rep;
	Socket socket;
	StaticSite staticS;
	public Worker(Socket sock) {
		socket = sock;
		// TODO Auto-generated constructor stub 
		try {
			rStream = new HttpRequestStream(socket.getInputStream());
			req = rStream.readRequest();
			staticS = req.;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
