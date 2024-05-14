package usmb.info405.grp1.app.affichage.panels.reservations.controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import usmb.info405.grp1.app.affichage.panels.vosReservations.ReservationsFunctions;
import usmb.info405.grp1.app.models.Creneau;
import usmb.info405.grp1.app.utilitaire.DataApi;


public class SupprimerCreneau implements ActionListener {
    
    private Creneau creneau;
    private JPanel reservations;
    private ReservationsFunctions reservationPanel;

    public SupprimerCreneau(Creneau creneau, JPanel reservations, ReservationsFunctions reservationPanel) {
        this.creneau = creneau;
        this.reservations = reservations;
        this.reservationPanel = reservationPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(reservations,
                "Êtes-vous sûr de vouloir supprimer ce créneau ?",
                "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
        	
            DataApi ad = DataApi.getinstance();
            ad.deleteCreneau(creneau.getId());
            
            System.out.println(creneau.getId());
            System.out.println("Creneau Supprimé");
            
            reservationPanel.updateRecapCreneaux();
           
        }
    }
}
