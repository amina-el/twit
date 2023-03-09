package main.java.com.ubo.tp.twitub.ihm;

import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.UUID;

import main.java.com.ubo.tp.twitub.datamodel.User;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controler.ConnexionControler;
import controler.TwitControler;

public class CreationTwit extends JFrame{
	
	//private TwitControler twitControler;
	private ConnexionControler twitControler;
	private final int MAX_MESSAGE_LENGTH = 250;
    private JLabel messageLabel;
    private JTextArea messageTextArea;
    private JButton sendButton;
    String pass = "test";
    String nom = "test";
    
	
	public CreationTwit(ConnexionControler cTwit) {
		this.twitControler = cTwit;
		setLayout(new BorderLayout());

        // Ajouter le label "Message"
        messageLabel = new JLabel("Message:");
        add(messageLabel, BorderLayout.NORTH);

        // Ajouter le champ de texte
        messageTextArea = new JTextArea(10, 10);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        add(new JScrollPane(messageTextArea), BorderLayout.CENTER); 

        // Ajouter le bouton "Envoyer"
        sendButton = new JButton("Envoyer");
        add(sendButton, BorderLayout.SOUTH);
        User ulo = twitControler.getUser();
        //User cuser = new User(UUID.randomUUID(),null,pass, nom,new HashSet<>(),"");
        System.out.println("ulo: " + ulo);
        sendButton.addActionListener(e -> twitControler.addTwit(ulo, messageTextArea.getText() ));
	}
}
