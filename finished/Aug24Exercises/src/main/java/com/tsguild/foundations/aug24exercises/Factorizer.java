/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.aug24exercises;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class Factorizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num,sum,factorCount;
        
        sum = 0;
        factorCount = 0;
        System.out.print("What num you wanna factor? ");
        num = sc.nextInt();
        
        
        System.out.println("The factors of " +num+" are ");
        for (int i=1;i<num;i++){
            if (num%i==0){
                System.out.println(i);
                sum+=i;
                factorCount++;
            }
            
        }
        
        if (sum==num){
            System.out.println(num + " is a perfect number");
        } else {System.out.println(num+" is not a perfect number");}
        if (factorCount==1){
            System.out.println(num + " is a prime number");
        } else {System.out.println(num+" is not a prime number");}
        
    }
}
