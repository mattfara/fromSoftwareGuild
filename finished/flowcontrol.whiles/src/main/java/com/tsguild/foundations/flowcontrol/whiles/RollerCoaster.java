/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.whiles;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class RollerCoaster {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("We're going to go on a roller coaster...");
        System.out.println("Let me know when you want to get off...!");

        String keepRiding = "y";
        int loopsLooped = 0;
        while (keepRiding.equals("n")) {
            System.out.println("WHEEEEEEEEEEEEE!!!");
            System.out.print("Want to keep going? (y/n) :");
            keepRiding = userInput.nextLine();
            loopsLooped++;
        }

        System.out.println("Wow, that was FUN!");
        System.out.println("We looped that loop " + loopsLooped + " times!!");
    }
}
//if the condition is changed to check whether keepRiding is "n", then the loop never begins, since keepRiding is initialized as "y"
//...there is an int in front of loopsLooped when it is assigned a value of zero. Once it is assigned a value, future changes to the value, like the increment unary, do not require statement of the data type