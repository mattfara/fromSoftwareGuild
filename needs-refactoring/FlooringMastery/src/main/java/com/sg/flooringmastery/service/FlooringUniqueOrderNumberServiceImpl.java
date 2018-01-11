/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringUniqueOrderNumberDao;
import com.sg.flooringmastery.dto.UniqueOrderNumber;

/**
 *
 * @author matt farabaugh
 */
public class FlooringUniqueOrderNumberServiceImpl implements FlooringUniqueOrderNumberService {

    FlooringUniqueOrderNumberDao orderNumberDao;
    
    public FlooringUniqueOrderNumberServiceImpl(FlooringUniqueOrderNumberDao orderNumberDao) {
        this.orderNumberDao = orderNumberDao;
    }
    
    @Override
    public UniqueOrderNumber getNewUniqueOrderNumber() {
        // create a new orderNumber object and set it equal to the current order number from dao
        UniqueOrderNumber orderNumber = orderNumberDao.getCurrentUniqueOrderNumber();
        // extract integer number from current order number
        Integer num = orderNumber.getOrderNumber();
        // increment the integer
        num++;
        // set the current order number object's value to the incremented value
        orderNumber.setOrderNumber(num);
        
        orderNumberDao.setCurrentUniqueOrderNumber(orderNumber);  // not redundant !!!!!
        
        return orderNumber;     
    }

    @Override
    public UniqueOrderNumber getCurrentOrderNumber() {
        return orderNumberDao.getCurrentUniqueOrderNumber();
    }

    @Override
    public void setCurrentOrderNumber(UniqueOrderNumber orderNumber) {
        orderNumberDao.setCurrentUniqueOrderNumber(orderNumber);
    }
    
}
