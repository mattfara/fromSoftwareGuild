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
public class PassingTheTuringTest {
    public static void main(String[] args) {
        String userName;
        String userFavColor;
        String userFavFood;
        int userFavNumber;
        
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println("What is your name?");
        userName = sc.nextLine();
        
        System.out.println("Hello, " + userName+". How nice of you to come. My name is Frunkis.");
        
        System.out.println("So, what is your favorite color?");
        userFavColor = sc.nextLine();
        
        System.out.println(userFavColor+"? Mine too!");
        
        System.out.println("And how about your favorite food?");
        userFavFood = sc.nextLine();
        
        System.out.println("To be honest, I hate " + userFavFood + ", but I won't hold it against you. I like fermented fish.");
        
        System.out.println("I'll let you make up for it. How about your favorite number?");
        userFavNumber = sc.nextInt();
        
        System.out.println("Yes!" + userFavNumber + " is a good one. It's zero for me");
        System.out.println("I gotta run. Maybe we'll talk later");
        
    }
}
