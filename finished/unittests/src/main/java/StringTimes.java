/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
import java.util.Collections;


public class StringTimes {

    public String stringTimes(String str, int n) {
        try{
            if (n<0) {
                return "You entered a negative number";
            }

            return String.join("", Collections.nCopies(n,str));
        } catch(NumberFormatException e){
            return "You didn't enter an integer";
        }
    }

}
