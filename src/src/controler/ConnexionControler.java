package controler;

import java.util.ArrayList;
import java.util.List;

import main.java.com.ubo.tp.twitub.core.Twitub;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.UserObservable;
import main.java.com.ubo.tp.twitub.ihm.UserObserver;

public class ConnexionControler implements UserObservable {
	protected IDatabase mDatabase;
	private List<UserObserver> observers;
	private User u;
	

	public ConnexionControler(IDatabase mDatabase) {
		this.mDatabase = mDatabase;
		this.observers = new ArrayList<>();
	}

	// Verification pour connexion
	public User verifLogin(String userName, String password) {
		for (User i : this.mDatabase.getUsers()) {

			// verifie si le couple tag et password sont les memes que dans la base
			if ((i.getUserTag().equals(userName)) && (i.getUserPassword().equals(password))) {
				this.u = i;
				System.out.println("connecte");
				this.notifyObservers();
				return u;
			}
		}
		System.out.println("not matching");
		return null;
	}

	//Renvoie le user qui est connecte
	public User getUser() {
		return this.u;
	}
	
	@Override
	public void addObserver(UserObserver observer) {
		this.observers.add(observer);

	}

	@Override
	public void removeObserver(UserObserver observer) {
		this.observers.remove(observer);

	}

	@Override
	public void notifyObservers() {
		System.out.println("notify");
		for (UserObserver o : this.observers) {
			o.update(this);
		}
	}

}
