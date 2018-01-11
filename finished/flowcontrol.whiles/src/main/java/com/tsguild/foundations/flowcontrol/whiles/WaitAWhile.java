package com.tsguild.foundations.flowcontrol.whiles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */

public class WaitAWhile {

    public static void main(String[] args) {

        int timeNow = 11;
        int bedTime = 10;

        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I'll stay up just a liiiiittle longer....");
            timeNow++; // Time passes
        }

        System.out.println("Oh. It's " + timeNow + " o'clock.");
        System.out.println("Guess I should go to bed ...");
    }
}
//if bedTime is 11, then the loop ends when timeNow hits 11
//if bedTime is 10 but timeNow is set to 11, the loop never runs
//if the increment unary is commented out, the loop never ends
