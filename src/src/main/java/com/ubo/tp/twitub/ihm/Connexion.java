package main.java.com.ubo.tp.twitub.ihm;

import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.*;
import controler.ConnexionControler;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class Connexion extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ConnexionControler cConnex;
    User isConnected;
    
    // Vue de la connexion
	public Connexion(ConnexionControler cConnex){

     // Crée les champs de texte et le bouton de connexion
        this.loginField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.loginButton = new JButton("Connexion");
        this.cConnex = cConnex;

        // Crée un panneau pour les champs de texte et le bouton
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1, 5, 5));
        loginPanel.add(new JLabel("Identifiant:"));
        loginPanel.add(loginField);
        loginPanel.add(new JLabel("Mot de passe:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        // Ajoute le panneau au contenu de la fenêtre
        setContentPane(loginPanel);
        loginButton.addActionListener(e -> {
        isConnected = cConnex.verifLogin(loginField.getText(), passwordField.getText());
        if (isConnected != null) {
        	//System.out.println("le user "+isConnected);
        	Window window = SwingUtilities.getWindowAncestor(loginPanel);
            window.dispose();
        }
        });
        
        // Configure la taille et l'emplacement de la fenêtre
        setSize(300, 150);
        setLocationRelativeTo(null);

    }
}
