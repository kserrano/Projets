import java.io.IOException;
import java.net.Socket;

import lsr.concurrence.http.HttpRequest;
import lsr.concurrence.http.HttpRequestStream;


public class Worker extends Thread{
	HttpRequest req;
	HttpRequestStream rStream;
	Socket socket;
	public Worker(Socket sock) {
		socket = sock;
		// TODO Auto-generated constructor stub 
		try {
			rStream = new HttpRequestStream(socket.getInputStream());
			req = rStream.readRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
