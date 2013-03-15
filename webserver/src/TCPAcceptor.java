import java.net.ServerSocket;

import lsr.concurrence.http.HttpRequestStream;


public class TCPAcceptor extends Thread {
	ServerSocket serverSocket = new ServerSocket(8080);
	while (true) {

		serverSocket.accept();
	}
}
