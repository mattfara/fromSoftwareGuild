/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringProductDaoFileImpl;
import com.sg.flooringmastery.exception.NoProductException;
import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matt farabaugh
 */
public class ProductServiceTest {
    
    FlooringProductDao productDao = new FlooringProductDaoFileImpl("test/");
    FlooringProductService productService = new FlooringProductServiceImpl(productDao);
    
    public ProductServiceTest() {
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


//    /** arrange, act, assert
//     * Test of addProduct method, of class ProductService.
//     */
//    @Test
//    public void testAddProduct() throws Exception {
//        Product product1 = new Product("Mahogany");
//        product1.setCostPerSqFt(new BigDecimal("0.17"));
//        product1.setLaborCostPerSqFt(new BigDecimal("45.89"));
//        
//        Product addedProduct = productService.addProduct(product1);
//        
//        // call product with get and check fields
//         
//        Product gotProduct;
//        
//        try{
//            gotProduct = productService.getProductByType("Mahogany");
//            assertEquals(new BigDecimal("0.17"), gotProduct.getCostPerSqFt());
//            assertEquals(new BigDecimal("45.89"), gotProduct.getLaborCostPerSqFt());
//            assertEquals("Mahogany", gotProduct.getProductType());
//        } catch(NoProductException ex) {
//             fail("The product couldn't be found");
//        }
//
//    }

//    /** arrange, act, assert
//     * Test of removeProduct method, of class ProductService.
//     */
//    @Test
//    public void testRemoveProduct() throws Exception {
//        Product product1 = new Product("Mahogany");
//        product1.setCostPerSqFt(new BigDecimal("0.17"));
//        product1.setLaborCostPerSqFt(new BigDecimal("45.89"));
//        
//        Product addedProduct = productService.addProduct(product1);
//        
//        Product removedProduct;
//        
//        try {
//            removedProduct = productService.removeProduct(product1.getProductType());
//        } catch (NoProductException e) {
//            fail("The product could not be removed because it was not found (NoProductFoundException).");
//        }
//        
//        try {
//            removedProduct = productService.getProductByType(product1.getProductType());
//            fail("The expected NoProductException not encountered.");
//        } catch (NoProductException e) {
//            assertTrue(true);
//        }
//        
//        
//        
//        
//    }

    /** arrange, act, assert
     * Test of editProduct method, of class ProductService.
     */
//    @Test
//    public void testEditProduct() {
//        
//        Product product1 = new Product("Mahogany");
//        product1.setCostPerSqFt(new BigDecimal("0.17"));
//        product1.setLaborCostPerSqFt(new BigDecimal("45.89"));
//        
//        Product addedProduct = productService.addProduct(product1);    
//        
//        
//        
//    }

    /** arrange, act, assert
     * Test of getAllProducts method, of class ProductService.
     */
    @Test
    public void testGetAllProducts() {
    }

    /** arrange, act, assert
     * Test of getProductByType method, of class ProductService.
     */
    @Test
    public void testGetProductByType() throws Exception {
        
//        Product newProduct = new Product("Wood");
//        newProduct.setCostPerSqFt(new BigDecimal("1.00"));
//        newProduct.setLaborCostPerSqFt(new BigDecimal(".50"));
        
//        Product addedProduct = productService.addProduct(newProduct);
        Product testProduct = productService.getProductByType("Wood");
        String testProductType = testProduct.getProductType();

        assertTrue(testProductType.equals("Wood"));
        
        
        Product missingProduct = new Product("Drift wood");
        missingProduct.setCostPerSqFt(new BigDecimal("1.00"));
        missingProduct.setLaborCostPerSqFt(new BigDecimal(".50"));
        
        // making sure that we can't call that doesn't exist from dao
        try {
            productService.getProductByType(missingProduct.getProductType());
            fail("The expected NoProductException was not thrown.");
        } catch (NoProductException e) {
            
        }
        
                        
    }
      
    
    
}
