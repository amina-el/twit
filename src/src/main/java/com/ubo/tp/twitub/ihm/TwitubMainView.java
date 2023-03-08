package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.*;
import controler.ConnexionControler;
import controler.InscriptionControler;
import controler.TwitControler;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView implements UserObserver{
	static String couser;
	protected IDatabase mDatabase;
	private JFrame frame;
	/*/
	 * controlers
	 */
	private ConnexionControler connexionControler;
	private InscriptionControler inscriptionControler;
	private TwitControler twitControler;

	public TwitubMainView(IDatabase database) {
		this.mDatabase = database;
		frame = new JFrame("Twitub");
		frame.setJMenuBar(menu());

		ImageIcon icon1 = new ImageIcon("src/main/resources/images/logo_20.png");
		JLabel label = new JLabel(icon1);
	    frame.getContentPane().add(label, BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);


	}

	private JMenuBar menu() {
		JMenuBar menuBar = new JMenuBar();

		// fichier
		JMenu menu = new JMenu("Fichier");
		menuBar.add(menu);

		//open file chooser
		JMenuItem chooseFile = new JMenuItem("Load File");
		menu.add(chooseFile);
		chooseFile.addActionListener(e -> loadFile());

		// sous-menu de fichier, quitter la frame
		JMenuItem menuItem = new JMenuItem("Quitter");
		menu.add(menuItem);

		//item quit app
		menuItem.addActionListener(e -> System.exit(0));
		//add icone to menu
		ImageIcon icon = new ImageIcon("src/main/resources/images/exitIcon_20.png");
		menuItem.setIcon(icon);

		//A propos
		JMenu aPropos = new JMenu("?");
		menuBar.add(aPropos);
		//open dialogbox about app
		JMenuItem about = new JMenuItem("A propos");
		aPropos.add(about);
		about.addActionListener(e -> about());

		//vue profil
		JMenu profil = new JMenu("Profil");
		menuBar.add(profil);
		//sous-menu Connexion
		JMenuItem connexion = new JMenuItem("Connexion");
		profil.add(connexion);
		connexion.addActionListener(e -> connexion());

		//sous-menu Inscription
		JMenuItem inscription = new JMenuItem("Inscription");
		profil.add(inscription);
		inscription.addActionListener(e -> inscription());

		// menu twit
		JMenu twit = new JMenu("Twit");
		menuBar.add(twit);
		//sous-menu creation twit
		JMenuItem addTwit = new JMenuItem("Creer Twit");
		twit.add(addTwit);
		addTwit.addActionListener(e -> createTwit());

		return menuBar;
	}
	
	// Panel affichage des infos user
	private JPanel userPanel() {
		JPanel panel = new JPanel(new GridLayout(4,1));

        // Ajouter les champs texte au panel
        panel.add(new JLabel("Nom:"));
        panel.add(new JLabel(connexionControler.getUser().getName()));
        
        panel.add(new JLabel("Tag:"));
        panel.add(new JLabel(connexionControler.getUser().getUserTag()));
        
		return panel;
	}

	//inscription
	private void inscription() {
//		inscriptionControler = new InscriptionControler(this.mDatabase);
		Inscription inscr = new Inscription(inscriptionControler);
		inscr.setVisible(true);
	}

	//Connexion
	private void connexion() {
//		this.connexionControler = new ConnexionControler(this.mDatabase);
		this.connexionControler.addObserver(this);
		Connexion co = new Connexion(connexionControler);
		co.setVisible(true);
	}

	private void createTwit() {
//		this.twitControler = new TwitControler(this.mDatabase);
		CreationTwit cTwit = new CreationTwit(twitControler);
		cTwit.setVisible(true);
	}

	//add fileChooser box
	private Object loadFile() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showOpenDialog(frame);

		if (result == JFileChooser.APPROVE_OPTION) {
			// Récupération du répertoire sélectionné
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Répertoire sélectionné : " + selectedFile.getAbsolutePath());
		}

		return null;
	}
	

	//add dialogbox about app
	public void about() {
		JLabel label = new JLabel("<html><div style='text-align: center;'>UBO M2-TIIL<br/>Département infromatique<br/></div></html>");

		ImageIcon icon = new ImageIcon("src/main/resources/images/logo_50.jpg");

		JOptionPane.showMessageDialog(null, label, "A propos", JOptionPane.INFORMATION_MESSAGE, icon);
	}

	@Override
	public void update(UserObservable observable) {
		System.out.println("update");
		frame.add(userPanel(), BorderLayout.CENTER);
		
	}

}
