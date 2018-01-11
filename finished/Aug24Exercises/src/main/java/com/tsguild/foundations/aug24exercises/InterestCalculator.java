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
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;
public class InterestCalculator {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        
        
        
        BigDecimal interest = readBigDecimal("What is your interest rate? ");
        final int YEAR = 2017;
        sc.nextLine();
        
        BigDecimal balance = readBigDecimal("How much money? ");
        
        int currentYear = YEAR;
        
        System.out.print("How many years do you want to invest? ");
        int years = sc.nextInt();
        
        
        System.out.print("Will your interst rate compound quarterly, monthly, or daily? ");
        String choice; 
        choice = sc.next();
        
        BigDecimal periods;
        
        if (choice.equals("quarterly")){
            periods = new BigDecimal("4");
            interest = interest.divide(periods, 10, RoundingMode.HALF_UP);
        } else if (choice.equals("monthly")){
            periods = new BigDecimal("12");
            interest = interest.divide(periods, 10, RoundingMode.HALF_UP);
        } else {
            periods = new BigDecimal("365");
            interest = interest.divide(periods, 10, RoundingMode.HALF_UP);
        }
        BigDecimal oneHundred = new BigDecimal("100");
        BigDecimal bigInterest = interest.divide(oneHundred);
        BigDecimal multiplier = new BigDecimal("1").add(bigInterest);
        
        
        
        for (int i = 0; i < years; i++) {
            System.out.println("Year: " + currentYear);
            System.out.println("Balance: $" + balance);
            BigDecimal currentBalance = balance.setScale(2, BigDecimal.ROUND_HALF_UP);
            for (int j = 0; j < periods.intValue(); j++) {
                balance = balance.multiply(multiplier).setScale(2, BigDecimal.ROUND_HALF_UP);
               
            }
            ++currentYear;
            
            System.out.println("End of year balance: $" + balance);
            System.out.println("Your interest earned was $" + (balance.subtract(currentBalance)));
        }
    }
    
    public static BigDecimal readBigDecimal(String prompt){
        boolean isValid = false;
        while (!isValid){
            try{
                BigDecimal big = new BigDecimal(readString(prompt));
                return big;
            } catch(NumberFormatException ex){
                println("That's not a valid number");
            }
        }
        return null;
    }
    
    public static String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }
    
    public static void println(String message) {
        System.out.println(message);
    }
    
    public static void print(String message) {
        System.out.print(message);
    }
    
}
