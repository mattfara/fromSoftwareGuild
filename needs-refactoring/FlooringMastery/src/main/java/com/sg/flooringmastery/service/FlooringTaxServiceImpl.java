/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringTaxDao;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoStateException;

/**
 *
 * @author matt farabaugh
 */
public class FlooringTaxServiceImpl implements FlooringTaxService {
    
    FlooringTaxDao taxDao;

    public FlooringTaxServiceImpl(FlooringTaxDao taxDao) {
        this.taxDao = taxDao;
    }
    
    @Override
    public Tax getTaxByState(String state) 
            throws NoStateException, FlooringPersistenceException {
        return taxDao.getTaxByState(state);
        
    }    

    @Override
    public Tax addTaxByState(String state, Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Tax editTaxByState(String state, Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax removeTaxByState(String state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
