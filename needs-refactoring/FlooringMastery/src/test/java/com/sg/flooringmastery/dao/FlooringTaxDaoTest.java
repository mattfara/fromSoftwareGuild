/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoStateException;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt farabaugh
 */
public class FlooringTaxDaoTest {
    
    FlooringTaxDao taxDao; 
    
    public FlooringTaxDaoTest() throws FlooringPersistenceException {
        this.taxDao = new FlooringTaxDaoFileImpl("test/");
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
     * Test of addTax method, of class FlooringTaxDao.
     */
    @Test
    public void testAddTax() {
    }

    /**
     * Test of removeTax method, of class FlooringTaxDao.
     */
    @Test
    public void testRemoveTax() {
    }

    /**
     * Test of editTax method, of class FlooringTaxDao.
     */
    @Test
    public void testEditTax() {
    }

    /**
     * Test of getTaxByState method, of class FlooringTaxDao.
     */
    @Test
    public void testGetTaxByState() throws Exception {
        
        try {
            taxDao.getTaxByState("XX");
            fail("The expected NoStateException was not thrown!!!!!!");
        } catch(NoStateException ex) {}
        
        Tax testTax = taxDao.getTaxByState("OH");
        
        assertTrue("OH".equals(testTax.getState()));
        assertEquals(new BigDecimal("6.25"), testTax.getTaxRate());
        
    }

    /**
     * Test of getAllStateTaxes method, of class FlooringTaxDao.
     */
    @Test
    public void testGetAllStateTaxes() {
    }
    
}
