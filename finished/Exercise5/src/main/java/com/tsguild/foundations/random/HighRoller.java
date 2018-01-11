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
import java.util.Scanner;

public class HighRoller {

    public static void main(String[] args) {

        Random diceRoller = new Random();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("How many sides will your dice have?");
        int userNum = sc.nextInt();
        
        int rollResult = diceRoller.nextInt(userNum) + 1;

        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("You rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        } else if (rollResult == userNum) { 
            System.out.println("You rolled a critical. Nice job!");
        }
    }
}