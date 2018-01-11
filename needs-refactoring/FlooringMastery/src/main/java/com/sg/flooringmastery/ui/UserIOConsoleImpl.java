/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author matt farabaugh
 */
public class UserIOConsoleImpl implements UserIO {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public void print(double message) {
        System.out.println(String.valueOf(message));
    }
    
    @Override
    public void print(float message) {
        System.out.println(String.valueOf(message));
    }
    
    @Override
    public void print(int message) {
        System.out.println(String.valueOf(message));
    }
    
    @Override
    public void print(long message) {
        System.out.println(String.valueOf(message));
    }

    @Override
    public double readDouble(String prompt) {
        boolean isValid = false;
        
        double result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Double.parseDouble(userInput);
            isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
        }
        
        return result;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isValid = false;
        
        double result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Double.parseDouble(userInput);
            isValid = true;
            if (result < min || result > max) {
                isValid = false;
                print("That number is out of range. Please try again.");
            }            
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
        }
        
        return result;
    }

    @Override
    public float readFloat(String prompt) {
        boolean isValid = false;
        
        float result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Float.parseFloat(userInput);
            isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
        }
        
        return result;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
                boolean isValid = false;
        
        float result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Float.parseFloat(userInput);
            isValid = true;
            if (result < min || result > max) {
                isValid = false;
                print("That number is out of range. Please try again.");
            }
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }

        }
        
        return result;
    }

    @Override
    public int readInt(String prompt) {
        boolean isValid = false;
        
        int result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Integer.parseInt(userInput);
            isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
        }
        
        return result;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean isValid = false;
        
        int result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Integer.parseInt(userInput);
            isValid = true;
                if (result < min || result > max) {
                    isValid = false;
                    print("That number is out of range. Please try again.");
                }                        
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }

        }
        
        return result;
    }
    
    @Override
    public Integer readInteger(String prompt) {
        boolean isValid = false;
        
        Integer result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Integer.parseInt(userInput);
            isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
        }
        
        return result;    
    }
    
    @Override
    public Integer readInteger(String prompt, int min, int max) {
        boolean isValid = false;
        
        Integer result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Integer.parseInt(userInput);
            isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
            if (result < min || result > max) {
                isValid = false;
                print("That number is out of range. Please try again.");
            }
        }
        
        return result;
    }

    @Override
    public long readLong(String prompt) {
                boolean isValid = false;
        
        long result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Long.parseLong(userInput);
            isValid = true;
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
        }
        
        return result;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean isValid = false;
        
        long result = 0;
        
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = Long.parseLong(userInput);
            isValid = true;
            if (result < min || result > max) {
                isValid = false;
                print("That number is out of range. Please try again.");
            }            
            } catch (NumberFormatException ex) {
                print("That is an invalid number. Please try again.");
            }
        }
        
        return result;
    }

    @Override
    public String readString(String prompt) {

        print(prompt);
        String userInput = sc.nextLine();
        return userInput;
    }
    
    @Override
    public LocalDate readLocalDate(String prompt) {
        
        LocalDate result = LocalDate.now();
        boolean isValid = false;
        while (!isValid) {
            print(prompt);
            String userInput = sc.nextLine();
            try {
            result = LocalDate.parse(userInput);
            isValid = true;
            } catch (DateTimeException ex) {
                print("That is an invalid date (yyyy-MM-dd). Please try again.");
            }
        }        
        
        return result;
    }
        
}
