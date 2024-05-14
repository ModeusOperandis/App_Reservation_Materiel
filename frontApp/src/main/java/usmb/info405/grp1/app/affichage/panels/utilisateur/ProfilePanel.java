 package usmb.info405.grp1.app.affichage.panels.utilisateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.json.JSONObject;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.ApplicationFrame;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.Utilisateur;
import usmb.info405.grp1.app.utilitaire.AbstractPanel;
import usmb.info405.grp1.app.utilitaire.DataApi;
public class ProfilePanel extends AbstractPanel {
	//Commentaire général : Panel pour l'affichage et la modification du profile de l'utilisateur 
	//a la modification les données seront envoyer sous forme de JSON. 
	
	
	//Sérialisation
	private static final long serialVersionUID = 1L;
	//Attributs
	
	private ApplicationFrame frame;
	
	//Les données
	private Utilisateur user;
	
	//Les boolean pour le switch
	private boolean modeEdition;
	
	//Les txtFields
	private JTextField txtUsername;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JPasswordField txtPass;
	
	//Les Buttons 
	JButton updateButton;
	JButton confirmButton;
	
	//Les Panels
	private JPanel formPanel;
	

	
	
	public ProfilePanel() {
		this.id = "profil";
		this.user = getUser();
		this.formPanel = createFormPanel();
		init();
		
	}



	@Override
	public void init() {
		//Initialisation du Layout
		//setBackground(Color.orange);
		setLayout(new MigLayout("fill,insets 20","[center]","[center]"));
		add(formPanel);
		switchMode(true);
		
		txtEmail.setEditable(false);
		txtSurname.setEditable(false);
		txtName.setEditable(false);
		txtUsername.setEditable(false);
		txtPass.setEditable(false);
		
		
		
	}
	
	//permet de cree et d'initaliser les formulaire sous forme d'un JPanel
	private JPanel createFormPanel() {
		//creation du formPanel et formatage
		JPanel formPanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45","fill,250:280"));
		formPanel.putClientProperty(FlatClientProperties.STYLE,"" +
				"arc:20;" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		
		JLabel lbTitle = new JLabel("Profil");
		lbTitle.putClientProperty(FlatClientProperties.STYLE, 
									"" + "font:bold +10");
		
		JLabel description = new JLabel("Veuillez trouver vos coordonnées ci-dessous");
		description.putClientProperty(FlatClientProperties.STYLE,"" +
				"[light]foreground:lighten(@foreground,30%);" +
				"[dark]foreground:darken(@foreground,30%)");
		
		 txtUsername = new JTextField(user.getLogin());
		 txtName = new JTextField(user.getNom());
		 txtSurname = new JTextField(user.getPrenom());
		 txtEmail = new JTextField(user.getEmail());
		 txtPass = new JPasswordField();
		 txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrez votre nouveau mot passe");
		 
		 //Boutons
		 updateButton = new JButton("Modifiez vos informations");
		 updateButton.addActionListener(new ProfileUpdateButtonListener());
		 confirmButton = new JButton("Confirmez vos changements");
		 confirmButton.addActionListener(new ProfileConfirmButtonListener());
		
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
		
		formPanel.add(new JLabel("Modification du Mot de Passe"),"gapy 8"); //gapy police de caratère avec la taille 
		formPanel.add(txtPass);
		txtPass.putClientProperty(FlatClientProperties.STYLE,""+
				"showRevealButton:true");
		
		
		formPanel.add(Box.createRigidArea(new Dimension(0,20)));
		
		
		return formPanel;
		
		
	}


		//Listeners
		final class ProfileUpdateButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				modeEdition = true;
				switchMode(false);
				
			}

		}
		
		final class ProfileConfirmButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				modeEdition = false;
				switchMode(false);
				System.out.println(sendDataJSON());
				System.out.println(DataApi.getinstance().updateUser(user.getId(), sendDataJSON()));
			}

		}
		
	//Methodes
	
	//Methode pour sélectionner dans quel mode passe le panel, quel bouttons lui sont disponible et si les 
	//JTextFields sont éditables
	//A noter je l'utilse également à l'initialisation d'ou le boolean first pour premier apparu
	//IN b : Boolean 

	public void switchMode (boolean first)
	{
		if (first) {
			formPanel.add(updateButton);
			this.modeEdition = false;
		} else {
			if (modeEdition) {
				formPanel.remove(updateButton);
				formPanel.add(confirmButton);
				
				this.txtUsername.setEditable(true);
				//this.passwordField.setEditable(true);
				this.txtEmail.setEditable(true);
				this.txtName.setEditable(true);
				this.txtSurname.setEditable(true);
				this.txtPass.setEditable(true);
			} else {
				formPanel.remove(confirmButton);
				formPanel.add(updateButton);
				
				this.txtUsername.setEditable(false);
				//this.passwordField.setEditable(false);
				this.txtEmail.setEditable(false);
				this.txtName.setEditable(false);
				this.txtSurname.setEditable(false);
				this.txtPass.setEditable(false);
			}
		}
		this.revalidate();
		this.repaint();
		
	}
	
	//Methode qui crée un JSON à partir des données dans le formulaire et les envoie en back. 
	public JSONObject sendDataJSON() {
		JSONObject dataToSend = new JSONObject();
		dataToSend.put("username", txtUsername.getText());
		//dataToSend.put("password", passwordField.getText());
		dataToSend.put("email", txtEmail.getText());
		dataToSend.put("nom", txtName.getText());
		dataToSend.put("prenom", txtSurname.getText());
		
		String myPass= String.valueOf(txtPass.getPassword());
		if (myPass.equals("")) {
			System.out.println("Mot de passe vide");
			myPass = null;
		}
		
		dataToSend.put("password", myPass);
		
		return dataToSend;
		
	}
	
	//fonction de récupération de l'utilisateur connecte depuis le back et cast vers la classe utilisateur
	public Utilisateur getUser() {
		JSONObject userInfo = DataApi.getinstance().getUserInfo();
		System.out.println(userInfo);
		Utilisateur user = new Utilisateur(Long.valueOf(String.valueOf(userInfo.get("id"))) , (String) userInfo.get("nom"), userInfo.getString("prenom"), userInfo.getString("email"), userInfo.getString("username"), "");
		return user;
		
	}
	
}

	
	
	//TODO rework avec Flatlaf et faire un formulaire à part pour le mot de passe
	//TODO faire une fonction de verification de mot de passe. 


