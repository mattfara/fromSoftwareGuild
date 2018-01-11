/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.random;

/**
 *
 * @author matt
 */
import java.util.Random;
public class CoinFlipper {
    public static void main(String[] args) {
        Random coin = new Random();
        
        System.out.println("Ready, Set, Flip");
        
        if (coin.nextBoolean()){
            System.out.println("You flipped heads");
        } else {
            System.out.println("You flipped tails");
        }
        
    }
}
