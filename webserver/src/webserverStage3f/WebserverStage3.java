package webserverStage3f;



import java.io.IOException;
import java.net.ServerSocket;

// TODO: Auto-generated Javadoc
/**
 * The Class WebserverStage3.
 */
public class WebserverStage3 {

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
			//Creat buffers of size N
			BufferOfTasks2<Task1> buffer1 = new BufferOfTasks2<Task1>(BUFFER_SIZE);
			BufferOfTasks2<Task2> buffer2 = new BufferOfTasks2<Task2>(BUFFER_SIZE);
			//Creat M workers

			for(int i = 0; i<WORKER_SIZE;i++){
				Thread t = new Thread(new WorkerRead(buffer1));
				Thread t2 = new Thread(new WorkerProcess(buffer2));
				t.start();
				t2.start();

			}
			
			TCPAcceptor2 TCPa = new TCPAcceptor2(serverSocket,buffer1,buffer2);
			TCPa.start();
		}

	}
