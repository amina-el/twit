package controler;

import java.util.UUID;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.core.Twitub;

public class TwitControler {
	protected IDatabase mDatabase;
	protected EntityManager mEntityManager;
	private Twitub controlerTwitub;
	
	public TwitControler(IDatabase mDatabase) {
		this.mDatabase = mDatabase;
		this.mEntityManager = new EntityManager(mDatabase);	
	
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
	
}
