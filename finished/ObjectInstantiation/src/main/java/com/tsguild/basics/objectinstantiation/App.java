/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.objectinstantiation;

/**
 *
 * @author matt
 */
public class App {
    public static void main(String[] args) {
        
        Adder myAdder = new Adder();
        
        int sum = Adder.add(5,4);
        System.out.println("Sum is: " +sum);
    }
    
    
}
