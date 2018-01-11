/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class TheCount {
    public static void main(String[] args) {
        int userStart, userEnd, userInc;
        Scanner sc = new Scanner(System.in);
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        int lineCount = 0;
        
        System.out.print("Start at: ");
        userStart = sc.nextInt();
        System.out.print("End at: ");
        userEnd = sc.nextInt();
        System.out.print("Count by: ");
        userInc = sc.nextInt();
        
        for (int i=userStart; i<=userEnd; i+=userInc ) {
            if (lineCount > 1) {
                System.out.print(i + " - Ah Ah AH!");
                System.out.println();
                lineCount=0;
            } else {
                System.out.print(i + " ");
                lineCount++;
            }
        }
        
    }
}
