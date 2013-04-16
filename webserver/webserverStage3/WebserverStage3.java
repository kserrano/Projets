

import java.io.IOException;
import java.net.ServerSocket;

public class WebserverStage3 {

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
			//Creat buffers of size N
			BufferOfTasks2<Task1> buffer1 = new BufferOfTasks2<Task1>(BUFFER_SIZE);
			BufferOfTasks2<Task2> buffer2 = new BufferOfTasks2<Task2>(BUFFER_SIZE);
			//Creat M workers

			for(int i = 0; i<WORKER_SIZE;i++){
				Thread t = new Thread(new WorkerRead(buffer1));
				Thread t2 = new Thread(new WorkerProcess(buffer2));
				System.out.println("Workers "+ (i+1) +" created");
				t.start();
				t2.start();
				System.out.println("Workers "+(i+1)+" running");
			}
			
			TCPAcceptor2 TCPa = new TCPAcceptor2(serverSocket,buffer1,buffer2);
			TCPa.start();
		}

	}