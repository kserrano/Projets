package webserverStage2;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;





// TODO: Auto-generated Javadoc
/**
 * The Class TCPAcceptor.
 */
public class TCPAcceptor extends Thread {

	/** The server socket. */
	private ServerSocket serverSocket;
	
	/** The buffer. */
	private BufferOfTasks<Task> buffer;
	
	/**
	 * Instantiates a new tCP acceptor.
	 *
	 * @param serverSocket the server socket
	 * @param buffer the buffer
	 */
	public TCPAcceptor(ServerSocket serverSocket,BufferOfTasks<Task> buffer) {
		this.serverSocket = serverSocket;
		this.buffer = buffer;
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
	        Task task = new Task(socket);
	        buffer.putIntoBuffer(task);



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	

	

}
