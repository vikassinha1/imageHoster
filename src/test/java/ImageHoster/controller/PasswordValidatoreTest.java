package ImageHoster.controller;

import ImageHoster.utils.PasswordValidator;
import org.junit.Test;

public class PasswordValidatoreTest {

    //Negative case
    @Test
    public void isValidPasswordTest(){
        String pass1 = "aaaaaaaaabbbb";
        assert (!PasswordValidator.isValidPassword(pass1));
    }

    @Test
    public void isValidPasswordTestRightInput(){
        String rigthPass= "1a@As";
        Boolean result = PasswordValidator.isValidPassword(rigthPass);
        System.out.println(result);
        assert (result);
    }
}
