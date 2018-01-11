/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.exception;

/**
 *
 * @author matt farabaugh
 */
public class NoStateException extends Exception {
    
    public NoStateException (String message) {
        super(message);
    }
    
    public NoStateException (String message, Throwable cause) {
        super(message, cause);
    }         
}
