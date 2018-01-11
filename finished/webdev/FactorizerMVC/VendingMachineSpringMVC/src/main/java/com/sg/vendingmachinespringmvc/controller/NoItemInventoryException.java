/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

/**
 *
 * @author matt
 */
public class NoItemInventoryException extends Exception {
    
    public NoItemInventoryException(String message){
        super(message);
    }
    
}
