package usmb.info405.grp1.app.utilitaire;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

//Objectif est de crée un panel scrollable uniquement verticalement
public class ScrollablePanel extends JPanel implements Scrollable{

	private static final long serialVersionUID = 1L;

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return getMinimumSize(); //Necessaire sinon aucun élément est afficher, se calque sur la taille des élément ajouter
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}
	//si la méthode retourne true la largeur du panel sera aligner au viewPort cf super
	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}
	
};