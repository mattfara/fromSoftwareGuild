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
import java.util.Scanner;

public class MiniZork {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.print("Go to the house, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.print("Look inside or stick your hand in? ");
            action = userInput.nextLine();
            
            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.print("Run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you alive. Possibly a wise choice.");
                }
            } else if (action.equals("stick your hand in")) { 
                System.out.println("The mailbox lid grows teeth and chomps on your hand like a bear trap");
                System.out.println("You feel sad that your arm is in worse shape...");
                System.out.println("Do you weep on the grass or  kick the box?");
                action = userInput.nextLine();
                if (action.equals("kick the box")){
                    System.out.println("The box stem flexes away from your foot");
                    System.out.println("The box coughs enough letters from its gullet to kill you with paper cuts");
                } else {
                    System.out.println("The box has mercy on your soul");
                    System.out.println("You are given the chance to walk away before you bleed out");
                }
            }
        } else if (action.equals("go to the house")) { 
            System.out.println("The door comes off the hinges and crawls like an inchworm toward you");
            System.out.println("You feel startled and begin urinating");
            System.out.println("Do you seize the opportunity to piss on the door or do you proceed to try and knock on it");
            action = userInput.nextLine();
            if (action.equals("proceed to try and knock on it")){
                System.out.println("The door politely stands poised like a cobra");
                System.out.println("The mail slot begins to sing in Latin");
                System.out.println("You sing along");
            } else {
                System.out.println("The door feels insulted");
                System.out.println("It fires its knob at you like a mortar");
                System.out.println("It misses, you run, you live");
            }
                
        
        }
    }
}