/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

/**
 *
 * @author matt
 */
public class ForTimesFor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int prod;
        int numRight = 15;
        int userAns;
        float score;
        
        
        System.out.println("What times table shall I receite?");
        num = sc.nextInt();
        
        for (int i = 1; i<16; i++){
            prod = i*num;
            System.out.println("What is " +i + " * " + num +"?");
            userAns = sc.nextInt();
            if (userAns == prod){
                System.out.println("Correct!");
            } else {
                System.out.println("Sorry, no." +i + " times " + num + " is: " + prod);
                numRight--;
            }
            
            
            
        }
        score = (numRight/15.0f)*100;
        System.out.println("You got " + numRight + " points, which is a score of " + score);
    }
}

//System.out.println("Sorry, no." i + " times " + num + " is: " + prod);