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
import java.util.Scanner;
public class GuessMeMore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean negative = rand.nextBoolean();
        int target = rand.nextInt(100) + 1;
        if (negative){
            target*=-1;
        }
        
        
        do { 
            System.out.println("Guess between -100 and 100");
            int userGuess = sc.nextInt();
            
            if (userGuess == target) {
                System.out.println("Wow, nice guess! You got it");
                break;
            } else if (userGuess > target){
                System.out.println("You guessed too high");
            } else {
                System.out.println("Yout guessed too low");
            }
        }
        while (true);
        
    }
    
    
    
    
    
}
