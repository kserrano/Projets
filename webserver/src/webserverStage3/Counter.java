
public class Counter implements BlockingCounter{
	int value;
public Counter() {
	// TODO Auto-generated constructor stub
	this.value = 0;
}	
	@Override
	public synchronized void await(int number)  {
		// TODO Auto-generated method stub
		while(value != number){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void increment() {
		// TODO Auto-generated method stub
		value++;
		notifyAll();
	}


}
