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
import java.util.Random;
public class BewareTheKraken {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int randNum;
        
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");

        int depthDivedInFt = 0;
        String fish1 = "marlin";
        String fish2 = "angler fish";
        String fish3 = "clown fish";
        
        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
        while(true){
            System.out.println("So far, we've swam " + depthDivedInFt + " feet");
            
            System.out.println("Do you want to stop? (y/n)");
            if (sc.nextLine().equals("y")){
                System.out.println("Then let's surface");
                break;
            }
            
            if(depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }
            
            
            
            // I can swim, really fast! 500ft at a time!
            depthDivedInFt += 1000;
            randNum = rand.nextInt(3)+1;
            switch (randNum){
                case 1: System.out.println("Look a " + fish1 + "!");
                            break;
                case 2: System.out.println("Look a " + fish2 + "!");
                            break;
                case 3: System.out.println("Look a " + fish3 + "!");
                            break;
            }
            
            
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
}

//since the break statement associated with a depth of 20,000 feet is smaller than the condition in the loop, whether the loop condition is the depth of the ocean floor or just true makes difference. The break statement applies.