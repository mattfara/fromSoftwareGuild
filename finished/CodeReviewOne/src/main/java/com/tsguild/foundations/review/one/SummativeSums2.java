/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.review.one;

/**
 * Going to try with multidimensional arrays this version
 * @author matt
 */
public class SummativeSums2 {//1
    public static void main(String[] args) { //2
        //initialize multidimentional array
        int[][] dataSet = { //3 draw
            { 1, 90, -33, -55, 67, -16, 28, -55, 15 },
            { 999, -60, -77, 14, 160, 301 },
            { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
        140, 150, 160, 170, 180, 190, 200, -99 }
        };
        
        //begin calculations
        for (int i=0; i<dataSet.length; i++){ //4
            int sumOfCurrentSet = sumArray(dataSet[i]); //5
            System.out.println("#" + (i+1) + " Array Sum: " + sumOfCurrentSet); //# need (i+1) so i can be used to reference dataSet AND count arrays's sums in output
        }
    }
    
    public static int sumArray(int[] array){ //6 explain parts of method declaration
        int sum = 0;
        for (int num : array){ //7
            sum+=num;
        }
        return sum;
    }
}

//maybe use debugger here to step into the sumArray method