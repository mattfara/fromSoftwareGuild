/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringTaxDao;
import com.sg.flooringmastery.dao.FlooringTaxDaoFileImpl;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.exception.NoStateException;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matt farabaugh
 */
public class FlooringTaxServiceTest {
    
    FlooringTaxDao taxDao = new FlooringTaxDaoFileImpl("test/");
    FlooringTaxService taxService = new FlooringTaxServiceImpl(taxDao);
    
    public FlooringTaxServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTaxByState method, of class FlooringTaxService.
     */
    @Test
    public void testGetTaxByState() throws Exception {
        
        Tax testTax = taxService.getTaxByState("OH");
        String testTaxState = testTax.getState();
        
        assertTrue(testTaxState.equals("OH"));
        
        Tax missingState = new Tax("XX");
        missingState.setTaxRate(new BigDecimal("50.00"));
        
        try {
            taxService.getTaxByState(missingState.getState());
            fail("The expected NoStateException was not thrown.");
        } catch (NoStateException e) {
            
        }
        
    }    
    
    /**
     * Test of addTaxByState method, of class FlooringTaxService.
     */
    @Test
    public void testAddTaxByState() {
    }



    /**
     * Test of editTaxByState method, of class FlooringTaxService.
     */
    @Test
    public void testEditTaxByState() {
    }

    /**
     * Test of removeTaxByState method, of class FlooringTaxService.
     */
    @Test
    public void testRemoveTaxByState() {
    }
    
}
