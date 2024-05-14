package usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.listeners;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.AppRole;


public class ModifierUtilisateurListener implements ActionListener {
    
    private JTextField nom;
    private JTextField prenom;
    private JTextField email;
    private JTextField login;
    private JTextField role;
    private JButton modifier;

    public ModifierUtilisateurListener(JTextField nom, JTextField prenom, JTextField email, JTextField login,
			JTextField role, JButton modifier) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.login = login;
		this.role = role;
		this.modifier = modifier;
	}



	@Override
    public void actionPerformed(ActionEvent e) {
        if (nom.isEditable()){
        	nom.setEditable(false);
        	prenom.setEditable(false);
        	email.setEditable(false);
        	login.setEditable(false);
        	role.setEditable(false);
        	modifier.setText("Modifier");
        	
        	// déduit les roles de l'utilisateur après sa modification
        	
        	ArrayList<JSONObject> listeRoles = new ArrayList<JSONObject>();
        	
        	ArrayList<AppRole> roles = this.getRolesFromDb();
        	
    		for(AppRole roleParcours: roles) {
    		    if (role.getText().toUpperCase().contains(roleParcours.getRoleName())){
    				JSONObject roleJson = new JSONObject();
    				try {
    					roleJson.put("id", roleParcours.getId());
    					roleJson.put("roleName", roleParcours.getRoleName());
    					listeRoles.add(roleJson);
    				} catch (JSONException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}

    		    }
    		}		    
		    
        	// crée l'objet JSON
		    
		    JSONObject utilisateurJson = new JSONObject();
		    try {
				utilisateurJson.put("nom", nom.getText());
				utilisateurJson.put("prenom", prenom.getText());
			    utilisateurJson.put("email", email.getText());
			    utilisateurJson.put("username", login.getText());
			    utilisateurJson.put("appRoles", listeRoles);
			    
			    System.out.println(utilisateurJson);
			    
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        }else {
        	nom.setEditable(true);
        	prenom.setEditable(true);
        	email.setEditable(true);
        	login.setEditable(true);
        	role.setEditable(true);
        	modifier.setText("Valider");
        	

        }
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
