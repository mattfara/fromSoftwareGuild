/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.tsguild.foundations.scanner;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class HealthyHearts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int age;
        
        System.out.println("Hey, pal. How old are you?");
        age = sc.nextInt();
        
        int maxHeartRate = 220-age;
        float minHeartRange = maxHeartRate * 0.5f;
        float maxHeartRange = maxHeartRate * 0.85f;
        
        System.out.println("So, the healthy range for your exercises is between " + minHeartRange + " and " + maxHeartRange + ". And the most your heart should be able to handle is " + maxHeartRate + ".");
        
    }
}
