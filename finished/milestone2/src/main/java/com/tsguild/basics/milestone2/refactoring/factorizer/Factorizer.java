/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.milestone2.refactoring.factorizer;

/**
 *
 * @author matt
 */
public class Factorizer {
    
    private int userNumber;
    private int sum=0, factorCount=0;
    
    
    
    //The main app will ask for the user's choice
    public void setUserNumber(int userNum){
        this.userNumber=userNum;
    }
    
    public void findFactors(){
        System.out.println("Here are the factors of your number, friend: ");
        for (int i=1;i<userNumber;i++){
            if (userNumber%i==0){
                System.out.println(i);
                sum+=i;
                factorCount++;
            }
        }
    }
        
    
    public void isPerfect(){
        if (sum==userNumber){
            System.out.println(userNumber + " is a perfect number");
        } else {System.out.println(userNumber+" is not a perfect number");}
    }

    public void isPrime(){
        if (factorCount==1){
            System.out.println(userNumber + " is a prime number");
        } else {System.out.println(userNumber+" is not a prime number");}

    }

}