package za.co.wave.waveapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by monageng on 2017/08/03.
 */

public class UserProfileValidator {
    private static  String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validPassord(String password) {
        return true;
    }

    public static boolean isEmailValid(String emailAddress) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(emailAddress);
            return matcher.matches();

    }
}
