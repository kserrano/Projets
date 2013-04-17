import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;





public class TCPAcceptor extends Thread {

	private ServerSocket serverSocket;
	private BufferOfTasks<Task> buffer;
	public TCPAcceptor(ServerSocket serverSocket,BufferOfTasks<Task> buffer) {
		this.serverSocket = serverSocket;
		this.buffer = buffer;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}


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
	        System.out.println("Put task into buffer");
	       // System.out.println("buffer full?"+buffer.isFull());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	

	

}
