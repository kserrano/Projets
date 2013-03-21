import java.net.ServerSocket;

import lsr.concurrence.http.HttpRequestStream;


public class TCPAcceptor extends Thread {
	ServerSocket serverSocket = new ServerSocket(8080);
	// read HTTP request from stream
	// when a request detected -> creat a worker 
	while (true) {

		serverSocket.accept();
	}
}
