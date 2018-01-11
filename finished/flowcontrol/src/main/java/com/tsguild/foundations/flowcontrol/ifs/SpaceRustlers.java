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
public class SpaceRustlers {
    public static void main(String[] args) {

        int spaceships = 10;
        int aliens = 25;
        int cows = 100;

        if(aliens > spaceships){
            System.out.println("Vrroom, vroom! Let's get going!");
        } else{
            System.out.println("There aren't enough green guys to drive these ships!");
        }

        if(cows == spaceships){
            System.out.println("Wow, way to plan ahead! JUST enough room for all these walking hamburgers!");
        } else if (cows > spaceships){
            System.out.println("Dangit! I don't how we're going to fit all these cows in here!");
        } else {
            System.out.println("Too many ships! Not enough cows.");
        }
        
        if (aliens>cows){
            System.out.println("Hurrah, we've got the grub! Hamburger party on Alpha Centauri!");
        } else if (cows>=aliens ){
            System.out.println("Oh no! The herds got restless and took over! Looks like _we're_ hamburger now!!");
        }
        
        
        
    }
}
// I assume the question is about the lower if/else if. THe if checks whether the number of cows and spaceships is equal, and if so prints out the given message. If that test fails, the else if runs, which cheks whether  the number of cows is greater than the number of spaceships, and if so prints another message
// if you remove the else from the else if, the second if will check its conditional even if the first if passed its conditional
