/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class SimpleSort {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};

        int[] wholeNumbers = new int[24];

        //try to have three counter variables that reference each array
        //what if we keep it safer by using two enhanced for loops?
        int i=0;
        int j=0;
        int k=0;
        //need to stop i from increasing too much
        while (i<12 && j<12){
            
            if (firstHalf[i]<secondHalf[j]){
               wholeNumbers[k]=firstHalf[i];
                i++; k++;
            } else if (firstHalf[i] > secondHalf[j]){
                wholeNumbers[k]=secondHalf[j];
                j++; k++;
            } else {
                wholeNumbers[k]=firstHalf[i];
                wholeNumbers[k+1]=secondHalf[j];
                i++; j++; k+=2;
            }
            
            wholeNumbers[k] = (i<j) ? firstHalf[i] : secondHalf[j];
        }
        
        
        System.out.println("Here ya go, all nice and sorted: ");
        
        for (int num : wholeNumbers){
            System.out.println(num + " ");
        }
        
        
        
        
    }
}
