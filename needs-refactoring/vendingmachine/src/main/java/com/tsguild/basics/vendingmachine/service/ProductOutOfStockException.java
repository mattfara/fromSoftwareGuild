/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.service;

/**
 *
 * @author matt
 */
public class ProductOutOfStockException extends Exception {
    public ProductOutOfStockException(String message){
        super(message);
    }
    
    public ProductOutOfStockException(String message, Throwable cause){
        super(message, cause);
    }
}
