/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.whiles;

import java.util.Random;

/**
 *
 * @author matt
 */

public class MaybeItLovesMe {
    public static void main(String[] args) {
        Random genPetals = new Random();
        int numPetals = genPetals.nextInt(76)+14;
        boolean lovesMe = false;
        
        do {
            if (!lovesMe){
                System.out.println("It loves me NOT!");
                numPetals--;
                lovesMe = true;
            } else {
                System.out.println("It LOVES me!");
                numPetals--;
                lovesMe=false;
            }
        } while(numPetals>=0);
        
        if (lovesMe){
            System.out.println("Awwww bummer");
        } else {
            System.out.println("Oh, wow. It REALLY loves me!");
        }
        
    }
}
