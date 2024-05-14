package usmb.info405.grp1.app;


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import usmb.info405.grp1.app.affichage.menu.MenuPanel;



public class Application {
	
	//Attributes
	
	//Methods 
	//Fonction qui permet de choisir la bonne frame en fonction de la réponse du back 
	public static void init() {
			EventQueue.invokeLater(()-> new ApplicationFrame().setVisible(true)); //L'idée ici est d'instancier la fenêtre que à partir du moment ou tout les thèmes par defaut on été défini
	}
	
	
	
	
	public static void main (String[] args) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("usmb.info405.grp1.frontApp.ressources.themes"); // Change la tête des boutons et de la fenêtre sur win cf fichier properties
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13)); //L'idée est de donner un fond par défaut à toute l'application. 
		FlatLaf.setup(new FlatIntelliJLaf());
		init();
		
		
	}
	
	/*Ancien AppFrame
	
	public static final int HAUTEUR = 800; 
	public static final int LARGEUR = 1000;
	
	private PanelFrameBuild gPanelBuild;
	private PanelFrameBuild mPanelBuild;
	private PanelFrameBuild dPanelBuild;
	
	public static void main(String[] args) {
		
		
		AppFrame appfr = new AppFrame();
		appfr.setVisible(true);
		appfr.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	}
	
	public AppFrame() {
		super();
		
		this.gPanelBuild = new PanelGauche();
		JPanel gPanel = gPanelBuild.buildPanel();
		
		
		this.mPanelBuild = new PanelMid(gPanel);
		JPanel mPanel = mPanelBuild.buildPanel();
		
		setTitle("App_Reserv");
		setSize(LARGEUR,HAUTEUR);
		setLayout(new BorderLayout());
		
	
		*/
		/*==========================================*/
		
		/*-------------------------------------------*/
		// Gestion des contraintes pour l'ensemble des panels
		/*
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
		getContentPane().setLayout(new GridBagLayout());
		// Ajout des panneaux à la fenêtre avec les contraintes spécifiées
		
			// Menu Gauche = Case (0,0)
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 0.05; // gauche % de la fenêtre
        getContentPane().add(gPanel, gbc);
        
        	//Mid = Case(1,0) donc gridy = 0
        gbc.gridx = 1;
        gbc.weightx = 0.95; // milieu % de la fenêtre
        getContentPane().add(mPanel, gbc);
        
        
	}*/
}


