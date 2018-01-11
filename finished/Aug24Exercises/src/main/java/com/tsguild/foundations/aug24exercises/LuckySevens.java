/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.aug24exercises;

/**
 *
 * @author matt
 */
import java.util.Random;
import java.util.Scanner;

public class LuckySevens {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int dice1, dice2;
        
        int rolls = 0;
        int peakRoll = rolls;
        
        int current$;
        System.out.print("How much money do you want to play? $");
        current$ = sc.nextInt();
        
        int peakWinnings = current$;
        
        while (current$ > 0){
            dice1 = rand.nextInt(5)+1;
            dice2 = rand.nextInt(5)+1;
            
            rolls++;
            if (dice1+dice2==7){
                
                current$+=4;
                if (current$>peakWinnings){
                    peakWinnings = current$;
                    peakRoll = rolls;
                }
            } else {
                
                current$-=1;
            }
        }
        System.out.println("You're broke after " + rolls + " rolls");
        System.out.println("You should have stopped after " +peakRoll+ " rolls when you had $" + peakWinnings + ".");
        
    }
    
   
}
