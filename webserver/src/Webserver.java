import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Webserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8080;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("httpServer running on port " + serverSocket.getLocalPort());
		
		TCPAcceptor TCPa = new TCPAcceptor(serverSocket);
		TCPa.run();
	}

}
