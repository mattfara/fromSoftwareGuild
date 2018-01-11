/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.tsguild.foundations.review.one;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class HealthyHearts {
    public static void main(String[] args) {
        //declare/initialize variables
        Scanner sc = new Scanner(System.in);
        int age, maxHeartRate;
        float targetMin, targetMax;
        
        
        //get user input
        System.out.println("What is your age?");
        age = sc.nextInt();
        
        //calculations
        maxHeartRate = 220-age;     
        targetMin = Math.round(maxHeartRate * 0.5f); //1 rounding float
        targetMax = Math.round(maxHeartRate * 0.85f);
        
        //report to user
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute");
        System.out.println("Your target HR zone is " + ((int) targetMin) + " - " + ((int)targetMax) + " beats per minute");//2 cast float to integer for proper output
        
    }
}

//what should be demonstrated here? debugger not necessary since it is so simple -- just run program twice with different starting ages