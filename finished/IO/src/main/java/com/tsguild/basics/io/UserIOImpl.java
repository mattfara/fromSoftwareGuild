/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.io;

/**
 *
 * @author matt
 */

import java.util.Scanner;
public class UserIOImpl implements UserIO {
    
    private Scanner sc = new Scanner(System.in); // do it once up here to make an instance variable; this cuts down on cost of creating a scanner once for every method call
    
    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }
    
    @Override
    public double readDouble(String prompt) {
        boolean isValid = false;
        double result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Double.parseDouble(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isValid = false;
        double result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Double.parseDouble(userInput);
                if (result >= min && result <= max){
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;
    }

    @Override
    public float readFloat(String prompt) {
        boolean isValid = false;
        float result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Float.parseFloat(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;    
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean isValid = false;
        float result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Float.parseFloat(userInput);
                if (result >= min && result <= max){
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;    
    }

    @Override
    public int readInt(String prompt) {
        boolean isValid = false;
        int result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Integer.parseInt(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean isValid = false;
        int result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Integer.parseInt(userInput);
                if (result >= min && result <= max){
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;    
    }

    @Override
    public long readLong(String prompt) {
        boolean isValid = false;
        long result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Long.parseLong(userInput);
                isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean isValid = false;
        long result = 0;
        
        while (!isValid){
            print(prompt);
            String userInput = sc.nextLine();
            
            try {
                result = Long.parseLong(userInput);
                if (result >= min && result <= max){
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again");
            }
            
        }
        return result;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }
}
