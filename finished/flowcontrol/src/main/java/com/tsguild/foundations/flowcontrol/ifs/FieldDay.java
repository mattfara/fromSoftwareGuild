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
public class FieldDay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userName;
        
        System.out.println("What's your last name, soldier?");
        userName = sc.nextLine().toLowerCase();
        // a.compareTo(b)
        // Alpert.compareTo("Baggins") > 0
        // if a is lower than b, you get positive
        // if a is higher than b in alphabet, you get negative
        System.out.println(userName.compareTo("Baggins"));
        
            if (userName.compareTo("baggins")<0) {
                System.out.println("You're on Red Dragons!");
            } else if (userName.compareTo("baggins")>0 && userName.compareTo("dresden")<0){
                System.out.println("You're on Dark Wizards!");
            } else if (userName.compareTo("dresden")>0 && userName.compareTo("howl")<0){
                System.out.println("You're on Moving Castles");
            } else if (userName.compareTo("howl")>0 && userName.compareTo("potter")<0) {
                System.out.println("You're on Golden Snitches!");
            } else if (userName.compareTo("potter")>0 && userName.compareTo("Vimes")<0){
                System.out.println("You're on Night Guards!");
            } else {System.out.println("You're on Black Holes!");}
        
        
        
    }
}
