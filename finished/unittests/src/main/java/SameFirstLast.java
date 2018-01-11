/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
    // Given an array of ints, return true if the array is length 
    // 1 or more, and the first element and the last element are equal. 
    //
    // sameFirstLast({1, 2, 3}) -> false
    // sameFirstLast({1, 2, 3, 1}) -> true
    // sameFirstLast({1, 2, 1}) -> true
    
public class SameFirstLast {
    public boolean sameFirstLast(int[] numbers) {
        int lastIdx = numbers.length-1;
        return numbers.length > 0 && numbers[0]==numbers[lastIdx];
    }
}
