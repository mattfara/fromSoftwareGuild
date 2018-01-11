/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class ForTimes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int prod;
        System.out.println("What times table shall I receite?");
        num = sc.nextInt();
        
        for (int i = 1; i<16; i++){
            prod = i*num;
            System.out.println(i + " * " + num + " is: " + prod);
        }
        
    }
}
