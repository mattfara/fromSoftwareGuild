/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.UniqueOrderNumber;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringUniqueOrderNumberDao {
    
    public UniqueOrderNumber getCurrentUniqueOrderNumber();
    
    public void setCurrentUniqueOrderNumber(UniqueOrderNumber num);
    
}
