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
public class Slot {
    
    private int slotNum; //acts as key in hashmap, represents one dimension of machine, with stock representing the other dimension
    private int stock;
    private String productName;
    private BigDecimal price;
    private String parentCompany;
    private double volumeInLiters;
    private int calories;

    public String getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(String parentCompany) {
        this.parentCompany = parentCompany;
    }

    public double getVolumeInLiters() {
        return volumeInLiters;
    }

    public void setVolumeInLiters(double volumeInLiters) {
        this.volumeInLiters = volumeInLiters;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    
    
    
        public Slot(int slotNum)   {
            this.slotNum=slotNum;
            //all comments here regard the admin side of the vending machine
            //throws VendingMachineSlotOverstockedException, VendingMachineOutOfSlotsException

//        if (stock<=46) {
//            if (slotsUsed <= 6){
//                this.productName = productName;
//                slotsUsed++;
//                this.slotNum = slotsUsed;
//            } else{
//                throw new VendingMachineOutOfSlotsException("You cannot add another product because all of the slots are occupied.")
//            }
//        } else {
//            throw new VendingMachineSlotOverstockedException("You cannot add so many of one product to a single slot. Consider adding more to another slot.")
//        }
    }

    public int getSlotNum() {
        return slotNum;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    //could use an override here
    @Override //this override is just to demonstrate overrides. normally not a good idea
    public String toString() {
        return "Slot Number : " + getSlotNum() + " Title: " + getProductName() + " Price: " + getPrice() + " Stock: " + getStock();
    }
    
    
    
}
