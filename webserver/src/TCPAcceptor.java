import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;





public class TCPAcceptor implements Runnable {

	private ServerSocket serverSocket;
	private BufferOfTasks buffer;
	public TCPAcceptor(ServerSocket serverSocket,BufferOfTasks buffer) {
		this.serverSocket = serverSocket;
		this.buffer = buffer;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		while(true){
			Socket socket = null;
			System.out.println("Nombre de boucle Socket = new socket");
		try {
			socket = serverSocket.accept();
			//InputStream is = socket.getInputStream();
			//OutputStream os = socket.getOutputStream();  // ces flux seront utiliser par les workers lorsqu'ils liront le socket dans le buffer
	        System.out.println("New connection accepted "
		            + socket.getInetAddress() + ":" + socket.getPort());
	        //Put the task into the buffer
	        Task task = new Task(socket);
	        buffer.putIntoBuffer(task);
//	        Worker worker = new Worker(buffer);
//	        Thread thread = new Thread(worker);
//			thread.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	
	// The TCPAcceptor thread waits	for TCP connections from browsers
	
	

}
