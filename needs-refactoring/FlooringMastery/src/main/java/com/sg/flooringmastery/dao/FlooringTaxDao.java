/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoStateException;
import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringTaxDao {
    
    public Tax addTax(Tax tax);
    
    public Tax removeTax(String state);
    
    public Tax editTax(Tax tax);
    
    public Tax getTaxByState(String state) 
            throws FlooringPersistenceException, NoStateException;
    
    public List<Tax> getAllStateTaxes();
}
