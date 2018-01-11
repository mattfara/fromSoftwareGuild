/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.ifs;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class GuessMe {
    public static void main(String[] args) {
        
    
    int rightAns = 7;
    int guess;
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Now you guess.");
    guess = sc.nextInt();
    
    if (guess==rightAns){
        System.out.println("You guessed right");
    } else if (guess > rightAns) {
        System.out.println("You guessed too high. I chose " + rightAns);
    } else {
        System.out.println("You guessed too low I chose " + rightAns);
    }
    
    
    }
    
    
    
    
}
