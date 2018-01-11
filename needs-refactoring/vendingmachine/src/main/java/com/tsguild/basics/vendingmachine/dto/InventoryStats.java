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
public class InventoryStats {
    
    BigDecimal userMoneyInserted = new BigDecimal("0");
    
    int minCalories = 0;
    int maxCalories = 0;
    double currentAvgCalories =0;
    
    double minVolume = 0;
    double maxVolume = 0;
    double currentAvgVolume = 0;
    
    BigDecimal minPrice =new BigDecimal("0");
    BigDecimal maxPrice = new BigDecimal("0");
    BigDecimal currentAvgPrice =new BigDecimal("0");
    
    
    
    public double getCurrentAvgCalories() {
        return currentAvgCalories;
    }

    public void setCurrentAvgCalories(double currentAvgCalories) {
        this.currentAvgCalories = currentAvgCalories;
    }

    public double getCurrentAvgVolume() {
        return currentAvgVolume;
    }

    public void setCurrentAvgVolume(double currentAvgVolume) {
        this.currentAvgVolume = currentAvgVolume;
    }

    public BigDecimal getCurrentAvgPrice() {
        return currentAvgPrice;
    }

    public void setCurrentAvgPrice(BigDecimal currentAvgPrice) {
        this.currentAvgPrice = currentAvgPrice;
    }
    
        public int getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(int minCalories) {
        this.minCalories = minCalories;
    }

    public int getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(int maxCalories) {
        this.maxCalories = maxCalories;
    }

    public double getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(double minVolume) {
        this.minVolume = minVolume;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    public BigDecimal getUserMoneyInserted() {
        return userMoneyInserted;
    }

    public void setUserMoneyInserted(BigDecimal userMoneyInserted) {
        this.userMoneyInserted = userMoneyInserted;
    }
    
}
