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
public class LazyTeenager {
    public static void main(String[] args) {
        Random rand = new Random();
        int chance = 5;
        boolean yesNo;
        do {
            yesNo = chance>rand.nextInt(100);
            
            System.out.print("Clean your room!! ");
            if (yesNo){
                System.out.println("FINE I'll CLEAN MY ROOM BUT NO MORE PEAS");
                break;
            } else if (chance == 85) {
                System.out.println("That's IT, I'm doing it!!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
                break;
            } else {
                System.out.println(String.format("(x%s)", chance/5));
                chance+=5;
            }
                
            
            
        } while(true);
        
        
    }
}
// not sure how to model a drop in 5% each round