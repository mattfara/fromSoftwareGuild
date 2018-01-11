/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoProductException;
import com.sg.flooringmastery.dto.Product;
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
public class FlooringProductDaoTest {
    
    FlooringProductDao productDao; 
    
    public FlooringProductDaoTest() throws FlooringPersistenceException {
        this.productDao = new FlooringProductDaoFileImpl("test/");
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
     * Test of addProduct method, of class FlooringProductDao.

    @Test
    public void testAddProduct() {
    }

    /**
     * Test of removeProduct method, of class FlooringProductDao.

    @Test
    public void testRemoveProduct() {
    }

    /**
     * Test of editProduct method, of class FlooringProductDao.

    @Test
    public void testEditProduct() {
    }

    /**
     * Test of getProductByType method, of class FlooringProductDao.
     */
    @Test
    public void testGetProductByType() throws Exception {
        
        try {
            productDao.getProductByType("drift wood");
            fail("The expected NoProductException was not thrown);");
        } catch(NoProductException e) {
            
        }
        
        Product testProduct = productDao.getProductByType("Wood");
        
        assertTrue("Wood".equals(testProduct.getProductType()));
        assertTrue(new BigDecimal("4.75").equals(testProduct.getLaborCostPerSqFt()));
        assertTrue(new BigDecimal("5.15").equals(testProduct.getCostPerSqFt()));
    }

    /**
     * Test of getAllProducts method, of class FlooringProductDao.
    
    @Test
    public void testGetAllProducts() {
    }
    *  
    */
    
}
