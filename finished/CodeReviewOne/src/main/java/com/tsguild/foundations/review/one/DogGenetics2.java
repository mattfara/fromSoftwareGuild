/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.review.one;

/**
 *
 * @author matt
 */
import java.util.Scanner;
import java.util.Random;
public class DogGenetics2 {
    public static void main(String[] args) {
        //declare/initialize variables
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.print("What is your dog's name? ");
        String dogName = sc.nextLine();
        
        //algorithm for finding % that fit into 100%
        int breed1 = rand.nextInt(100-4)+1; //1
        int breed2 = rand.nextInt(100-breed1-3)+1;
        int breed3 = rand.nextInt(100-breed2-breed1-2)+1;
        int breed4 = rand.nextInt(100-breed3-breed2-breed1-1)+1;
        int breed5 = 100-breed4-breed3-breed2-breed1; //unpredictable, therefore random?
        /*additional breeds could be added using Nick's idea*/
        
        
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.");
        System.out.println();
        System.out.println(breed1 + "% St. Bernard");
        System.out.println(breed2 + "% Chihuahua");
        System.out.println(breed3 + "% Pug");
        System.out.println(breed4 + "% Common Cur");
        System.out.println(breed5 + "% King Doberman");
        System.out.println();
        System.out.println("Wow, that's QUITE the dog!");
        
    }
}

//no need for debugger w/ breakpoints -- just run a few times to show that it works