package main.java.com.ubo.tp.twitub.ihm;

public interface TwitObservable {
	
	void addObserver(TwitObserver observer);
    void removeObserver(TwitObserver observer);
    
    void notifyObservers();
}
