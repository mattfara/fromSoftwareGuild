/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.dto;

/**
 *
 * @author matt
 */
public class Change {
    private int pennies=0;
    private int nickels=0;
    private int dimes=0;
    private int quarters=0;

    
    
    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }
    
    @Override
    public String toString(){
        return "Quarters: " + getQuarters() + " Dimes " + getDimes() + " Nickels " + getNickels() + " Pennies " + getPennies();
    }
}
