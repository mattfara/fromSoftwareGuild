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
public class AllTheTrivia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String question1 = "What nationality was Chopin?";
        String answer1 = "";
        String question2 = "What is the best known international language?";
        String answer2 = "";
        String question3 = "Who cut van Gogh's ear?";
        String answer3 = "";
        String question4 = "Where did Salvdor Dali live?";
        String answer4 = "";
        
        System.out.println("Welcome to the game. You're going to lose. Trust me.");
        System.out.println(question1);
        
        answer1 = sc.nextLine();
        
        System.out.println(question2);
        answer2 = sc.nextLine();
        
        System.out.println(question3);
        answer3 = sc.nextLine();
        
        System.out.println(question4);
        answer4 = sc.nextLine();
        
        
        System.out.println("Wow! So Chopin's nationality was " + answer2 + "!");
        System.out.println("And the best known international language was " + answer1+"? No shit?");
        System.out.println("And you gotta be kidding me. " + answer4 + " cut Dali's ear? Really?");
        System.out.println("Don't pull my leg. You're serious that " + answer3 + " is where Dali lived?");
    }
}
