/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.whiles;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class StayPositive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userNum;
        System.out.print("What's your number? ");
        userNum = sc.nextInt();
        int counter = 1;
        
        while (userNum >= 0) {
            while (counter<=10 && userNum>=0){
                System.out.print(userNum + " ");
                counter++;
                userNum--;
            }
            System.out.println();
            counter=1;
        }
        
        System.out.println("Whew! Better stop there");
    }
}
