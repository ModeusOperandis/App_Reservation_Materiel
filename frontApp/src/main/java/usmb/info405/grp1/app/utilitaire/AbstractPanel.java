package usmb.info405.grp1.app.utilitaire;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel {
	private static final long serialVersionUID = 8850765442952246642L;
	//attribut
	protected String id; //Permet d'identifier le pannel pour la sélection
	
	//Objectif initialiser les panels de l'ensemble de l'application en fonction des besoins
	public abstract void init();
	

}
