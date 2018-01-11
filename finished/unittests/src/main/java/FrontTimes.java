
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class FrontTimes {
    public String frontTimes(String str, int n) {
        int length = 3;
        if (str.length()<3) {
            length=str.length();
        }
        String firstLetters = str.substring(0, length);
        return String.join("", Collections.nCopies(n,firstLetters));
    }
}
