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
public class LovesMe {
    public static void main(String[] args) {
        
        int numPetals = 37;
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
        System.out.println("I knew it all along");
    }
}
// I choose a while loop, since nobody would try to pick a petal off a daisy with no petals 