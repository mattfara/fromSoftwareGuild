/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoStateException;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringTaxService {
    
    // create
    public Tax addTaxByState(String state, Tax tax);
    // read
    public Tax getTaxByState(String state)
            throws NoStateException, FlooringPersistenceException;
    // update
    public Tax editTaxByState(String state, Tax tax);
    // delete
    public Tax removeTaxByState(String state);
    
}
