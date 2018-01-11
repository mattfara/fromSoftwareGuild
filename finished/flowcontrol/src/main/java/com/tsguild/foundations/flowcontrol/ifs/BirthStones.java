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
public class BirthStones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userMonth;
        System.out.print("Which month's birthstone are you looking to know?");
        userMonth = sc.nextInt();
        
        if (userMonth < 1 || userMonth > 12) {
            System.out.println("Are you human?");
        }  else if (userMonth == 1){
            System.out.println("January's birthstone is garnet");
        } else if (userMonth == 2){
            System.out.println("February's birthstone is amethyst");
        } else if (userMonth == 3){
            System.out.println("March's birthstone is aqua marine");
        } else if (userMonth == 4){
            System.out.println("April's birthstone is diamond");
        } else if (userMonth == 5){
            System.out.println("May's birthstone is emerald");
        } else if (userMonth == 6){
            System.out.println("June's birthstone is pearl");
        } else if (userMonth == 7){
            System.out.println("July's birthstone is ruby");
        } else if (userMonth == 8){
            System.out.println("August's birthstone is peridot");
        } else if (userMonth == 9){
            System.out.println("September's birthstone is sapphire");
        } else if (userMonth == 10){
            System.out.println("October's birthstone is opal");
        } else if (userMonth == 11){
            System.out.println("November's birthstone is topaz");
        } else {
            System.out.println("December's birthstone is turquoise");
        }
        
        
    }
    
    
    
}
