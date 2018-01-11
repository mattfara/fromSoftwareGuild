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
public class DogGenetics {
    public static void main(String[] args) {
        //declare/initialize variables
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.print("What is your dog's name? ");
        String dogName = sc.nextLine();
        
        float breed1 = rand.nextInt(100)+1;
        float breed2 = rand.nextInt(100)+1;
        float breed3 = rand.nextInt(100)+1;
        float breed4 = rand.nextInt(100)+1;
        float breed5 = rand.nextInt(100)+1;
        
        System.out.println("breed1: " + breed1);
        
        float sum = breed1+breed2+breed3+breed4+breed5;
        
        
        breed1=(breed1*(100.0f/sum));
        breed2=(breed2*(100.0f/sum));
        breed3=(breed3*(100.0f/sum));
        breed4=(breed4*(100.0f/sum));
        breed5=(breed5*(100.0f/sum));
        
        
        
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
