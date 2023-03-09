package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controler.ConnexionControler;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView implements UserObserver{

	protected IDatabase mDatabase;
	private JFrame frame;
	
	/*/
	 * controlers
	 */
	private ConnexionControler controler;
	

	public TwitubMainView(IDatabase database, EntityManager mEntityManager) {
		this.mDatabase = database;
		this.controler = new ConnexionControler(this.mDatabase, mEntityManager);
		
		frame = new JFrame("Twitub");
		frame.setJMenuBar(menu());
		ImageIcon icon1 = new ImageIcon("src/main/resources/images/logo_20.png");
		JLabel label = new JLabel(icon1);
	    frame.getContentPane().add(label, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);

		
		frame.setLocationRelativeTo(null);
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
		JMenuItem deconnexion = new JMenuItem("Deconnexion");
		profil.add(deconnexion);
		inscription.addActionListener(e -> deconnexion());
		

		// menu twit
		JMenu twit = new JMenu("Twit");
		menuBar.add(twit);
		//sous-menu creation twit
		JMenuItem addTwit = new JMenuItem("Creer Twit");
		twit.add(addTwit);
		addTwit.addActionListener(e -> createTwit());

		return menuBar;
	}
	
	private void deconnexion() {
		this.controler.deconnexion();
	}

	// Panel affichage des infos user
	private Box userPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1));

		// Ajouter les champs texte pour le nom dans un panneau
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nameLabel = new JLabel("Nom:");
		nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		nameLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		namePanel.add(nameLabel);
		JLabel nameValueLabel = new JLabel(controler.getUser().getName());
		nameValueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		nameValueLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		namePanel.add(nameValueLabel);
		panel.add(namePanel);

		// Ajouter les champs texte pour le tag dans un panneau
		JPanel tagPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel tagLabel = new JLabel("Tag:");
		tagLabel.setFont(new Font("Arial", Font.BOLD, 16));
		tagLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		tagPanel.add(tagLabel);
		JLabel tagValueLabel = new JLabel(controler.getUser().getUserTag());
		tagValueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		tagValueLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		tagPanel.add(tagValueLabel);
		panel.add(tagPanel);
		
		Box verticalBox = Box.createVerticalBox();
		Box horizontalBox = Box.createHorizontalBox();

		// Ajouter le panneau à la boîte horizontale
		
		int horizontalMargin = 50;
		horizontalBox.setBorder(new EmptyBorder(0, horizontalMargin, 0, horizontalMargin));
		horizontalBox.add(panel);
		// Ajouter la boîte horizontale à la boîte verticale pour centrer le panneau
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(horizontalBox);
		verticalBox.add(Box.createVerticalGlue());

		return verticalBox;
	}
//	private JPanel twitPanel() {
//		JPanel panel = new JPanel(new GridLayout(4,1));
//
//        // Ajouter les champs texte au panel
//        panel.add(new JLabel("Nom:"));
//        panel.add(new JLabel();
//        
//        panel.add(new JLabel("Tag:"));
//        panel.add(new JLabel(controler.getUser().getUserTag()));
//        
//		return panel;
//	}

	//inscription
	private void inscription() {
//		inscriptionControler = new InscriptionControler(this.mDatabase);
		Inscription inscr = new Inscription(controler);
		inscr.setVisible(true);
	}

	//Connexion
	private void connexion() {
//		this.connexionControler = new ConnexionControler(this.mDatabase);
		this.controler.addObserver(this);
		Connexion co = new Connexion(controler);
		co.setVisible(true);
	}

	private void createTwit() {
//		this.twitControler = new TwitControler(this.mDatabase);
		CreationTwit cTwit = new CreationTwit(controler);
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
		frame.getContentPane().removeAll();
		frame.add(userPanel(), BorderLayout.CENTER);
	}

}
