package za.co.wave.waveapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import org.json.JSONException;
import org.json.JSONObject;

import za.co.wave.waveapp.R;
import za.co.wave.waveapp.*;
import za.co.wave.waveapp.process.UserProfile;
import za.co.wave.waveapp.utils.DialogBoxUtil;
import za.co.wave.waveapp.utils.UserProfileValidator;
import za.co.wave.waveapp.utils.ViewValidator;

public class RegisterUser extends AppCompatActivity {

    private  String errorMessage = "";
    ViewValidator viewValidator = new ViewValidator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public void home(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private boolean validateView(View view) throws Exception {
        boolean isValid = true;
        ((EditText)findViewById(R.id.emailAddressTxt)).setError(null);
        ((EditText)findViewById(R.id.passwordTxt)).setError(null);
        // Validate password
        String password = String.valueOf(((EditText)findViewById(R.id.passwordTxt)).getText());
        if(!UserProfileValidator.isPasswordValid(password)) {
            isValid = false;
            ((EditText)findViewById(R.id.passwordTxt)).setError("Password must have atleast a lower case\n" +
                    " , an upper case letter \n" +
                    " , a special character \n" +
                    " , atleast 8 characters\n");
        }
        String email = String.valueOf(((EditText)findViewById(R.id.emailAddressTxt)).getText());

        if(!email.isEmpty() && !UserProfileValidator.isEmailValid(email.trim().toLowerCase())) {
            isValid = false;
            ((EditText)findViewById(R.id.emailAddressTxt)).setError("Email address is invalid");
        }
        return isValid;
    }

    public void doRegister(View view) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        UserProfile userProfile = new UserProfile();
        try {

            if (validateView(view)) {
                String username = String.valueOf(((EditText)findViewById(R.id.usernameTxt)).getText());
                String password = String.valueOf(((EditText)findViewById(R.id.passwordTxt)).getText());
                String name = String.valueOf(((EditText)findViewById(R.id.nameTxt)).getText());
                String surname = String.valueOf(((EditText)findViewById(R.id.surnameTxt)).getText());
                String dateOfBirth = String.valueOf(((EditText)findViewById(R.id.dateOfBirthTxt)).getText());
                String race = String.valueOf(((EditText)findViewById(R.id.raceTxt)).getText());
                String cellNumber = String.valueOf(((EditText)findViewById(R.id.cellNumberTxt)).getText());
                String email = String.valueOf(((EditText)findViewById(R.id.emailAddressTxt)).getText());



                String role = null;
                boolean isArtistSelected = ((RadioButton)findViewById(R.id.artistRadio)).isSelected();
                if (isArtistSelected) {
                    role = "ARTIST";
                } else {
                    role = "FAN";
                }

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("surname", surname);
                jsonObject.put("dateOfBirth",dateOfBirth);
                jsonObject.put("age","20");
                jsonObject.put("race",race);
                jsonObject.put("cellNumber", cellNumber);
                jsonObject.put("emailAddress", email);
                jsonObject.put("role", role);


                JSONObject addressJson = new JSONObject();
                addressJson.put("country", "South Africa");
                addressJson.put("province","Gauteng");
                addressJson.put("city", "Midrand");
                jsonObject.put("address", addressJson);

                JSONObject userCredentialJson = new JSONObject();
                userCredentialJson.put("username", username);
                userCredentialJson.put("password", password);
                jsonObject.put("userCredential",userCredentialJson);
                userProfile.createUserProfile(jsonObject);
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }else {
                //DialogBoxUtil.createOKDialogBox(this, e.getMessage());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            DialogBoxUtil.createOKDialogBox(this, e.getMessage());
            /*final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(e.getMessage());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    });
            alertDialog.show(); */

        }


    }
}
