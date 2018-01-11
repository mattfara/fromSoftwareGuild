/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.dao;

/**
 *
 * @author matt
 */
public class AddressBookDaoException extends Exception{
    	    public AddressBookDaoException(String message) {
	        super(message); //this passes the buck to Exception base class
	    }
	    
	    public AddressBookDaoException(String message, Throwable cause) { //use Throwable 
	        super(message, cause); //this passes the buck to Exception base class
	    }

}
