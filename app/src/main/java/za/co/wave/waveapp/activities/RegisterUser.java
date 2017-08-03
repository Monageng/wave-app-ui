package za.co.wave.waveapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
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

    private void validateView(View view) throws Exception {

      // boolean isValid = viewValidator.validateMandatoryEditText(findViewById(R.id.usernameTxt));
        //viewValidator.validateMandatoryEditText(findViewById(R.id.usernameTxt))? isValid = true  throw new  Exception("e");

        /*EditText editName = (EditText) findViewById(R.id.editName);
        if (editName.getText().length() < 1) {
            errorMessage = errorMessage + " Name is mandatory, \r\n";
        }

        EditText editSurname = (EditText) findViewById(R.id.editSurname);
        if (editSurname.getText().length() < 1) {
            errorMessage = errorMessage + " Surname is mandatory, \r\n";
        }

        EditText editRegNo = (EditText) findViewById(R.id.editRegNo);
        if (editRegNo.getText().length() < 1) {
            errorMessage = errorMessage + " Registration no is mandatory, \r\n";
        }
        EditText editDateOfBirth = (EditText) findViewById(R.id.editDateOfBirth);
        if (editDateOfBirth.getText().length() <  1) {
            errorMessage = errorMessage + " DateOfBirth is mandatory, \r\n";
        }
        String dateInput = editDateOfBirth.getText().toString();
        try {
            Date date = DateUtils.parseToDate(dateInput, DateUtils.PATTERN_YYYY_MM_DD );
        } catch (Exception e) {
            errorMessage = errorMessage + " , " + e.getMessage() + ", \r\n";
        }*/

       // if (!isValid) {
       //     throw new Exception("Validation failed");
        //}
    }

    public void doRegister(View view) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        UserProfile userProfile = new UserProfile();
        try {

            validateView(view);

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
