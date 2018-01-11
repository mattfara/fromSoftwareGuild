/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.random;

/**
 *
 * @author matt
 */
import java.util.Random;

public class ALittleChaos {

    public static void main(String[] args) {

        Random randomizer = new Random();

        System.out.println("Random can make integers: " + randomizer.nextInt());
        System.out.println("Or a float: " + randomizer.nextFloat());
        System.out.println("Or even a boolean: " + randomizer.nextBoolean());

        int num = randomizer.nextInt(100);

        System.out.println("You can store a randomized result: " + num);
        System.out.println("And use it over and over again: " + num + ", " + num);

        System.out.println("Or just keep generating new values");
        System.out.println("Here's a bunch of numbers from 0 - 500: ");

        System.out.print(randomizer.nextInt(501) + ", ");
        System.out.print(randomizer.nextInt(501) + ", ");
        System.out.print(randomizer.nextInt(501) + ", ");
        System.out.print(randomizer.nextInt(501) + ", ");
        System.out.print(randomizer.nextInt(501) + ", ");
        System.out.println(randomizer.nextInt(501));
        
        int r = randomizer.nextInt(50) + 50;
        System.out.println("This is what happens when we add 50 to a random number from 0 to 49: " + r);
        System.out.print("And we do it with a bunch of randoms: ");
        System.out.print(String.format("%s, ", randomizer.nextInt(50)+50));
        System.out.print(String.format("%s, ", randomizer.nextInt(50)+50));
        System.out.print(String.format("%s, ", randomizer.nextInt(50)+50));
        //a number btw 0 and 49 is added to the constant, 50
        
    }
}