package usmb.info405.grp1.app.connexion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.ApplicationFrame;
import usmb.info405.grp1.app.utilitaire.DataApi;

//Nota dans l'ordre d'ajout des coposants ont une importance. 
public class ConnexionPanel extends JPanel {
	
	private static final long serialVersionUID = 6000603855835527414L;
	private JLabel errorLabel = new JLabel("Identifiant ou mot de passe invalide");
	
	//Attribut classe utilitaire 
	//	private DataApi utilitaire;
	//constructeur
	public ConnexionPanel(ApplicationFrame frame) {
		
		//this.utilitaire = utilitaire;
		this.frame = frame;
		init();
	}

	private void init() {
		setLayout(new MigLayout("fill,insets 20","[center]","[center]")); //Le panel de connexion vis à vis de la fenêtre
		
		//Init des éléments et modification de leur propriétées
		txtUsername = new JTextField();
		txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre identifiant");
		
		txtPassword = new JPasswordField();
		txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre mot de passe");
		txtPassword.putClientProperty(FlatClientProperties.STYLE,""+
											"showRevealButton:true");
		
		cmdLogin = new JButton("Connexion");
		
		//Initialisation du panel de formulaire.
		JPanel formPanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45","fill,250:280"));
		formPanel.putClientProperty(FlatClientProperties.STYLE,"" +
									"arc:20;" +
									"[light]background:darken(@background,3%);" +
									"[dark]background:lighten(@background,3%)"); 	//Permet de modifier les propriete de la classe, ici le rendre rond sur les bords et modifier son fond en fonction du thème
		
		//Initialisation des labels ici car ils seront statiques
		JLabel lbTitle = new JLabel("Bienvenue !");
		
		lbTitle.putClientProperty(FlatClientProperties.STYLE, 
									"" + "font:bold +10");
		
		JLabel description = new JLabel("Veuillez vous connecter pour acceder à votre espace");
		description.putClientProperty(FlatClientProperties.STYLE,"" +
				"[light]foreground:lighten(@foreground,30%);" +
				"[dark]foreground:darken(@foreground,30%)");
		
		
		formPanel.add(lbTitle);
		formPanel.add(description);
		
		formPanel.add(new JLabel("Identifiant"),"gapy 8"); //gapy police de caratère avec la taille 
		formPanel.add(txtUsername);
		
		formPanel.add(new JLabel("Mot de passe"),"gapy 8");
		formPanel.add(txtPassword);
		
		//Ajout d'un label d'erreur au cas ou l'utilisateur se plante de base invisible
		formPanel.add(errorLabel,"gapy 8");
		errorLabel.setForeground(Color.red);
		errorLabel.setVisible(false);
		
		
		final class LoginListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Passer par LoginListener");
				if (verifUserInfo()) {
					frame.updateToMenu();
					
				} else {
					errorLabel.setVisible(true);
				}
				
				
			}
			
		}
		formPanel.add(cmdLogin,"gapy 10");
		cmdLogin.addActionListener(new LoginListener());
		
		
		formPanel.add(createSignUpLabel(),"gapy 10");
		
		add(formPanel);
		}
	
	//Composant creation du bouton inscription
	//background null, aspect web
	//Se reseigner sur l'ajout d'un composant comme ça interet etc ? 
	private Component createSignUpLabel() {
		
		JPanel SignUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		SignUpPanel.putClientProperty(FlatClientProperties.STYLE, "" +
											"background:null");
		
		JButton cmdRegister = new JButton("<html><a href=\"#\">Inscription</a><html>");
		cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
													"border:3,3,3,3");
		cmdRegister.setContentAreaFilled(false); //Permet de faire un bouton qui n'occupe pas l'espace a la web en somme
		cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		JLabel inscriptionLb = new JLabel("Pas encore inscrit ? Par ici ! -> ");
		inscriptionLb.putClientProperty(FlatClientProperties.STYLE, "" + 
						"[light]foreground:lighten(@foreground,30%);" +
						"[dark]foreground:darken(@foreground,30%)");
		
		SignUpPanel.add(inscriptionLb);
		SignUpPanel.add(cmdRegister);
		
		final class ConnToRegisterListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setInscriptionMode(true);
				frame.init();
				
			}
			
		}
		cmdRegister.addActionListener(new ConnToRegisterListener());
		
		
		
		return SignUpPanel;
	}
	
	
	//Fonction de vérification des données de l'utilisateur avant la connexion
	public boolean verifUserInfo() {
		//L'on commence par request les infos de l'utilisateur à l'aide des info rentrer
		boolean res;
		//DataApi utilitaire = new DataApi();
		String myPass= String.valueOf(txtPassword.getPassword()); //Methode pour récupéré le mdp de manière sécurisé
		res = DataApi.connecteUser(txtUsername.getText(), myPass);
		System.out.println(txtUsername.getText());
		System.out.println(myPass);
		System.out.println(res);
		
		return res;
		
		}
	//Attributs
	private ApplicationFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword; //Permet de pas voir le mdp en clair quand il est taper. 
	private JButton cmdLogin;
}
