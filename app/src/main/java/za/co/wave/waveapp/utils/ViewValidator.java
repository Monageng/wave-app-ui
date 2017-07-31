package za.co.wave.waveapp.utils;

import android.view.View;
import android.widget.EditText;

/**
 * Created by monageng on 2017/05/14.
 */

public class ViewValidator {

    public boolean validateMandatoryEditText(View view) {
        EditText editText = (EditText)view;
        if( editText.getText().toString().trim().isEmpty() ) {
            editText.setError( "Field required!" );
            return false;
        } else {
            return true;
        }
    }
}
