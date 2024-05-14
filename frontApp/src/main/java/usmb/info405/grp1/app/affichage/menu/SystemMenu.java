package usmb.info405.grp1.app.affichage.menu;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.drawer.component.header.SimpleHeader;
import usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.drawer.component.header.SimpleHeaderData;
import usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.drawer.component.menu.*;
import usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.drawer.component.menu.data.Item;
import usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.drawer.component.menu.data.MenuItem;
import usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.swing.AvatarIcon;
import usmb.info405.grp1.app.ApplicationFrame;
import usmb.info405.grp1.app.ContenantPane;
import usmb.info405.grp1.app.UnderframePanel;
import usmb.info405.grp1.app.affichage.menu.MenuPanel.cmdDarkListener;
import usmb.info405.grp1.app.affichage.menu.MenuPanel.cmdLightListener;


public class SystemMenu extends JPanel  {
	
	private SimpleMenu simpleMenu;
	private static final long serialVersionUID = 1L;
	private ContenantPane contenantPanel;
	private ApplicationFrame frame;
	private String role = UnderframePanel.getRoleFromUserInfo();
	private String username = UnderframePanel.getUsernameFromUserInfo();
	
	
	//Constructeur par defaut avec methode init()
	public SystemMenu(ContenantPane panel, ApplicationFrame frame) {
		this.frame = frame;
		this.contenantPanel = panel;
		
		
		init();
		
	}
	
	private void init() {
		
		setLayout(new MigLayout("wrap,fill", "[fill]", "[grow 0] [fill]" ));
		
		simpleMenu= new SimpleMenu(getMenuOption());
		simpleMenu.setOpaque(false);
		
		//ajout de la scrollBarmenu
		JScrollPane scrollPane = new JScrollPane(simpleMenu);
		
			//retrait background + border
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		
			//passe en flatlaf style
		scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,"" + 
			"trackArc : 999;"
			+ "width: 5;"
			+ "thumbInsets: 0,0,0,0");
		
		/* Création header Menu */
		SimpleHeader header = new SimpleHeader(getHeaderData());
		header.setOpaque(false);
		add(header);
		
		//ajoute le menu à au menuBlur
		add(scrollPane);
		
		
		add(createThemeSelectPanel());
		
	}
	
	// Donnees pour header
	// Static pour le moment à modifier pour afficher les bonnes données selon utilisateur connecté
	private SimpleHeaderData getHeaderData() {
		return new SimpleHeaderData()
				.setTitle(username)
				.setDescription(role)
				.setIcon(new AvatarIcon(getClass().getResource("/usmb/info405/grp1/app/ressources/images/profile.png"), 60,60,999 ));
	}
	
	
private SimpleMenuOption getMenuOption() {
        
        //Création de la liste des items du menu -- utilisation de la librairie drawer -- :
	 SimpleMenuOption res;
		if (role.equals("ADMIN")) {
			res = createAdminMenu();
			
		} else {
			 res = createUserMenu();
		}
		return res;
	
				
				
							
	}

private Component createThemeSelectPanel() {
	JPanel ThemePanel = new JPanel(new MigLayout("insets 5 20 0 120,fill,gap 0"));
	
	JRadioButton LightButton = new JRadioButton("Light");
	LightButton.addActionListener(new cmdLightListener());
	LightButton.setSelected(true);
	MenuPanel.light = LightButton;
	ThemePanel.add(MenuPanel.light);
	
	JRadioButton DarkButton = new JRadioButton("Dark");
	DarkButton.addActionListener(new cmdDarkListener());
	MenuPanel.dark = DarkButton;
	ThemePanel.add(MenuPanel.dark);
	
	
	return ThemePanel;
	
	
	
}
//Composant qui cree un affichage du menu pour les users
private SimpleMenuOption createUserMenu() {
	
	  MenuItem items[]= new MenuItem[] {   
            new Item.Label("Menu"),
            new Item("Consultation du profil", "blabla.png"),
            
            //Titre de la partie Item.Label([nom])

            // nom de l'option du menu avec Item([nom], [img])
            new Item("Réservations", "calendar.svg")
                .subMenu("Effectuer une réservation")
                .subMenu("Réservations effectuées")
            
           
    };
	  return new SimpleMenuOption()
				.setBaseIconPath("/usmb/info405/grp1/app/ressources/menu") //Chemin à corriger avec les bonnes images pour icons
				.setIconScale(0.5f)
				.setMenus(items)
				
				//rendre transparent le fond des options du menu + sous menu
				.setMenuStyle(new SimpleMenuStyle() {
		            @Override
		            public void styleMenuPanel(JPanel panel, int[] index) {
		            	//met fond des panels en transparent
		                panel.setOpaque(true);
		            }
		
		            @Override
		            public void styleMenuItem(JButton menu, int[] index) {
		            	//Mettre fond des boutons transparent
		                menu.setContentAreaFilled(true);
		            }
		        })
				
				//Ajout des events sur le menu 
				.addMenuEvent(new MenuEvent(){ //MenuEvent implements ActionListener et agit comme tel
					@Override
					public void selected(MenuAction menuAction, int[] ints) {
						if (ints.length == 1) {
							int index = ints[0];
							
							if (index == 0) {
								System.out.print("Consulte profil");
								contenantPanel.choosePanel("profil");
								
								
							}
						} else if (ints.length == 2) {
								
							int index = ints[0];
							int subIndex = ints[1];
								
							if (index == 1) {
								if (subIndex == 0) {
									System.out.println("Effectuer une réservation");
									contenantPanel.choosePanel("reservations");
									
										
								} else if (subIndex == 1) {
										System.out.println("Consulter réservations");
										contenantPanel.choosePanel("vosReservations");
									
								}
							} else if (index == 2) {
								if (subIndex == 0) {
									System.out.println("Liste des utilisateurs");
									contenantPanel.choosePanel("gestUtilisateur");
									
								}
							} else if (index == 3) {
								
								if (subIndex == 0) {
									System.out.println("ref produit");
									contenantPanel.choosePanel("gestProduit");
									
								} else if (subIndex == 1) {
									System.out.println("gestion stock");
									contenantPanel.choosePanel("gestEntrepot");
									
								}
							} 
							
							
						} else {
							System.out.print("Menu Unslected");
						}
						
					}
				});
	
}

