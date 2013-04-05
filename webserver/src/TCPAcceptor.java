import java.io.IOException;
import java.io.InputStream;
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
		try {
			socket = serverSocket.accept();
			InputStream is = socket.getInputStream();
	        System.out.println("New connection accepted "
		            + socket.getInetAddress() + ":" + socket.getPort());
			HttpRequestStream httpRequStream = new HttpRequestStream(is);
			Worker worker = new Worker(httpRequStream);
			worker.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	
	// The TCPAcceptor thread waits	for TCP connections from browsers
	
	

}
