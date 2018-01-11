/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.UniqueOrderNumber;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringUniqueOrderNumberService {
    
    public UniqueOrderNumber getNewUniqueOrderNumber();
    
    public UniqueOrderNumber getCurrentOrderNumber();
    
    public void setCurrentOrderNumber(UniqueOrderNumber orderNumber);
    
}
