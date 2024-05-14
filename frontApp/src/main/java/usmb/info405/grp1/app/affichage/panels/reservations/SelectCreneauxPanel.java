package usmb.info405.grp1.app.affichage.panels.reservations;

import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import usmb.info405.grp1.app.exceptions.InvalidDateException;
import usmb.info405.grp1.app.models.Panier;
import net.miginfocom.swing.MigLayout;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.JLabel;

public class SelectCreneauxPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Panier panier;

	/**
	 * Represente les deux inputs pour la selection de la date de debut et de fin
	 */
	JDatePickerImpl datePickerDebut;
	JDatePickerImpl datePickerFin;

	private JLabel label;

	/**
	 * Create the panel.
	 */
	public SelectCreneauxPanel(Panier panier) {
		this.panier = panier;
		initialiseDatePicker();

	}

	/**
	 * initialise les deux dates pickers
	 */
	public void initialiseDatePicker() {
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		datePickerDebut = new JDatePickerImpl(datePanel1, new DateLabelFormatter());

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePickerFin = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		setLayout(new MigLayout("", "[75px][205px][55px][205px][75px]",
				"[31px][31px][31px][31px][31px][31px][31px][31px][31px][31px][31px][31px][31px][31px][31px][31px]"));

		JLabel lblEtapeN = new JLabel("Etape n°1");
		add(lblEtapeN, "cell 0 0");

		this.add(datePickerDebut, "cell 1 1,grow");

		label = new JLabel("");
		add(label, "cell 2 0,grow");
		this.add(datePickerFin, "cell 3 1,grow");
	}
	
	
	/**
	 * recupere les dates et les enregistres dans le panier
	 * @throws InvalidDateException 
	 */
	public void updatePanierAvecDate() throws InvalidDateException {
		Date startDate = (Date) datePickerDebut.getModel().getValue();
        Date endDate = (Date) datePickerFin.getModel().getValue();
        
        if (datesCorrectes(startDate, endDate)) {
            ajoutDatesPanier(startDate, endDate);
        } else {
        	System.out.println("Veuillez sélectionner les deux dates.");
        	throw new InvalidDateException("Veuillez sélectionner deux dates valides");
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
	

	public JDatePickerImpl getDatePickerDebut() {
		return datePickerDebut;
	}

	public void setDatePickerDebut(JDatePickerImpl datePickerDebut) {
		this.datePickerDebut = datePickerDebut;
	}

	public JDatePickerImpl getDatePickerFin() {
		return datePickerFin;
	}

	public void setDatePickerFin(JDatePickerImpl datePickerFin) {
		this.datePickerFin = datePickerFin;
	}

}
