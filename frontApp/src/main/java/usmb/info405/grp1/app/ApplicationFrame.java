package usmb.info405.grp1.app;

import java.awt.Dimension;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import usmb.info405.grp1.app.connexion.ConnexionPanel;
import usmb.info405.grp1.app.connexion.InscriptionPanel;

public class ApplicationFrame extends JFrame {
	
	private static final long serialVersionUID = -524419722309715132L;
	private boolean inscriptionMode = false; //Boolean servant à la connexion
	private boolean estConnecte = false; //Boolean qui permet à l'application de savoir le status de connexion
	//private DataApi utilitaire = new DataApi();
	
	public ApplicationFrame()
	{
		init();
	}
	
	
	//Initialisation de la frame
	public void init() {
		
		//Initialisation de la classe utilitaire
		
		
		setSize(new Dimension(1200,700)); //Set de la taille par la classe dimension. 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Initialisation de l'inscription
		if (inscriptionMode) {
			setTitle("Inscription");
			setContentPane(new InscriptionPanel(this)); //En utilisant contentPane on défini un conteneur parent pour cette fenetre TO-DO appliquer a frontpanel	
		} else {
			setTitle("Connexion");
			setContentPane(new ConnexionPanel(this));
		}
		revalidate();
		repaint();
	}
	
	//L'objectif est d'afficher le menu après la connexion
	//L'appel se fait lors de la connexion. 
	public void updateToMenu() {
		//ajout du menu et du panel de contenu
		setContentPane(new UnderframePanel(this));
		setTitle("App_Materiel_Audio_Visuel");
		revalidate();
		repaint();
		
	}
	
	public boolean getInscriptionMode() {
		return inscriptionMode;
	}
	
	public void setInscriptionMode(boolean b) {
		inscriptionMode = b;
	}
	
	//fonction de selection des themes
	
	public void SwitchLightTheme() {
		SwingUtilities.invokeLater(()->{
			try {
				UIManager.setLookAndFeel(new FlatLightLaf());
				SwingUtilities.updateComponentTreeUI(this);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
	});
	}
	
	public void SwitchDarkTheme() {
		SwingUtilities.invokeLater(()->{
			try {
				UIManager.setLookAndFeel(new FlatDarculaLaf());
				SwingUtilities.updateComponentTreeUI(this);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
	});
		
	
}
}
