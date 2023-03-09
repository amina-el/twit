package main.java.com.ubo.tp.twitub.ihm;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controler.ConnexionControler;
import controler.InscriptionControler;

public class Inscription extends JFrame{
	private JTextField tagField;
	private JTextField nomField;
    private JPasswordField passwordField;
    private JButton inscriptionButton;
    //private InscriptionControler cInsc;
    private ConnexionControler cInsc;

	public Inscription(ConnexionControler cInsc) {

     // Crée les champs de texte et le bouton de connexion
        this.tagField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.nomField = new JTextField(20);
        this.inscriptionButton = new JButton("Inscription");
        this.cInsc = cInsc;

        // Crée un panneau pour les champs de texte et le bouton
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1, 5, 5));
        loginPanel.add(new JLabel("Identifiant:"));
        loginPanel.add(tagField);
        loginPanel.add(new JLabel("Nom:"));
        loginPanel.add(nomField);
        loginPanel.add(new JLabel("Mot de passe:"));
        loginPanel.add(passwordField);
        loginPanel.add(inscriptionButton);

        // Ajoute le panneau au contenu de la fenêtre
        setContentPane(loginPanel);
        inscriptionButton.addActionListener(e -> cInsc.inscription(tagField.getText(), nomField.getText(), passwordField.getText()));

        // Configure la taille et l'emplacement de la fenêtre
        setSize(300, 150);
        setLocationRelativeTo(null);

    }
}
