package usmb.info405.grp1.app.affichage.panels.reservations;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.affichage.panels.reservations.models.ProduitUtilisateurReservation;
import usmb.info405.grp1.app.affichage.panels.reservations.models.ProduitUtilisateurReservation;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ReservationProduitPanel extends JPanel implements PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	//private JTextField textField;
	
	private JButton plus;
	private JButton moins;
	private JButton max;
	private JTextField produitNb;
	private ProduitUtilisateurReservation model;

	/**
	 * Create the panel.
	 */
	public ReservationProduitPanel(ProduitUtilisateurReservation model) {
		this.model = model;
		model.addPropertyChangeListener(this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 47, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.WEST;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 2;
		gbc_lblNom.gridy = 1;
		//zadd(lblNom, gbc_lblNom);
		
		moins = new JButton("-");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 1;
		moins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setProduitNombre(model.getProduitNombre() - 1);;
            }
        });
		add(moins, gbc_button_1);
		
		produitNb = new JTextField();
		//textField.setText("0");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(produitNb, gbc_textField);
		produitNb.setColumns(10);
		
		plus = new JButton("+");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 1;
		plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setProduitNombre(model.getProduitNombre() + 1);;
            }
        });
		add(plus, gbc_button);
		
		JLabel lblMax = new JLabel("Max:");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 4;
		gbc_lblMax.gridy = 1;
		add(lblMax, gbc_lblMax);
		
		max = new JButton("0"); // "MAX produits value"
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 5;
		gbc_button_2.gridy = 1;
		max.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setProduitNombre(model.getMaxProduitEntrepot());;
            }
        });
		add(max, gbc_button_2);

	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponent(g);
		max.setText(String.valueOf(model.getMaxProduitEntrepot()));
		produitNb.setText(String.valueOf(model.getProduitNombre()));
		
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		repaint();
	}
	
	
	
	
	
	
	
}

















