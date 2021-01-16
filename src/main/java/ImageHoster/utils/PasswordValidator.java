package ImageHoster.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static boolean isValidPassword(String password)
    {
        if (password == null) {
            System.out.println("Pass is null");
            return false;
        }
        //Regex to check valid password.
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,20}$";
        //Need 1 number , 1 char and 1 special char
        //String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])$";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        System.out.println("Value : "+m.matches());
        if(m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
