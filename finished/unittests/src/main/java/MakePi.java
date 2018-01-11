/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class MakePi {
        // Return an int array length n containing the first n digits of pi.
    //
    // makePi(3) -> {3, 1, 4}

    public static final int[] PI = {3,1,4,1,5,9,2,6,5,3,5,9};
    
    public int[] makePi(int n) {
        int[] result = new int[n];
        for (int i = 0; i<n; i++){
            result[i]=PI[i];
        }
        return result;
    }
}
