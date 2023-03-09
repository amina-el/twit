package controler;

import java.util.HashSet;
import java.util.UUID;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class InscriptionControler {
	
	protected IDatabase mDatabase;
	protected EntityManager mEntityManager;
	

	public InscriptionControler(IDatabase mDatabase, EntityManager mEntityManager) {
		this.mDatabase = mDatabase;
		this.mEntityManager = new EntityManager(mDatabase);
	}
	
	public void inscription(String tag, String nom, String mdp) {
		String users = "C:\\Users\\utilisateur\\Downloads\\src\\src\\users";
		User u = new User(UUID.randomUUID(),tag,mdp,nom,new HashSet<>(),"");
	
		mDatabase.addUser(u);
		mEntityManager.setExchangeDirectory(users);
		mEntityManager.sendUser(u);
	}
	

}
