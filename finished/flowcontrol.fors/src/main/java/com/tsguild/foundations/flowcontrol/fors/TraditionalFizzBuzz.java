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
public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        int userChoice;
        int numHits=0;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("How much units fizzing and buzzing do you need in your life?");
        userChoice = sc.nextInt();
        
        for (int i=0; numHits < userChoice; i++ ){
            if (i%15==0 && i>0) {
                System.out.println("Fizzbuzz");
                numHits++;
            } else if (i%5==0 && i>0) {
                System.out.println("buzz");
                numHits++;
            } else if (i%3==0 && i>0) {
                System.out.println("fizz");
                numHits++;
            } else {
                System.out.println(i);
            }
        }
        System.out.println("TRADITION!!!");
    }
}
