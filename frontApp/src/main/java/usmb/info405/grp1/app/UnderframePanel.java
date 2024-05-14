package usmb.info405.grp1.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import usmb.info405.grp1.app.affichage.menu.MainForm;
import usmb.info405.grp1.app.affichage.menu.MenuPanel;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.AppRole;
import usmb.info405.grp1.app.utilitaire.AbstractPanel;
import usmb.info405.grp1.app.utilitaire.DataApi;

public class UnderframePanel extends AbstractPanel{
	private static final long serialVersionUID = 1L;
	//private MenuPanel menu;
	private MainForm menu;
	private ContenantPane contenantPanel;
	private ApplicationFrame frame;
	private JSONObject userInfo = DataApi.getinstance().getUserInfo();
	
	public UnderframePanel(ApplicationFrame f) {
		this.frame = f;
		contenantPanel = new ContenantPane();
		menu = new MainForm(this.contenantPanel, this.frame);
		
		//menu = new MenuPanel(contenantPanel,frame);
	init();
	}

	@Override
	public void init() {
		System.out.println(getRoleFromUserInfo() );
		setLayout(new BorderLayout());
		//setBackground(Color.pink);
		//Ajout du menu à gauche et du contenantPanel à droite. 
		add(menu,BorderLayout.WEST);
		add(contenantPanel,BorderLayout.CENTER);
		
		revalidate();
		repaint();
				
	}
	//Permet de récupéré le role de l'utilisateur connecté à partir des données back
	public static String getRoleFromUserInfo() {
		
		String res = "USER";
		JSONObject info = DataApi.getinstance().getUserInfo();
		JSONArray arr = (JSONArray) info.get("appRoles");
		
		System.out.println(arr);
		for (int i = 0; i < arr.length(); i++) {
			String role = (String) arr.getJSONObject(i).get("roleName");
			if (role.equals("ADMIN")) {
				res = role;
			}
			
		}
		
		return res;
		
	}
	
	public static String getUsernameFromUserInfo() {
		
		JSONObject info = DataApi.getinstance().getUserInfo();
		String res = info.getString("username");
		return res;
	}


}
