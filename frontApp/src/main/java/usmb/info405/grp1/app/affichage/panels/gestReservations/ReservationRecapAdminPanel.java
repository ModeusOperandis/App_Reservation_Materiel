package usmb.info405.grp1.app.affichage.panels.gestReservations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.affichage.panels.reservations.controllers.SupprimerCreneau;
import usmb.info405.grp1.app.models.Creneau;
import usmb.info405.grp1.app.models.Produit;

/***
 * represente un recap de creneau 
 * avec un header qui affiche les 2 dates
 * et un content qui se deplie lorsque le header est clique
 * 				-> affiche les produits entrepot  + quantité
 */
public class ReservationRecapAdminPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JPanel headerPanel;
	
	private JPanel contentPanel;
	
	private Creneau creneau;
	
	private GestReservationsPanel reservationPanel;
	
	
	public ReservationRecapAdminPanel(GestReservationsPanel reservationPanel, Creneau creneau) {
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));
		
		this.reservationPanel = reservationPanel;
		this.creneau = creneau;
		init();
		
	}
	
	private void init() {
		headerPanel = new JPanel(new GridLayout(1,7));
		headerPanel.setVisible(true);
		
		contentPanel = new JPanel(new GridLayout(0, 2));
		contentPanel.setVisible(false);
		headerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentPanel.setVisible(!contentPanel.isVisible());
                // Rafraîchir l'affichage 
                revalidate();
                repaint();
            }
        });
		add(headerPanel, BorderLayout.CENTER);
		add(contentPanel, BorderLayout.SOUTH);
		updateHeaderPanel();
		updateContentPanel();
		
	}
	
	/**
	 * supprime puis rajoute les données pour le header panel
	 */
	private void updateHeaderPanel() {
		headerPanel.removeAll();
		JLabel deLabel = new JLabel("De: ");
        deLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Changer la police si nécessaire
        deLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deLabel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel ddebutLabel = new JLabel(creneau.formatDate(creneau.getDateDebut()));
        ddebutLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Changer la police si nécessaire
        ddebutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ddebutLabel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel dfinLabel = new JLabel(creneau.formatDate(creneau.getDateFin()));
        dfinLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Changer la police si nécessaire
        dfinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dfinLabel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel aLabel = new JLabel("A: ");
        aLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Changer la police si nécessaire
        aLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        JLabel userLabel = new JLabel(creneau.getUtilisateur().getUsername());
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Changer la police si nécessaire
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel parLabel = new JLabel("Par: ");
        parLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Changer la police si nécessaire
        parLabel.setHorizontalAlignment(SwingConstants.CENTER);
        parLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        JButton SupprCmd = new JButton("Supprimer");
        SupprCmd.addActionListener(new SupprimerCreneau(this.creneau, this, reservationPanel));

        headerPanel.add(deLabel);
        headerPanel.add(ddebutLabel);
        headerPanel.add(aLabel);
        headerPanel.add(dfinLabel);
        headerPanel.add(parLabel);
        headerPanel.add(userLabel);
        headerPanel.add(SupprCmd);
        
	}
	
	
	
	/**
	 * supprime puis rajoute les données pour le header content
	 */
	private void updateContentPanel() {
		contentPanel.removeAll();
		contentPanel.revalidate();
		ArrayList<Produit> prodList = getUniqueProduitsListeFromCreneau();
		System.out.println("Taille de prodList : " + prodList.size());
		//contentPanel.setLayout(new GridLayout(prodList.size(), 1));
		
		JLabel deLabel = creerLabelCentre("Produit");
        deLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Changer la police si nécessaire
        
        contentPanel.add(deLabel);
        JLabel qtyLabel = creerLabelCentre("Quantité");
        qtyLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Changer la police si nécessaire
        contentPanel.add(qtyLabel);
        
        for (Produit prod : prodList) {
        	JLabel pNom = creerLabelCentre(prod.getNom());
            contentPanel.add(pNom);
            JLabel pQty = creerLabelCentre(String.valueOf(getProduitEntrepotNumber(prod)));
            contentPanel.add(pQty);
        }
        
		
		
		
	}
	
	
	private JLabel creerLabelCentre(String text) {
		JLabel jLabel = new JLabel(text);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setVerticalAlignment(SwingConstants.CENTER);
		return jLabel;
	}
	
	
	/** 
	 * renvoie le nombre de produits entrepot associe au produit donne qui sont dans le creneau 
	 */
	public int getProduitEntrepotNumber(Produit prodARecup) {
		int res = 0;
		for (Entrepot prodEntrepot : creneau.getProduitsEntrepot()) {
			Produit prod = prodEntrepot.getProduit();
			if (prodARecup.getId() == prod.getId()) {
				res++;
			}
		}
		
		
		return res;
	}
	
	
	/**
	 * renvoie une liste de produits unique depuis une liste de produitEntrepot
	 * @return
	 */
	public ArrayList<Produit> getUniqueProduitsListeFromCreneau() {
		ArrayList<Produit> res = new ArrayList<Produit>();
		for (Entrepot prodEntrepot : creneau.getProduitsEntrepot()) {
			Produit prod = prodEntrepot.getProduit();
			if (! produitEstDansListe(prod, res)) {
				res.add(prod);
			}
		}
		
		return res;
	}
	
	
	/**
	 * renvoie si le produit donne est dans la liste
	 */
	public boolean produitEstDansListe(Produit p, ArrayList<Produit> liste) {
		boolean res = false;
		int i = 0;
		int liste_size = liste.size();
		while (!res && i < liste_size) {
			if (p.getId() == liste.get(i).getId()) {
				res = true;
			}
			i++;
		}
		
		return res;
	}
	
	
}









