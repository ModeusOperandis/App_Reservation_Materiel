/**
 * 
 */
package usmb.info405.grp1.app.affichage.panels.reservations.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.jdatepicker.JDatePicker;

import usmb.info405.grp1.app.affichage.panels.reservations.SelectCreneauxPanel;
import usmb.info405.grp1.app.models.Panier;

/**
 * TODO: remove, utilisé pour les tests de selections de 2 dates
 */
public class SelectButtonActionListener implements ActionListener {
	private Panier panier;
	private SelectCreneauxPanel panel;

	public SelectButtonActionListener(Panier panier, SelectCreneauxPanel panel) {
		super();
		this.panier = panier;
		this.panel = panel;
	}
	
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
        Date startDate = (Date) ((JDatePicker) panel.getDatePickerDebut()).getModel().getValue();
        Date endDate = (Date) ((JDatePicker) panel.getDatePickerFin()).getModel().getValue();
        
        if (datesCorrectes(startDate, endDate)) {
            ajoutDatesPanier(startDate, endDate);
        } else {
        	System.out.println("Veuillez sélectionner les deux dates.");
        }
    }
	
	/**
	 * renvoie si les deux dates sont valides
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Boolean datesCorrectes(Date date1, Date date2) {
		Boolean res;
		res = (date1 != null && date2 != null);
		// compare si la date1 est avant ou la meme que la date 2
		if (res) {
			res = date1.compareTo(date2) <= 0;
		} else {
			res = false;
		}
		return  res;
	}
	
	/**
	 * realise la conversion des Date en DateTime et ajoute les dates au panier
	 * @param startDate
	 * @param endDate
	 */
	public void ajoutDatesPanier(Date startDate, Date endDate) {
		LocalDateTime startDateTime = dateToLocalDateTime(startDate);
		LocalDateTime endDateTime = dateToLocalDateTime(endDate);
		
		// ajout des heures (minuit pour debut et 23h59 pour fin)
		startDateTime = startDateTime.toLocalDate().atStartOfDay();
        endDateTime = endDateTime.toLocalDate().atTime(23, 59, 59);
		
		panier.getCreneau().setDateDebut(startDateTime);
        panier.getCreneau().setDateFin(endDateTime);
        
        System.out.println("Dates sélectionnées :\nDébut : " + startDateTime.toString() + "\nFin : " + endDateTime.toString());
        
	}
	
	public LocalDateTime dateToLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	
}
