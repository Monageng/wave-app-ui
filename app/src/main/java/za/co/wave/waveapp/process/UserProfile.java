package za.co.wave.waveapp.process;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import za.co.wave.waveapp.utils.HTTPHelper;

/**
 * Created by monageng on 2017/07/21.
 */

public class UserProfile {

    HttpURLConnection con;
    public void createUserProfile(JSONObject jsonObject) throws Exception {
        try {
            HTTPHelper httpHelper = new HTTPHelper();

            String path = "/wave-api/rest/UserProfileService/createUserProfile";
            //http://localhost:8080/wave-api/rest/UserProfileService/createUserProfile

            con = httpHelper.getConnection(path);

            con.setDoInput(true);
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");


            OutputStreamWriter streamWriter = new OutputStreamWriter(con.getOutputStream());
            streamWriter.write(jsonObject.toString());
            streamWriter.flush();
            streamWriter.close();

            int responseCode = con.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                String server_response = httpHelper.readStream(con.getInputStream());
            } else {

                throw new Exception("Username already exists");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());

        } catch (Exception e) {
            throw e;
        }

        finally {
            try {
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean authenticate(JSONObject jsonObject) throws Exception {
        try {
            HTTPHelper httpHelper = new HTTPHelper();

            String path = "/wave-api/rest/SecurityService/authenticate";
            con = httpHelper.getConnection(path);

            con.setDoInput(true);
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");

            OutputStreamWriter streamWriter = new OutputStreamWriter(con.getOutputStream());
            streamWriter.write(jsonObject.toString());
            streamWriter.flush();
            streamWriter.close();

            int responseCode = con.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                String server_response = httpHelper.readStream(con.getInputStream());
                return  true;
            } else {
                return  false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }

        finally {
            try {
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
