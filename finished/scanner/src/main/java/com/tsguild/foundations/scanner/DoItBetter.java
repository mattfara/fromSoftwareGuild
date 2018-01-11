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
public class DoItBetter {
    public static void main(String[] args) {
        int miles;
        int hotdogs;
        int languages;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("So, just how many miles can you run? ");
        miles = sc.nextInt();
        miles = miles*2+1;
        System.out.println("pfffff I can run " + miles);
        
        System.out.println("How about hotdogs? How many can you eat in a sitting?");
        hotdogs = sc.nextInt();
        hotdogs = hotdogs*2+1;
        System.out.println("HA! I can eat " + hotdogs);
        
        System.out.println("Last one and I'll let you go cry. How many languages can you speak?");
        languages = sc.nextInt();
        languages = languages*2+1;
        System.out.println("I'm not trying to make you feel bad, but I can speak " + languages);
        
    }
}
