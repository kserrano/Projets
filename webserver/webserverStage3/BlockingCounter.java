package webserverStage3;

public interface BlockingCounter {
	
	void await(int number);
	void increment();
}
