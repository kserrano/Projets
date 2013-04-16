import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;





public class TCPAcceptor2 extends Thread {

	private ServerSocket serverSocket;
	private BufferOfTasks2<Task1> buffer1;
	private BufferOfTasks2<Task2> buffer2;
	public TCPAcceptor2(ServerSocket serverSocket,BufferOfTasks2<Task1>buffer1, BufferOfTasks2<Task2>buffer2) {
		this.serverSocket = serverSocket;
		this.buffer1 = buffer1;
		this.buffer2 = buffer2;
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
			System.out.println("Nombre de boucle Socket = new socket");
		try {
			socket = serverSocket.accept();
			
	        System.out.println("New connection accepted "
		            + socket.getInetAddress() + ":" + socket.getPort());
	        //Put the task into the buffer
	        Task1 task1 = new Task1(socket,buffer2);
	        buffer1.putIntoBuffer(task1);
	        System.out.println("Put task into buffer");
	       // System.out.println("buffer full?"+buffer.isFull());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	

	

}
