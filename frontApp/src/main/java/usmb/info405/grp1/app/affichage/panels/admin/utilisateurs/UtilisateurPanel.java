package usmb.info405.grp1.app.affichage.panels.admin.utilisateurs;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.AppRole;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.Utilisateur;

import usmb.info405.grp1.app.utilitaire.DataApi;

import javax.swing.JButton;
import javax.swing.JDialog;

public class UtilisateurPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// liste des utilisateurs
	public ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	
	/**
	 * Create the panel.
	 */
	public UtilisateurPanel() {
		init();
	}
	
	private void init() {
		utilisateurs = getUtilisateurs();
		setLayout(new MigLayout("wrap,fillx,insets 35 45 30 45"));
		
		add(this.affichageUtilisateurs(utilisateurs));
		JPanel panel = new JPanel();
		add(panel);
		
		panel.add(this.ajouterUtilisateurButton());
	}
	
	public JScrollPane affichageUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
		// panel principal stockant toutes les informations
        String data[][];   
        data = new String[utilisateurs.size()][5];
		String column[]={"NOM","PRENOM","EMAIL","USERNAME","ROLES"};    
		
	      for (int i = 0; i < utilisateurs.size(); i++) {
	    	  Utilisateur u = utilisateurs.get(i);
	        	  
	          data[i][0] = u.getNom(); 
	          data[i][1] = u.getPrenom();
	          data[i][2] = u.getEmail();
	          data[i][3] = u.getUsername(); 
	          data[i][4] = u.rolesToString();
	       }
	       
		final JTable jt = new JTable(data,column);    
		jt.setDefaultEditor(Object.class, null);

		JScrollPane sp=new JScrollPane(jt);  
		sp.setPreferredSize(new Dimension(800,500));
		return sp;  
	}

	
	public JButton ajouterUtilisateurButton() {
		AjoutUtilisateurPanel ajoutUtilisateurPanel = new AjoutUtilisateurPanel(this);
        JButton btnAjouterUtilisateur = new JButton("Ajouter Utilisateur");

        btnAjouterUtilisateur.addActionListener(e -> {
        	
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(ajoutUtilisateurPanel);
            //dialog.pack(); // prend size auto
            dialog.setSize(300, 500);
            dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(ajoutUtilisateurPanel));
            dialog.setVisible(true);
        });
        return btnAjouterUtilisateur;
	}

	
	public ArrayList<Utilisateur> getUtilisateurs() {
		DataApi ad = DataApi.getinstance();
		//ad.connecteUser("admin","1234");
		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		JSONArray resRequete = ad.getUsers();
		for(int i = 0; i < resRequete.length(); i++) {
			try {
				JSONObject user = (JSONObject) resRequete.get(i);
				ArrayList<AppRole> roles = getRole(user);
				
				Utilisateur u = new Utilisateur(
				Long.parseLong(user.get("id").toString()), 
				user.get("nom").toString(), 
				user.get("prenom").toString(), 
				user.get("email").toString(), 
				user.get("username").toString(), 
				roles);
				utilisateurs.add(u);	
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return utilisateurs;
	}
	
	public ArrayList<AppRole> getRole(JSONObject u) throws JSONException {
		
		 ArrayList<AppRole> res = new ArrayList<AppRole>();
		 JSONArray roles = (JSONArray) u.get("appRoles");
		 
		 for(int i = 0; i < roles.length(); i++) {
			 JSONObject role = (JSONObject) roles.get(i);
			 res.add(new AppRole( Long.parseLong(role.get("id").toString()), role.get("roleName").toString()));
		 }
		 
		 return res;
		
	}
	
	public void refreshPanel() {
		removeAll();
		init();
		revalidate();
		repaint();
	}

}
