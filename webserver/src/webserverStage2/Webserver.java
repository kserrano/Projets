package webserverStage2;

import java.io.IOException;
import java.net.ServerSocket;


// TODO: Auto-generated Javadoc
/**
 * The Class Webserver.
 */
public class Webserver {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8080;
		final int BUFFER_SIZE = 2;
		final int WORKER_SIZE = 5;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("httpServer running on port " + serverSocket.getLocalPort());
		//Creat buffer of size N
		BufferOfTasks<Task> buffer = new BufferOfTasks<Task>(BUFFER_SIZE);
		//Creat M workers

		for(int i = 0; i<WORKER_SIZE;i++){
			Thread t = new Thread(new Worker(buffer));
			t.start();
		}
		
		TCPAcceptor TCPa = new TCPAcceptor(serverSocket,buffer);
		TCPa.start();
	}

}