//Composant qui cree un affichage du menu pour les admins 
private SimpleMenuOption createAdminMenu() {
	
	  MenuItem items[]= new MenuItem[] {
              new Item.Label("Menu"),
              new Item("Consultation du profil", "blabla.png"),
              
              //Titre de la partie Item.Label([nom])

              // nom de l'option du menu avec Item([nom], [img])
              new Item("Réservations", "calendar.svg")
                  .subMenu("Effectuer une réservation")
                  .subMenu("Réservations effectuées"),
              
             
              new Item.Label("Partie Administrateur"),
              new Item("Gestion des utilisateurs", "icon.png")
                  .subMenu("Liste des utilisateurs"),
              new Item("Gestion de l'entrepôt", "icon.png")
                  //nom du sous menu avec subMenu([nom])
                  .subMenu("Gestion références des produits")
                  .subMenu("Gestion stock des produits"),
              new Item("Gestion des réservations", "icon.png")

      };
	  
	  
	  
	  return new SimpleMenuOption()
				.setBaseIconPath("/usmb/info405/grp1/app/ressources/menu") //Chemin à corriger avec les bonnes images pour icons
				.setIconScale(0.5f)
				.setMenus(items)
				
				//rendre transparent le fond des options du menu + sous menu
				.setMenuStyle(new SimpleMenuStyle() {
		            @Override
		            public void styleMenuPanel(JPanel panel, int[] index) {
		            	//met fond des panels en transparent
		                panel.setOpaque(true);
		            }
		
		            @Override
		            public void styleMenuItem(JButton menu, int[] index) {
		            	//Mettre fond des boutons transparent
		                menu.setContentAreaFilled(true);
		            }
		        })
				
				//Ajout des events sur le menu 
				.addMenuEvent(new MenuEvent(){ //MenuEvent implements ActionListener et agit comme tel
					@Override
					public void selected(MenuAction menuAction, int[] ints) {
						if (ints.length == 1) {
							int index = ints[0];
							
							if (index == 0) {
								System.out.print("Consulte profil");
								contenantPanel.choosePanel("profil");
								
							} else if (index == 4) {
								System.out.println("Gest reserv");
								contenantPanel.choosePanel("gestReservations");
							}
							
						} else if (ints.length == 2) {
								
							int index = ints[0];
							int subIndex = ints[1];
								
							if (index == 1) {
								if (subIndex == 0) {
									System.out.println("Effectuer une réservation");
									contenantPanel.choosePanel("reservations");
									
										
								} else if (subIndex == 1) {
										System.out.println("Consulter réservations");
										contenantPanel.choosePanel("vosReservations");
									
								}
							} else if (index == 2) {
								if (subIndex == 0) {
									System.out.println("Liste des utilisateurs");
									contenantPanel.choosePanel("gestUtilisateur");
									
								}
							} else if (index == 3) {
								
								if (subIndex == 0) {
									System.out.println("ref produit");
									contenantPanel.choosePanel("gestProduit");
									
								} else if (subIndex == 1) {
									System.out.println("gestion stock");
									contenantPanel.choosePanel("gestEntrepot");
									
								}
							} 
							
							
						} else {
							System.out.print("Menu Unslected");
						}
						
					}
				});
	
}

//permet le switch des boutons des themes
public static void selectTheme() {
	if (FlatLaf.isLafDark()) {
		MenuPanel.dark.setSelected(false);
		MenuPanel.light.setSelected(true);
		
	} else {
		MenuPanel.dark.setSelected(true);
		MenuPanel.light.setSelected(false);
		
	}
	
}

public 

final class cmdLightListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.SwitchLightTheme();
		selectTheme();
		
		
	}
	
}

final class cmdDarkListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.SwitchDarkTheme();
		selectTheme();
		
	}
	

}


	
	
	//Copyright (c) 2023 Raven Laing
	
}
