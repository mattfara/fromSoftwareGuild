/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dto;

import java.math.BigDecimal;

/**
 *
 * @author matt
 */
public class Slot {
    
    private long slotNum; //acts as key in hashmap, represents one dimension of machine, with stock representing the other dimension
    private int stock;
    private String productName;
    private BigDecimal price;
    private String parentCompany;
    private int calories;

    public Slot() {
    }
    
    public Slot(long slotNum)   {
            this.slotNum=slotNum;
    }
    
    public String getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(String parentCompany) {
        this.parentCompany = parentCompany;
    }

    

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public long getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(long slotNum){
        this.slotNum=slotNum;
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
//    @Override //this override is just to demonstrate overrides. normally not a good idea
//    public String toString() {
//        return "Slot Number : " + getSlotNum() + " Title: " + getProductName() + " Price: " + getPrice() + " Stock: " + getStock();
//    }
//    
    
    
}
