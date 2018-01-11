/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.random;

/**
 *
 * @author matt
 */
import java.util.Random;
public class FortuneCookie {
    public static void main(String[] args) {
        Random rand = new Random();
        int chance = rand.nextInt(10)+1;
        
        switch (chance){
            case 1: System.out.println("Those aren’t the droids you’re looking for.");
                break;
            case 2: System.out.println("Never go in against a Sicilian when death is on the line!");
                break;
            case 3: System.out.println("Goonies never say die.");
                break;    
            case 4: System.out.println("With great power there must also come — great responsibility.");
                break;
            case 5: System.out.println("Never argue with the data.");
                break;
            case 6: System.out.println("Try not. Do, or do not. There is no try.");
                break;
            case 7: System.out.println("You are a leaf on the wind, watch how you soar.");
                break;
            case 8: System.out.println("Do absolutely nothing, and it will be everything that you thought it could be.");
                break;
            case 9: System.out.println("Kneel before Zod.");
                break;
            case 10: System.out.println("Make it so.");
                break;    
            
        }
    }
}
