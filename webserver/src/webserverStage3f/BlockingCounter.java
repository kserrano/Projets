package webserverStage3f;



public interface BlockingCounter {
	
	void await(int number);
	void increment();
}
