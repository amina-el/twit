package main.java.com.ubo.tp.twitub.ihm;

public interface UserObservable {
	
	void addObserver(UserObserver observer);
    void removeObserver(UserObserver observer);
    
    void notifyObservers();

}
