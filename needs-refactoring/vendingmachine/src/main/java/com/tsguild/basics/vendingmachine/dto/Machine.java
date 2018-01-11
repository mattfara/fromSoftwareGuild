/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author matt
 */

//how do i load this? need a dao for the machine, a file to read/write, 
public class Machine {
    private int dietCalories;
    int maxStock;
    int lowStock; 
    int maxSlots;    
    

    public int getDietCalories() {
        return dietCalories;
    }

    public void setDietCalories(int dietCalories) {
        this.dietCalories = dietCalories;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public int getLowStock() {
        return lowStock;
    }

    public void setLowStock(int lowStock) {
        this.lowStock = lowStock;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    

    
}
