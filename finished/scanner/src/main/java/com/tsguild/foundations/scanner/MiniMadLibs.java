/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.scanner;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class MiniMadLibs {
    public static void main(String[] args) {
        String noun1, adj1, noun2, num, adj2, pNoun1, pNoun2, pNoun3, presentTenseVerb, pastTenseVerb;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("I'm going to need some words and numbers from you.");
        
        System.out.println("Gimme a noun");
        noun1 = sc.nextLine();
        
        System.out.println("An adjective");
        adj1 = sc.nextLine();
        
        System.out.println("Gimme another noun");
        noun2 = sc.nextLine();
        
        System.out.println("Now a number, any number");
        num = sc.nextLine();
        
        System.out.println("Another adjective");
        adj2 = sc.nextLine();
        
        System.out.println("Now a plural noun");
        pNoun1 = sc.nextLine();
        
        System.out.println("Another plural noun");
        pNoun2 = sc.nextLine();
        
        System.out.println("One more");
        pNoun3 = sc.nextLine();
        
        System.out.println("Almost done. Now give me a present tense verb");
        presentTenseVerb = sc.nextLine();
        
        System.out.println("Lastly, a past tense verb");
        pastTenseVerb = sc.nextLine();
        
        System.out.print("Good. You are done. Here it is: ");
        System.out.println(noun1 + " the " + adj1 + " frontier. These are the voyages of the starship " + noun2 + ". Its " +num+ " year mission: to explore strange " + adj2 +" " + pNoun1 + ", to seek out " +adj2 + " " +pNoun2+ " and " +adj2+ " " + pNoun3 + ", to boldly " +presentTenseVerb+ "where no one has " +pastTenseVerb+ "before.");
        
        
        
        
    }
}
