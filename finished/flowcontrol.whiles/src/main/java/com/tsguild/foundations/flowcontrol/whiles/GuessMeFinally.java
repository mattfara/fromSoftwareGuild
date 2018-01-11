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
import java.util.Random;
import java.util.Scanner;
public class GuessMeFinally {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean negative = rand.nextBoolean();
        int target = rand.nextInt(100) + 1;
        int guesses=0;
        
        if (negative){
            target*=-1;
        }
        
        
        do { 
            System.out.println("Guess between -100 and 100");
            int userGuess = sc.nextInt();
            guesses++;
            
            if (userGuess == target && guesses==1) {
                System.out.println("Wow, nice guess! You psychic?");
                break;
                
            } else if (userGuess == target){
                System.out.println("Finally you got it! It only took you " + guesses + " guesses!");
            }else if (userGuess > target){
                System.out.println("You guessed too high");
            } else {
                System.out.println("Yout guessed too low");
            }
        }
        while (true);
        
    }
    
    
    
    
    
}
