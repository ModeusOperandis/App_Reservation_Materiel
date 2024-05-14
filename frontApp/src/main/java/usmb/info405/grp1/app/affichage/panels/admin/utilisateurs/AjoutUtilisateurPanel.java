package usmb.info405.grp1.app.affichage.panels.admin.utilisateurs;

import javax.swing.JPanel;



import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.AppRole;
import usmb.info405.grp1.app.utilitaire.DataApi;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;

import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JButton;

/**
 * Classe pour ajout utilisateur
 * @author humban
 */
public class AjoutUtilisateurPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	private JTextField txtUsername;
	private ArrayList<JCheckBox> checkbox = new ArrayList<JCheckBox>();
	private ArrayList<String> rolesUser = new ArrayList<String>();

	/**
	 * Create the panel.
	 */
	public AjoutUtilisateurPanel(UtilisateurPanel userPanel) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_ajout_utilisateur_main = new JPanel();
		add(panel_ajout_utilisateur_main, BorderLayout.CENTER);
		panel_ajout_utilisateur_main.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAjoutDunUtilisateur = new JLabel("Ajout d'un Utilisateur");
		lblAjoutDunUtilisateur.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAjoutDunUtilisateur.setHorizontalAlignment(SwingConstants.CENTER);
		panel_ajout_utilisateur_main.add(lblAjoutDunUtilisateur, BorderLayout.NORTH);
		
		JPanel panelFormAjout = new JPanel();
		panel_ajout_utilisateur_main.add(panelFormAjout, BorderLayout.CENTER);
		panelFormAjout.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel FormNom = new JPanel();
		panelFormAjout.add(FormNom);
		
		JLabel lblNom = new JLabel("Nom:");
		FormNom.add(lblNom);
		
		txtNom = new JTextField();
		FormNom.add(txtNom);
		txtNom.setPreferredSize(new Dimension(100, 30));
		
		JPanel FormPrenom = new JPanel();
		panelFormAjout.add(FormPrenom);
		
		JLabel lblPrenom = new JLabel("Prenom:");
		FormPrenom.add(lblPrenom);
		
		txtPrenom = new JTextField();
		txtPrenom.setPreferredSize(new Dimension(100, 30));
		FormPrenom.add(txtPrenom);
		
		JPanel FormEmail = new JPanel();
		panelFormAjout.add(FormEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		FormEmail.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(100, 30));
		FormEmail.add(txtEmail);
		
		JPanel FormUsername = new JPanel();
		panelFormAjout.add(FormUsername);
		
		JLabel lblUsername = new JLabel("Username:");
		FormUsername.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(100, 30));
		FormUsername.add(txtUsername);
		
		JPanel FormPassword = new JPanel();
		panelFormAjout.add(FormPassword);
		
		JLabel lblPassword = new JLabel("Password:");
		FormPassword.add(lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setPreferredSize(new Dimension(100, 30));
		FormPassword.add(pwdPassword);
		
		
		ArrayList<AppRole> roles = this.getRolesFromDb();
		JPanel FormRole = new JPanel();
		panelFormAjout.add(FormRole);
		
		JLabel lblRoles = new JLabel("Roles:");
		FormRole.add(lblRoles);
		
		
		
		for(AppRole role: roles) {
			JCheckBox checkRole = new JCheckBox(role.getRoleName());
			FormRole.add(checkRole);
			
			checkbox.add(checkRole);
		}

		
		
		JButton btnAjouter = new JButton("Ajouter");
		panel_ajout_utilisateur_main.add(btnAjouter, BorderLayout.SOUTH);
		btnAjouter.addActionListener(e -> {
			
		    // Obtenir les informations saisies par l'utilisateur
		    String nom = txtNom.getText();
		    String prenom = txtPrenom.getText();
		    String email = txtEmail.getText();
		    String password = new String(pwdPassword.getPassword());
		    String username = txtUsername.getText();
		    String role = ""; 
				    
			for(JCheckBox check: checkbox) {
				if (check.isSelected()) {
					role = role + check.getText();
				}
				
				
			}
		    
		    // ajoute l'utilisateur dans la base
		    
			DataApi.inscritUser(nom, prenom, email, role, username, password);
		    System.out.println("Utilisateur ajouté à la base");

		    
		    Window window = SwingUtilities.getWindowAncestor(btnAjouter); // Récupérer la fenêtre parente du bouton
		    if (window instanceof JDialog) {
		        JDialog dialog = (JDialog) window;
		        
		        // RAZ des données des formulaires
		        
		        txtNom.setText("");
		        txtPrenom.setText("");
		        txtEmail.setText("");
		        txtUsername.setText("");
		        pwdPassword.setText("");
		        rolesUser.clear();
		        
		        dialog.dispose(); // Fermer le JDialog actuel
		    }
		    userPanel.refreshPanel();

		});

	}
	
	/**
	 * recupere les roles dans la db et renvoie une liste
	 * @return
	 */
	public ArrayList<AppRole> getRolesFromDb() {
		//TODO FAIRE REQUETE
		AppRole user = new AppRole((long)1, "USER");
		AppRole admin = new AppRole((long)2, "ADMIN");
		ArrayList<AppRole>  allRoles = new ArrayList<AppRole>();
		allRoles.add(user);
		allRoles.add(admin);
		return allRoles;
	}
	
}
