/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.userio;

/**
 *
 * @author matt
 */
public class App {
    public static void main(String[] args) {
        //reference is of the interface, not the class that implements it
        UserIO myIo = new UserIOImpl();//could replace this with RealBadUserIO, which allows for two totally different implementations applied to the same main
        
        
        myIo.print("???");
        
        int enteredInt = myIo.readInt("Please enter an int ", 25, 35);
        
        myIo.print("You entered " + enteredInt);
    }
}
