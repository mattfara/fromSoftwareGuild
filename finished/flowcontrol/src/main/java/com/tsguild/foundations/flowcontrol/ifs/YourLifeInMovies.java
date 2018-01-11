/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.tsguild.foundations.flowcontrol.ifs;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userAge;
        String userName;
        
        System.out.println("Hey! What's your name?");
        userName = sc.nextLine();
        
        System.out.print("What year were you born?");
        userAge = sc.nextInt();
        
        if (userAge < 2005){
            System.out.println("Pixar's 'UP' came out half a decade ago");
        }  if (userAge < 1995){
            System.out.println("The first Harry Potter came out over 15 years ago");
        }  if (userAge < 2005) {
            System.out.println("Space Jam came out not last decade, but the one before that!");
        }  if (userAge < 1975){
            System.out.println("The original Jurassic Park release is closer to the lunar landing than today");
        } if (userAge < 1975) {
            System.out.println("Mash has been around for almost half a century");
        }
        
        
    }
}
