package usmb.info405.grp1.app.utilitaire;

public class principale {

	public static void main(String[] args) {
		DataApi ad = DataApi.getinstance();
		//ad.connecteUser("admin","1234");
		//boolean res = ad.inscritUser("adminetuser", "stan", "dojacat@gmail.com","ADMIN USER", "adminetuser", "1234");
		//System.out.println(res);
		//System.out.println(ad.getUsers());
		//System.out.println(ad.addCategorie("Audio-Visuel"));
		//System.out.println(ad.getAllMarques());
		//System.out.println(ad.getAllCategories());
		//System.out.println(ad.getAllProduits());
		//ad.addMarque("Epson");
		System.out.println(ad.getAllProduits());
		System.out.println(ad.addProduit("lien", "85858", 1, "", "bien", 100, 1));
		//System.out.println(ad.addProduitEntrepot(1));
		//System.out.println(ad.getProduitsEntrepot());
		
		//ad.connecteUser("bivina","bivi");
		System.out.println(ad.getAllProduits());
	}

}

//{"id":1,"nom":"Damien","prenom":"Panpan","email":"damien@gmail.com","appRoles":[{"roleName":"USER","id":1}],"username":"damien"}
//{"password":"dodo","nom":"d"éonald","prenom":"trump","email":"usa@gmail.com","appRoles":[{"roleName":"USER"}],"username":"dodo"}