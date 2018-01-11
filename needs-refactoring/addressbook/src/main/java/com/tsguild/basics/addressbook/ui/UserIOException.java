/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.ui;

/**
 *
 * @author matt
 */
public class UserIOException extends Exception{
    public UserIOException(String message) {
        super(message); //this passes the buck to Exception base class
    }
	    
    public UserIOException(String message, Throwable cause) { //use Throwable 
        super(message, cause); //this passes the buck to Exception base class
    }
}
