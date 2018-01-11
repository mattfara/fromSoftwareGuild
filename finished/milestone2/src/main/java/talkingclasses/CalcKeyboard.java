/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkingclasses;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class CalcKeyboard {
    //properties
    Scanner sc = new Scanner(System.in);
    SimpleCalculator myCalc = new SimpleCalculator();
    UserIO io = new UserIOImpl();
    
    double num1, num2, result;
    boolean calculateAgain = true, inputValid = false, divisionByZero=true;
    String operator;
    
    public void runCalculator(){
        io.print("************************************");
        io.print("You can do some calculations with me");
        io.print("************************************");
        while (calculateAgain==true){
            divisionByZero=true;
            while (divisionByZero==true){
                num1 = io.readDouble("Enter a number: ");    
                num2 = io.readDouble("Enter another: ");
               
                io.print("What operation would you like to perform on these beauties?");
                io.print("");
                io.print("Enter + for addition");
                io.print("Enter - for substraction");
                io.print("Enter * for multiplication");
                io.print("Enter / for division");
                
                
                operator = setOperator();
                divisionByZero = dividingByZero();
            }
            result = doCalc();
            io.print("The result is " + result);
            calculateAgain = askCalculateAgain();
        }
        io.print("****************");
        io.print("Thanks for using");
        io.print("****************");
    }
    
    public void showStartMessage(){
        System.out.println("Hello. You can do some simple calculations here");
        System.out.println("");
    }
    
    public boolean askCalculateAgain(){
        System.out.println("Do you want to do some more calculating?");
        String userResponse = sc.next();
        System.out.println("");
        return userResponse.equalsIgnoreCase("yes") || userResponse.equalsIgnoreCase("y");
    }
        
    public String setOperator(){
        
        String anOperator = io.readString("> ");
        
        while (!(anOperator.equals("+") || anOperator.equals("-") || anOperator.equals("*") || anOperator.equals("/"))){
            System.out.println("That's not a valid operation. Try again");
            anOperator = sc.next();   
        }
        return anOperator;
    }
    
    public boolean dividingByZero(){
        if (operator.equals("/") && num2==0){
            io.print("Don't divide by zero, dick");
            io.print("Start again");
            io.print("");
            return true;
        }
        return false;
    }
    
    public double doCalc(){
        switch (operator) {
            case "+":
                return myCalc.add(num1, num2);
            case "-":
                return myCalc.sub(num1, num2);
            case "*":
                return myCalc.mult(num1, num2);
            default:
                return myCalc.div(num1, num2);
        }
    }
    
    
    
}
