/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringUniqueOrderNumberDao;
import com.sg.flooringmastery.dao.FlooringUniqueOrderNumberDaoInMemImpl;
import com.sg.flooringmastery.dto.UniqueOrderNumber;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matt farabaugh
 */
public class FlooringUniqueOrderNumberServiceTest {
    
    // InMemDao represents Dao Stub
    FlooringUniqueOrderNumberDao orderNumberDao = new FlooringUniqueOrderNumberDaoInMemImpl();
    
    // Pass Dao Stub to orderNumberService constructor
    FlooringUniqueOrderNumberService orderNumberService = 
        new FlooringUniqueOrderNumberServiceImpl(orderNumberDao); 
    
    public FlooringUniqueOrderNumberServiceTest() {
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
     * Test of getNewUniqueOrderNumber method, of class FlooringUniqueOrderNumberService.
     */
    @Test
    public void testGetNewUniqueOrderNumber() {
        
        // We get the old number with the service getter
        UniqueOrderNumber oldNumber = orderNumberService.getCurrentOrderNumber();
        // Pull out the integer value of the order number object
        int oldNumberInt = oldNumber.getOrderNumber();
        
        // We get a new unique order number with the corresponding service method
        UniqueOrderNumber newNumber = orderNumberService.getNewUniqueOrderNumber();
        // Pull out the integer value of the new order number
        int newNumberInt = newNumber.getOrderNumber();
        
        // assert that the new number is the old number plus one
        assertTrue(oldNumberInt + 1 == newNumberInt); 
    }

    /**
     * Test of getCurrentOrderNumber method, of class FlooringUniqueOrderNumberService.
     */
    @Test
    public void testGetCurrentOrderNumber() {
        
        // create a new UON
        UniqueOrderNumber setNum = new UniqueOrderNumber();
        // set its int value
        setNum.setOrderNumber(999);
        
        // call the service to set the current order number object to the one we created
        orderNumberService.setCurrentOrderNumber(setNum);
        // get our number object back from the service
        UniqueOrderNumber testNum = orderNumberService.getCurrentOrderNumber();
        
        // pull out the integer value of the number we got
        int testNumInt = testNum.getOrderNumber();
        // assert that the integer value is the one we gave it
        assertTrue(testNumInt == 999);
        
    }

    /**
     * Test of setCurrentOrderNumber method, of class FlooringUniqueOrderNumberService.
     */
    @Test
    public void testSetCurrentOrderNumber() {
        
        // create a new UON
        UniqueOrderNumber setNum = new UniqueOrderNumber();
        // set its int value
        setNum.setOrderNumber(999);
        
        // call the service to set the current order number object to the one we created
        orderNumberService.setCurrentOrderNumber(setNum);
        // get our number object back from the service
        UniqueOrderNumber testNum = orderNumberService.getCurrentOrderNumber();
        
        // pull out the integer value of the number we got
        int testNumInt = testNum.getOrderNumber();
        // assert that the integer value is the one we gave it
        assertTrue(testNumInt == 999);     
        
    }
    
}
