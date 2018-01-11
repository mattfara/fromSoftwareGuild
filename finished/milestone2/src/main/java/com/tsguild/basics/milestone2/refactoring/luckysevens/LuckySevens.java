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
import java.util.Random;

public class LuckySevens {
    
    Random rand = new Random();
    private int dice1, dice2, current$, peakWinnings;
    private int rolls =0;
    private int peakRoll=rolls;
    
    public void setCurrent$(int money){
        this.current$ = money;
        this.peakWinnings = money;
    }
    
    //can we combine these into a single setter?
    public void setDice1(){
        this.dice1 = rand.nextInt(5)+1;
    }
    
    public void setDice2 (){
        this.dice2 = rand.nextInt(5)+1;
    }
    
    public void showResults(){
        System.out.println("You went broke after "+ rolls + " rolls");
        System.out.println("You should have stopped after " +peakRoll+ " rolls when you had $" + peakWinnings + ".");

    }
    
    public void startGame(){
        while (current$ > 0){
            this.setDice1();
            this.setDice2();
            
            rolls++;
            
            this.evaluateRoll(dice1, dice2);
            
        }
        
        this.showResults();
    }
    
    public void evaluateRoll(int dice1, int dice2){
        if (dice1+dice2==7){
                
                current$+=4;
                if (current$>peakWinnings){
                    peakWinnings = current$;
                    peakRoll = rolls;
                }
            } else {
                
                current$-=1;
            }
        }
    }

