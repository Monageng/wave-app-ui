package za.co.wave.waveapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import za.co.wave.waveapp.R;
import za.co.wave.waveapp.process.UserProfile;

public class Login extends AppCompatActivity {

    UserProfile userProfile = new UserProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void doLogin(View view) {
        String username = String.valueOf(((EditText)findViewById(R.id.usernameTxt)).getText());
        String password = String.valueOf(((EditText)findViewById(R.id.passwordTxt)).getText());

        JSONObject userCredentialJson = new JSONObject();
        try {
            userCredentialJson.put("username", username);
            userCredentialJson.put("password", password);
            boolean isAuthenticated = userProfile.authenticate(userCredentialJson);
            if (isAuthenticated) {
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void doRegister(View view) {
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }

}
