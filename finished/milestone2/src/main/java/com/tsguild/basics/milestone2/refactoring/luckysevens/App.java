/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.milestone2.refactoring.luckysevens;

/**
 *
 * @author matt
 */
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        LuckySevens game = new LuckySevens();
        
        System.out.println("How much money do you want to play with?");
        
        game.setCurrent$(sc.nextInt());
        
        game.startGame();
        
        
        
        
        
    }
}
