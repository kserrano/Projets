import java.io.IOException;
import java.net.ServerSocket;


public class Webserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8080;
		final int BUFFER_SIZE = 5;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("httpServer running on port " + serverSocket.getLocalPort());
		BufferOfTasks buffer = new BufferOfTasks(BUFFER_SIZE);
		TCPAcceptor TCPa = new TCPAcceptor(serverSocket,buffer);
		TCPa.run();
	}

}
