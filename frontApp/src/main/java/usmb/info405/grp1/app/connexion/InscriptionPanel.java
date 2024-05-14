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
public class InscriptionPanel extends JPanel {
	
	private static final long serialVersionUID = -7982358254356415242L;
	//Attribut classe utilitaire 
	private JLabel errorLabel = new JLabel("Informations saisies incorrectes");	//constructeur
	public InscriptionPanel(ApplicationFrame frame) {
		this.frame = frame;
		//this.utilitaire = utilitaire;
		init();
	}

	private void init() {
		setLayout(new MigLayout("fill,insets 20","[center]","[center]")); //Le panel de connexion vis à vis de la fenêtre
		
		//Init des éléments et modification de leur propriétées
		
		txtName = new JTextField();
		txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre nom");
		
		txtSurname = new JTextField();
		txtSurname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre prenom");
		
		txtEmail = new JTextField();
		txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre email");
		
		txtUsername = new JTextField();
		txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre identifiant");
		
		txtPassword = new JPasswordField();
		txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre mot de passe");
		txtPassword.putClientProperty(FlatClientProperties.STYLE,""+
											"showRevealButton:true");
		
		cmdRegister = new JButton("Inscription");
		
		
		
		
		
		//Initialisation du panel de formulaire.
		JPanel formPanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45","fill,250:280"));
		formPanel.putClientProperty(FlatClientProperties.STYLE,"" +
									"arc:20;" +
									"[light]background:darken(@background,3%);" +
									"[dark]background:lighten(@background,3%)"); 	//Permet de modifier les propriete de la classe, ici le rendre rond sur les bords et modifier son fond en fonction du thème légerment plus clair ou plus foncer
		
		//Initialisation des labels ici car ils seront statiques
		JLabel lbTitle = new JLabel("Première fois ?");
		
		lbTitle.putClientProperty(FlatClientProperties.STYLE, 
									"" + "font:bold +10");
		
		JLabel description = new JLabel("Veuillez renseigner vos coordonnées ci-dessous");
		description.putClientProperty(FlatClientProperties.STYLE,"" +
				"[light]foreground:lighten(@foreground,30%);" +
				"[dark]foreground:darken(@foreground,30%)");
		
		
		//Construction du formulaire
		formPanel.add(lbTitle);
		formPanel.add(description);
		
		formPanel.add(new JLabel("Nom"),"gapy 8");
		formPanel.add(txtName);
		
		formPanel.add(new JLabel("Prénom"),"gapy 8");
		formPanel.add(txtSurname);
		
		formPanel.add(new JLabel("Email"),"gapy 8");
		formPanel.add(txtEmail);
		
		formPanel.add(new JLabel("Identifiant"),"gapy 8"); //gapy police de caratère avec la taille 
		formPanel.add(txtUsername);
		
		formPanel.add(new JLabel("Mot de passe"),"gapy 8");
		formPanel.add(txtPassword);
		
		formPanel.add(cmdRegister,"gapy 10");
		
		formPanel.add(createSignUpLabel(),"gapy 10");
		
		formPanel.add(errorLabel,"gapy 8");
		errorLabel.setForeground(Color.red);
		errorLabel.setVisible(false);
		
		add(formPanel);
		}
	
	//Composant creation du bouton inscription
	//background null, aspect web
	//Se reseigner sur l'ajout d'un composant comme ça interet etc ? 
	
	public boolean verifUserInfoBeforeSignUp() {
		boolean res;
		String myPass= String.valueOf(txtPassword.getPassword());
		res = DataApi.inscritUser(txtName.getText(), txtSurname.getText(),txtEmail.getText(), "USER", txtUsername.getText(), myPass);
		return res;
		
	}
	private Component createSignUpLabel() {
		
		JPanel SignUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		SignUpPanel.putClientProperty(FlatClientProperties.STYLE, "" +
											"background:null");
		
		JButton cmdLogin = new JButton("<html><a href=\"#\">Connexion</a><html>");
		cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
													"border:3,3,3,3");
		
		cmdLogin.setContentAreaFilled(false); //Permet de faire un bouton qui n'occupe pas l'espace a la web en somme
		cmdLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//TODO cree le listener pour le switch de formulaire
		
		JLabel loginlb = new JLabel("Déjà inscrit ? Connectez-vous ! -> ");
		loginlb.putClientProperty(FlatClientProperties.STYLE, "" + 
						"[light]foreground:lighten(@foreground,30%);" +
						"[dark]foreground:darken(@foreground,30%)");
		
		SignUpPanel.add(loginlb);
		SignUpPanel.add(cmdLogin);
		
		final class RegisterToConnListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setInscriptionMode(false);
				frame.init();
				
			}
			
		}
		
		//fonction de vérification des infos utilisateur avant l'inscription
		
		
		//Listener qui valide l'inscription au back 
		final class cmdResgisterListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println(verifUserInfoBeforeSignUp());
				
				if (verifUserInfoBeforeSignUp()) {
					//System.out.println(verifUserInfoBeforeSignUp());
					frame.updateToMenu();
				} else  {
					errorLabel.setVisible(true);
				}
				
			}
			
		}
		
		cmdLogin.addActionListener(new RegisterToConnListener());
		cmdRegister.addActionListener(new cmdResgisterListener());
		
		
		
		
		return SignUpPanel;
	}
	
	//Attributs
	private ApplicationFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword; //Permet de pas voir le mdp en clair quand il est taper.
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JButton cmdRegister;
	
}