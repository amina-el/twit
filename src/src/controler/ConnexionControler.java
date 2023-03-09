package controler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.core.Twitub;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.UserObservable;
import main.java.com.ubo.tp.twitub.ihm.UserObserver;

public class ConnexionControler implements UserObservable {
	protected IDatabase mDatabase;
	protected EntityManager mEntityManager;
	private List<UserObserver> observers;
	private User userCourrant;
	

	public ConnexionControler(IDatabase mDatabase, EntityManager mEntityManager) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.observers = new ArrayList<>();
	}

	// connexion
	public User verifLogin(String userName, String password) {

		for (User i : this.mDatabase.getUsers()) {
			// verifie si le couple tag et password sont les memes que dans la base
			if ((i.getUserTag().equals(userName)) && (i.getUserPassword().equals(password))) {
				this.userCourrant = i;
				System.out.println("connecte");
				this.notifyObservers();
				return userCourrant;
			}
		}
		System.out.println("not matching");
		return null;
	}

	//Renvoie le user qui est connecte
	public User getUser() {
		return this.userCourrant;
	}
	
	//Deconnexion
	public void deconnexion() {
		this.userCourrant = null;
		System.out.println("deco");
	}
	
	// Inscription
	// verification du tag existant
	public void inscription(String tag, String nom, String mdp) {
		String users = "C:\\Users\\utilisateur\\Downloads\\src\\src\\users";
		for (User i : this.mDatabase.getUsers()) {
			if(i.getUserTag().equals(tag)) {
				System.out.println("Tag deja utilise");
				return;
			}
		}
		User u = new User(UUID.randomUUID(),tag,mdp,nom,new HashSet<>(),"");
		mDatabase.addUser(u);
		mEntityManager.setExchangeDirectory(users);
		mEntityManager.sendUser(u);
	}
	
	/*
	 * Ajouter un twit dans la bdd
	 * 
	 */
	public void addTwit(User u, String text) {
		String file = "C:\\Users\\utilisateur\\Downloads\\src\\src\\twits";
		long millis = new java.util.Date().getTime();
		Twit twit = new Twit(UUID.randomUUID(),u, millis, text);
		mDatabase.addTwit(twit);
		mEntityManager.setExchangeDirectory(file);
		mEntityManager.sendTwit(twit);
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
