package main.java.com.ubo.tp.twitub.ihm;

import controler.ConnexionControler;
import controler.InscriptionControler;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

public class ProfilView implements UserObserver {
	
	private ConnexionControler connexionControler;
	private InscriptionControler inscriptionControler;
	private IDatabase mDatabase;

	private void connexion() {
		this.connexionControler = new ConnexionControler(this.mDatabase);
		this.connexionControler.addObserver(this);
		Connexion co = new Connexion(connexionControler);

		co.setVisible(true);
	}
	//inscription
	private void inscription() {
		inscriptionControler = new InscriptionControler(this.mDatabase);
		Inscription inscr = new Inscription(inscriptionControler);
		inscr.setVisible(true);
	}
	@Override
	public void update(UserObservable observable) {
		// TODO Auto-generated method stub
		
	}


}
