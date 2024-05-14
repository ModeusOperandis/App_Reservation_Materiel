package usmb.info405.grp1.app.affichage.panels.admin.entrepot.listeners;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import usmb.info405.grp1.app.affichage.panels.admin.entrepot.EntrepotPanel;
import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.utilitaire.DataApi;


public class ModifierDispoListener implements ActionListener {
    
    private Entrepot objet;
    private EntrepotPanel entrepotPanel;
    
	public ModifierDispoListener(Entrepot objet, EntrepotPanel entrepotPanel) {
		super();
		this.objet = objet;
		this.entrepotPanel = entrepotPanel;
	}

	@Override
    public void actionPerformed(ActionEvent e) {
        if (objet.getEstDispo()){
        	objet.setEstDispo(false);
        }else {
        	objet.setEstDispo(true);
        }
        
    	// requete vers API
        
        DataApi ad = DataApi.getinstance();
        ad.updateProduitEntrepot(objet.getId(), objet.getEstDispo());
	    
	    entrepotPanel.refreshPanel();
    }
	

}
