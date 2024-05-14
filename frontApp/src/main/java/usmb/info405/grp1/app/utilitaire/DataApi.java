package usmb.info405.grp1.app.utilitaire;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

public final class DataApi {
	public String token;
	private static DataApi instance;

	private DataApi(String token) {
		this.token = token;
	}

	// methode pour récupérer le token et donc se connecter
	public static boolean connecteUser(String username, String password) {
		try {
			PostGetToken apidata = new PostGetToken(username, password);
			instance = new DataApi(apidata.getToken());
			// this.instance = apidata.getToken();
			// System.out.println("Connexion is good");
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("Connexion failed");
			return false;
		}
	}

	public static DataApi getinstance() {
		/**
		 * if (instance == null) { throw new Exception(); }
		 */
		return instance;
	}

	// methode pour recup les utilisateurs depuis l'api
	public JSONArray getUsers() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/utilisateurs";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONObject getUser(int idUser) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/utilisateurs/" + idUser;
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONObject jsonObject = new JSONObject(response.toString());
			connection.disconnect();

			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getAllProduits() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/produits";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getAllCategories() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/categories";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getAllMarques() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/marques";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getAllImages() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/images";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getProduitsEntrepot() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/entrepot";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONObject getProduit(long id) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/produits/" + id;
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONObject jsonObject = new JSONObject(response.toString());
			connection.disconnect();

			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public JSONObject getProduitEntrepot(long id) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/entrepot/" + id;
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONObject jsonObject = new JSONObject(response.toString());
			connection.disconnect();

			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	 public static boolean inscritUser(String nom, String prenom, String email, String role, String username, String password) {
	    	boolean estAdmin = role.contains("ADMIN");
	    	boolean estUser = role.contains("USER");
	        try {
	            String apiUrl = "http://188.165.71.58:8080/api/v1/auth/signup";
	            URL url = new URL(apiUrl);

	            // ouverture de la conn http
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);

	            // donnes json
	            JSONObject userData = new JSONObject();
	            userData.put("nom", nom);
	            userData.put("prenom", prenom);
	            userData.put("email", email);
	            
	            JSONArray appRoles = new JSONArray();
	            
	            if (estUser) {
	            	JSONObject roleObjU = new JSONObject();
	            	roleObjU.put("roleName", "USER");
	            	roleObjU.put("id", 1);
	            	appRoles.put(roleObjU);
	            	
	            }
	            if (estAdmin) {
	            	JSONObject roleObjA = new JSONObject();
	            	roleObjA.put("roleName", "ADMIN");
	            	roleObjA.put("id", 2);
	            	appRoles.put(roleObjA);
	            	
	            }
	            userData.put("appRoles", appRoles);
	            
	            userData.put("username", username);
	            userData.put("password", password);
	            
	            // ecriture json dans le output stream
	            try (OutputStream os = connection.getOutputStream()) {
	                byte[] input = userData.toString().getBytes(StandardCharsets.UTF_8);
	                os.write(input, 0, input.length);
	            }

	            // reponse hhtp
	            int responseCode = connection.getResponseCode();

	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                try (InputStream inputStream = connection.getInputStream();
	                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	                    StringBuilder response = new StringBuilder();
	                    String line;
	                    while ((line = reader.readLine()) != null) {
	                        response.append(line);
	                    }
	                    instance = new DataApi(response.toString().split(":\"")[1].split("\"")[0]);
	                    return true ;
	                    
	                    
	                }
	            } else {
	                System.out.println("code: " + responseCode);
	                return false ;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();	
	        }
			return false;
	    }

	public boolean addProduit(String imageUrl, String refConstructeur, long idCategorie, String description,
			String nomProduit, double caution, long idMarque) {

		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/produits";
			URL url = new URL(apiUrl);

			// ouverture de la conn http
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			connection.setDoOutput(true);

			// données JSON
			JSONObject productData = new JSONObject();

			// Image
			JSONObject imageObj = new JSONObject();
			imageObj.put("id", "1");
			imageObj.put("nom", imageUrl);
			productData.put("image", imageObj);

			// Reférence Constructeur
			productData.put("refConstructeur", refConstructeur);

			// Catégorie
			JSONObject categoryObj = new JSONObject();
			categoryObj.put("id", idCategorie);
			// categoryObj.put("nom", nomCategorie);
			productData.put("categorie", categoryObj);

			// Description
			productData.put("description", description);

			// ID du produit
			// productData.put("id", idProduit);

			// Nom du produit
			productData.put("nom", nomProduit);

			// Caution
			productData.put("caution", caution);

			// Marque
			JSONObject brandObj = new JSONObject();
			brandObj.put("id", idMarque);
			// brandObj.put("nom", nomMarque);
			productData.put("marque", brandObj);

			// écriture JSON dans le output stream
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = productData.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// reponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					System.out.println("succes : " + response.toString());
					return true;
				}
			} else {
				System.out.println("echec code : " + responseCode);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateProduit(long idProduit, String imageUrl, String refConstructeur, long idCategorie,
			String description, String nomProduit, double caution, long idMarque) {

		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/produits/" + idProduit;
			URL url = new URL(apiUrl);

			// ouverture de la conn http
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			connection.setDoOutput(true);

			// données JSON
			JSONObject productData = new JSONObject();

			// Image
			JSONObject imageObj = new JSONObject();
			imageObj.put("id", "1");
			imageObj.put("nom", imageUrl);
			productData.put("image", imageObj);

			// Reférence Constructeur
			productData.put("refConstructeur", refConstructeur);

			// Catégorie
			JSONObject categoryObj = new JSONObject();
			categoryObj.put("id", idCategorie);
			// categoryObj.put("nom", nomCategorie);
			// productData.put("categorie", categoryObj);

			// Description
			productData.put("description", description);

			// ID du produit
			// productData.put("id", idProduit);

			// Nom du produit
			productData.put("nom", nomProduit);

			// Caution
			productData.put("caution", caution);
			// Marque
			JSONObject brandObj = new JSONObject();
			brandObj.put("id", idMarque);
			// brandObj.put("nom", nomMarque);
			productData.put("marque", brandObj);
			productData.put("categorie", categoryObj);
			// écriture JSON dans le output stream
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = productData.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// reponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					System.out.println(connection.getResponseCode());
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addMarque(String nomMarque) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/marques";
			URL url = new URL(apiUrl);

			// ouverture de la conn http
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			connection.setDoOutput(true);

			// données JSON
			JSONObject categorieObj = new JSONObject();

			categorieObj.put("nom", nomMarque);

			// écriture JSON dans le output stream
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = categorieObj.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// reponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addCategorie(String nomCategorie) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/categories";
			URL url = new URL(apiUrl);

			// ouverture de la conn http
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			connection.setDoOutput(true);

			// données JSON
			JSONObject categorieObj = new JSONObject();

			categorieObj.put("nom", nomCategorie);

			// écriture JSON dans le output stream
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = categorieObj.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// reponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addProduitEntrepot(long idProduit) {

		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/entrepot";
			URL url = new URL(apiUrl);

			// Ouverture de la connexion HTTP
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);
			connection.setDoOutput(true);

			// Données JSON
			JSONObject productData = new JSONObject();

			// Image
			// JSONObject imageObj = new JSONObject();
			// imageObj.put("id", idImage);
			// imageObj.put("nom", imageUrl);

			// Produit
			JSONObject productObj = new JSONObject();
			// productObj.put("image", imageObj);
			// productObj.put("refConstructeur", refConstructeur);

			// Catégorie
			// JSONObject categorieObj = new JSONObject();
			// categorieObj.put("id", idCategorie);
			// categorieObj.put("nom", nomCategorie);
			// productObj.put("categorie", categorieObj);

			// Description
			// productObj.put("description", description);

			// ID du produit
			productObj.put("id", idProduit);

			// Nom du produit
			// productObj.put("nom", nomProduit);

			// Caution
			// productObj.put("caution", caution);

			// Marque
			// JSONObject marqueObj = new JSONObject();
			// marqueObj.put("id", idMarque);
			// marqueObj.put("nom", nomMarque);
			// productObj.put("marque", marqueObj);

			// Final JSON
			productData.put("estDispo", true);
			productData.put("produit", productObj);
			System.out.println(productData);

			// Écriture JSON dans le flux de sortie
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = productData.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// Réponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateProduitEntrepot(long idProduit, boolean estDispo) {

        try {
            // URL de l'API
            String apiUrl = "http://188.165.71.58:8080/api/v1/entrepot/" + idProduit;
            URL url = new URL(apiUrl);

            // Ouverture de la connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setDoOutput(true);

            // Données JSON
            JSONObject productData = new JSONObject();


            // Produit
            JSONObject productObj = new JSONObject();
            long idprod = getProduitEntrepot(idProduit).getJSONObject("produit").getInt("id");
            
            productObj.put("id", idprod);

            // Final JSON
            productData.put("estDispo", estDispo);
            productData.put("produit", productObj);
            System.out.println(productData);
            System.out.println(idProduit);

            // Écriture des données JSON dans le flux de sortie
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = productData.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Réponse HTTP
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lecture de la réponse
                try (InputStream inputStream = connection.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return true;
                }
            } else {
                // echec
                return false;
            }
        } catch (Exception e) {
            // exception
            e.printStackTrace();
        }
        // erreur
        return false;
    }

	public JSONObject getCreneau(long id) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/creneaux/" + id;
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONObject jsonObject = new JSONObject(response.toString());
			connection.disconnect();

			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteCreneau(long id) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/creneaux?id=" + id;
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			in.close();

			connection.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public JSONArray getAllCreneau() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/creneaux";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONObject getUserInfo() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/auth/utilisateur";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONObject json = new JSONObject(response.toString());
			connection.disconnect();

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	public boolean saveCreneau(String imageUrl, String refConstructeur, long idCategorie, String description,
			String nomProduit, double caution, long idMarque) {

		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/produits";
			URL url = new URL(apiUrl);

			// ouverture de la conn http
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			connection.setDoOutput(true);

			// données JSON
			JSONObject productData = new JSONObject();

			// Image
			JSONObject imageObj = new JSONObject();
			imageObj.put("id", "1");
			imageObj.put("nom", imageUrl);
			productData.put("image", imageObj);

			// Reférence Constructeur
			productData.put("refConstructeur", refConstructeur);

			// Catégorie
			JSONObject categoryObj = new JSONObject();
			categoryObj.put("id", idCategorie);
			// categoryObj.put("nom", nomCategorie);
			productData.put("categorie", categoryObj);

			// Description
			productData.put("description", description);

			// ID du produit
			// productData.put("id", idProduit);

			// Nom du produit
			productData.put("nom", nomProduit);

			// Caution
			productData.put("caution", caution);

			// Marque
			JSONObject brandObj = new JSONObject();
			brandObj.put("id", idMarque);
			// brandObj.put("nom", nomMarque);
			productData.put("marque", brandObj);
			// écriture JSON dans le output stream
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = productData.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// reponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					System.out.println("succes : " + response.toString());
					return true;
				}
			} else {
				System.out.println("echec code : " + responseCode);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	*/

	public JSONArray getProduitsEntrepotPourProduit(Long idProduit) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/entrepot/produit/dispo/" + idProduit;
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getProduitsEntrepotPourCategorie(Long idCategorie) {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/entrepot/categorie/dispo/" + idCategorie;
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray getProduitsEntrepotDispo() {
		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/entrepot/dispo";
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			JSONArray jsonArray = new JSONArray(response.toString());
			connection.disconnect();

			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveCreneau(long idUser, LocalDateTime dateDebut, LocalDateTime dateFin,
			JSONArray listeIdProduitsEntrepot) {

		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/creneaux";
			URL url = new URL(apiUrl);

			// ouverture de la conn http
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			connection.setDoOutput(true);

			// données JSON
			JSONObject creneauData = new JSONObject();

			System.out.println(getUserInfo());

			creneauData.put("utilisateur", getUserInfo());

			creneauData.put("dateDebut", dateDebut.toString());

			creneauData.put("dateFin", dateFin.toString());

			creneauData.put("produitEntrepots", listeIdProduitsEntrepot);

			System.out.println(creneauData);
			// écriture JSON dans le output stream
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = creneauData.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// reponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					System.out.println("succes : " + response.toString());
					return true;
				}
			} else {
				System.out.println("echec code : " + responseCode);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public JSONArray getAllProduitEntrepotEntreDates(LocalDateTime dateDebut, LocalDateTime dateFin) {

	    try {
	        String apiUrl = "http://188.165.71.58:8080/api/v1/creneaux/dates?dateDebut="+dateDebut+"&dateFin="+dateFin;
	        URL url = new URL(apiUrl);

	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.setRequestProperty("Authorization", "Bearer " + token);

	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            StringBuilder response = new StringBuilder();
	            String inputLine;

	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }

	            in.close();

	            JSONArray jsonArray = new JSONArray(response.toString());
	            connection.disconnect();
	            return jsonArray ;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}
	
	public boolean updateUser(long idUser, JSONObject jsonUser) {
		

		try {
			String apiUrl = "http://188.165.71.58:8080/api/v1/utilisateurs/" + idUser;
			URL url = new URL(apiUrl);

			// ouverture de la conn http
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			connection.setDoOutput(true);
			
			// écriture JSON dans le output stream
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = jsonUser.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// reponse HTTP
			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					System.out.println(connection.getResponseCode());
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}