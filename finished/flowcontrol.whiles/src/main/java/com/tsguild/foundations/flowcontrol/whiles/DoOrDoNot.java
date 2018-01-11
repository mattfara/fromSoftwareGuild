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

public class DoOrDoNot {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Should I do it? (y/n) ");
        boolean doIt;

        if (input.next().equals("y")) {
            doIt = true; // DO IT!
        } else {
            doIt = false; // DONT YOU DARE!
        }

        boolean iDidIt = false;
        
        while (doIt) {
            iDidIt = true;
            break;
        }
        
            if (doIt && iDidIt) {
                System.out.println("I did it!");
            } else if (!doIt && iDidIt) {
                System.out.println("I know you said not to ... but I totally did anyways.");
            } else {
                System.out.println("Don't look at me, I didn't do anything!");
            }
        
//        do {
//            iDidIt = true;
//            break;
//        } while (doIt);
        
        
/*        while (true){
            
            iDidIt = true;
            
            if (doIt && iDidIt) {
                System.out.println("I did it!");
            } else if (!doIt && iDidIt) {
                System.out.println("I know you said not to ... but I totally did anyways.");
            } else {
                System.out.println("Don't look at me, I didn't do anything!");
            }
            
           break;
        }
*/
    
    }
}

//answer "y" prints "I did it!" and "n" prints "I know you said not to...."
//w/o the do loop, the initially false iDidIt stays false, and answering "n" leads to "Don't look at me..." printing