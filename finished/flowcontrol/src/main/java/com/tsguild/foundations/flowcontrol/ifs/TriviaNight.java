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
public class TriviaNight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userAns;
        int score=0;
        
        System.out.println("It's TRIVIA NIGHT! Are you ready???");
        
        System.out.println("First Question");
        System.out.println("What is the lowest level programming language?");
        System.out.println("1) Source code               2) Assembly Language");
        System.out.println("3) C#                        4) Machine language");
        System.out.print("Your answer: ");
        userAns = sc.nextInt();
        {score++;} if (userAns == 4) 
        
        System.out.println("Second Question");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper              2) Alan Turing");
        System.out.println("3) Charles Babbage           4) Larry Page");
        System.out.print("Your answer: ");
        userAns = sc.nextInt();
        {score++;} if (userAns == 2)
        
        System.out.println("Last Question");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity                              2) Battlestar Galactica");
        System.out.println("3) USS Enterprise                        4) Millenium Falcon");
        System.out.print("Your answer: ");
        userAns = sc.nextInt();
        {score++;} if (userAns == 3)
            
            System.out.println("Your scored a " + score);
            if (score == 0) {System.out.println("You suck");}
            if (score == 3) {System.out.println("You rock");}
            
    }
}
