package webserverStage3f;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;





// TODO: Auto-generated Javadoc
/**
 * The Class TCPAcceptor2.
 */
public class TCPAcceptor2 extends Thread {

	/** The server socket. */
	private ServerSocket serverSocket;
	
	/** The buffer1. */
	private BufferOfTasks2<Task1> buffer1;
	
	/** The buffer2. */
	private BufferOfTasks2<Task2> buffer2;
	
	/**
	 * Instantiates a new tCP acceptor2.
	 *
	 * @param serverSocket the server socket
	 * @param buffer1 the buffer1
	 * @param buffer2 the buffer2
	 */
	public TCPAcceptor2(ServerSocket serverSocket,BufferOfTasks2<Task1>buffer1, BufferOfTasks2<Task2>buffer2) {
		this.serverSocket = serverSocket;
		this.buffer1 = buffer1;
		this.buffer2 = buffer2;
	}

	/**
	 * Gets the server socket.
	 *
	 * @return the server socket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		System.out.println("TCPa started");
		while(true){
			
			Socket socket = null;

		try {
			socket = serverSocket.accept();
			
	        System.out.println("New connection accepted "
		            + socket.getInetAddress() + ":" + socket.getPort());
	        //Put the task into the buffer
	        Task1 task1 = new Task1(socket,buffer2);
	        buffer1.putIntoBuffer(task1);




		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	

	

}
