/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class FirstLast6 {
    public boolean firstLast6(int[] numbers) {
        //int lengthOfArray = numbers.size();
        return numbers[0]==6 || numbers[numbers.length-1]==6;
    }
}
