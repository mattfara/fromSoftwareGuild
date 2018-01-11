/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.milestone2.refactoring.factorizer;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Factorizer yourFactors = new Factorizer();
        Scanner sc = new Scanner(System.in);
        System.out.println("What number would you like to factorize?");
        
        yourFactors.setUserNumber(sc.nextInt());
        yourFactors.findFactors();
        yourFactors.isPerfect();
        yourFactors.isPrime();
        
    }
}
