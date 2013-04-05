import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import lsr.concurrence.http.HttpRequestStream;


public class TCPAcceptor implements Runnable {

	private ServerSocket serverSocket;

	public TCPAcceptor(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
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
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
	        System.out.println("New connection accepted "
		            + socket.getInetAddress() + ":" + socket.getPort());

			Worker worker = new Worker(is,os);
			worker.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	
	// The TCPAcceptor thread waits	for TCP connections from browsers
	
	

}
