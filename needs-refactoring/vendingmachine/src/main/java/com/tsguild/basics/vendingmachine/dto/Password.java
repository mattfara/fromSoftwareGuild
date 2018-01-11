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

//NOT SURE IF THIS IS NECESSARY
public class Password {
    
    private int password;

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
        return "Password changed";
    }
    
}
