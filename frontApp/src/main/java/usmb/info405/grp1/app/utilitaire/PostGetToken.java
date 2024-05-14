package usmb.info405.grp1.app.utilitaire;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostGetToken {
	String username ;
	String password ;
    public PostGetToken(String u, String p) {
        username = u;
        password = p ;
    }
    
    public String getToken() throws Exception {
        //try {
            String apiUrl = "http://188.165.71.58:8080/api/v1/auth/login";
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String userData = "username=" + URLEncoder.encode(this.username, "UTF-8") +
                    "&password=" + URLEncoder.encode(this.password, "UTF-8");
            //System.out.println("userdata: "+ userData);
            
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = userData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return response.toString().split(":\"")[1].split("\"")[0];
            } else {
            	//System.out.println("error 401");
            	throw new Exception("Error 401, failed to connect");
                //return "code : " + responseCode;
                
        //    }
        //} catch (Exception e) {
         //   e.printStackTrace();
        }
       // return null;
    }
}
