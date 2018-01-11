/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class CountXX {
    public int countXX(String str) {
        String search = "xx";
        int matches = 0;
        for (int i = 0; i<str.length()-1; i++){
            String currentTwo = str.substring(i,i+2);
            if (currentTwo.equals(search)){
                matches++;
            }
        }
        return matches;
    }
}
