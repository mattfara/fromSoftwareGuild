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
public class BiggerBetterAdder {
    public static void main(String[] args) {
        
    
    int num1; 
    float num2;
    byte num3;
    float sum;
    Scanner sc = new Scanner(System.in);       
    
    
    System.out.println("Enter the first number");
    num1 = sc.nextInt();
    
    System.out.println("Enter the second number");
    num2 = sc.nextFloat();
    
    System.out.println("Enter the third number");
    num3 = sc.nextByte();
    
    sum = num1+num2+num3;
    
    System.out.println("The three numbers were " +num1+ ", "+ num2 +", and "+num3);
    System.out.println("Their sum is " + sum);
    }
}
