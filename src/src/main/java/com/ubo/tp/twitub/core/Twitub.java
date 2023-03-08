package main.java.com.ubo.tp.twitub.core;

import java.io.File;

import controler.ConnexionControler;
import controler.InscriptionControler;
import controler.TwitControler;
import main.java.com.ubo.tp.twitub.datamodel.ConsoleObserver;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.events.file.IWatchableDirectory;
import main.java.com.ubo.tp.twitub.events.file.WatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.TwitObservable;
import main.java.com.ubo.tp.twitub.ihm.TwitObserver;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.TwitubMock;
import main.java.com.ubo.tp.twitub.ihm.UserObservable;
import main.java.com.ubo.tp.twitub.ihm.UserObserver;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitub implements UserObserver, TwitObserver{
	
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitubMainView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = true;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;
	
	protected ConnexionControler connexionControler;
	
	protected InscriptionControler inscriptionControler;
	
	protected TwitControler twitControler;

	
	/**
	 * Constructeur.
	 */
	public Twitub() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}

		// Initialisation de l'IHM
		//this.initGui();

		// Initialisation du répertoire d'échange
		this.initDirectory();
		
		this.connexionControler = new ConnexionControler(this.mDatabase);
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		// this.mMainView...
		this.mMainView = new TwitubMainView(mDatabase);
		this.connexionControler = new ConnexionControler(mDatabase);
		this.inscriptionControler = new InscriptionControler(mDatabase);
		this.twitControler = new TwitControler(mDatabase);
		
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
	}

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 * 
	 * @param directory
	 *            , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitubMock mock = new TwitubMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
		ConsoleObserver mConsoleObserver = new ConsoleObserver();
		mDatabase.addObserver(mConsoleObserver);
	}

	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}
	
	public User userLoggedIn() {
		User u = connexionControler.getUser();
		return u;
	}

	public void show() {
		initGui();
		
		// ... setVisible?
	}

	@Override
	public void update(UserObservable observable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TwitObservable observable) {
		// TODO Auto-generated method stub
		
	}
}
