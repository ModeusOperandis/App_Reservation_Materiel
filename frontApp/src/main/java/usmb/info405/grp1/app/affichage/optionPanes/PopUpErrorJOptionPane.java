/**
 * 
 */
package usmb.info405.grp1.app.affichage.optionPanes;

import javax.swing.JOptionPane;

/**
 * @author humbeeva
 */
public class PopUpErrorJOptionPane extends JOptionPane {
	
	/**
	 * represente le niveau des erreurs
	 */
	public static final int BASIQUE = 0;
	public static final int CRITIQUE = 1;

	private static final long serialVersionUID = -2724145542801760766L;
	
	public PopUpErrorJOptionPane(String message, int level) {
		showError(message, level);
	}
	
	private void showError(String message, int level) {
		if (level == PopUpErrorJOptionPane.BASIQUE) {
			showBasiqueError(message);
		} else if (level == PopUpErrorJOptionPane.CRITIQUE) {
			showCritiqueError(message);
		}
	}
	
	private  void showBasiqueError(String message) {
		showMessageDialog(getComponentPopupMenu(), message, "ERREUR", JOptionPane.ERROR_MESSAGE);
	}
	
	private  void showCritiqueError(String message) {
		 Object[] options = {"OK", "Quitter"};
	        int choix = JOptionPane.showOptionDialog(null, message, "ERREUR",
	                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
	                        null, options, options[0]);

	        if (choix == 1) {
	            System.exit(0); // Quitter l'app
	        }
	}
}
