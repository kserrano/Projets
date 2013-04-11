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


public class Worker implements Runnable{
	
	BufferOfTasks buffer;


	public Worker(BufferOfTasks buffer) {
		this.buffer = buffer;

	} 	
	

	public void run(){
		Task task = buffer.readTask();
		task.run();
		
	}
	

	
}
