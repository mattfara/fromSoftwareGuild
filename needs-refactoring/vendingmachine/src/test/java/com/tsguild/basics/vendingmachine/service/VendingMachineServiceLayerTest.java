/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.service;

import com.tsguild.basics.vendingmachine.dao.ProductNotFoundException;
import com.tsguild.basics.vendingmachine.dao.VendingMachinePersistenceException;
import com.tsguild.basics.vendingmachine.dto.Change;
import com.tsguild.basics.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matt
 */
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    private VendingMachineServiceLayer dao;
    private VendingMachineServiceLayer auditDao;
    BigDecimal currentMoneyInserted;
    //wiring the test
    public VendingMachineServiceLayerTest() {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", VendingMachineServiceLayerImpl.class);

    }
    
    
    @Test
    public void testAddProduct() throws Exception {
        Slot slot1 = new Slot(1);
        slot1.setProductName("Coke");
        slot1.setPrice(new BigDecimal("125"));
        slot1.setStock(46);
        slot1.setCalories(200);
        slot1.setParentCompany("Coca-Cola");
        slot1.setVolumeInLiters(1);
        service.addProduct(slot1.getSlotNum(), slot1);
        
//        Slot slot2 = new Slot(2);
//        slot2.setProductName("Sprite");
//        slot2.setPrice(new BigDecimal("100"));
//        slot2.setStock(46);
//        dao.addProduct(slot2.getSlotNum(), slot2);
    }
    
    @Test
    public void testGetProductBySlotNumber()throws Exception {
        Slot slot = service.getProductBySlotNumber(1);
        assertNotNull(slot);
        
        try {
            slot = service.getProductBySlotNumber(999);
            fail("Expected ProductNotFoundException not thrown");
        } catch(ProductNotFoundException ex){
            
        }
        
    }
    
    @Test //should be updated
    public void testUpdateProduct() throws Exception {
        Slot oldSlot = service.getProductBySlotNumber(1);
        
        
        service.updateProduct(oldSlot);
    }
    
    @Test
    public void testGetAllProducts() throws Exception {
        assertEquals(2, service.getAllProducts().size());
    }
    
    @Test
    public void testRemoveProduct() throws Exception {
        Slot slot = service.removeProduct(1);
        assertNotNull(slot);
        
        slot = service.removeProduct(999);
        assertNull(slot);
    }
    
    @Test
    public void testReturnChange(){
        Change testChange1 = new Change();
        testChange1.setQuarters(4);
        testChange1.setDimes(1);
        testChange1.setNickels(0);
        testChange1.setPennies(0);
        
        Change testChange2 = service.returnChange(new BigDecimal("110"));
        
        assertEquals(testChange1.getQuarters(), testChange2.getQuarters());
        assertEquals(testChange1.getDimes(), testChange2.getDimes());
        assertEquals(testChange1.getNickels(), testChange2.getNickels());
        assertEquals(testChange1.getPennies(), testChange2.getPennies());
                
    }
    
     
     @Test //test logic that says we can't buy something we haven't put enough money in for//not sure how to write exception catches
     public void testBuyProductWithInsufficientFunds() throws ProductOutOfStockException, ProductNotFoundException,VendingMachinePersistenceException{
         //without enough money
         currentMoneyInserted = new BigDecimal("35");
         try{
             Slot toPurchase = service.getProductBySlotNumber(1);
             service.buyProduct(toPurchase);
             
             fail("Expected InsufficientFundsException was not thrown");
            } catch(InsufficientFundsException e){
                
            }
     }
     @Test
     public void testBuyProductWithSufficentFunds() throws ProductOutOfStockException,ProductNotFoundException,VendingMachinePersistenceException{
         
         //with enough money
         currentMoneyInserted = new BigDecimal("7000");
         try{
            Slot toPurchase = service.getProductBySlotNumber(1);
            service.buyProduct(toPurchase);
            
         } catch(InsufficientFundsException e){
             
         }
     }
     
     @Test
     public void testBuyProductThatDoesNotExist() throws ProductOutOfStockException, InsufficientFundsException, ProductNotFoundException,VendingMachinePersistenceException{    
         
         //non-existent product
         try{
             Slot toPurchase = service.getProductBySlotNumber(33);
             service.buyProduct(toPurchase);
             fail("Expected ProductNotFoundException was not thrown");
         } catch(ProductNotFoundException e){
            
         }
         
     }
     
     
//     @Test
//     public void testCalculateMinCalories() throws Exception{
//         int minCalories = service.calculateMinCalories();
//         assertEquals(20, minCalories);
//     }
//     @Test
//     public void testCalculateMaxCalories()throws Exception{
//         int maxCalories = service.calculateMaxCalories();
//         assertEquals(200, maxCalories);
//     }
     @Test
     public void testCalculateMinPrice()throws Exception{
         BigDecimal minPrice = service.getStats().getMinPrice();
         assertEquals(new BigDecimal("150"), minPrice);
     }
     @Test
     public void testCalculateMaxPrice()throws Exception{
         BigDecimal maxPrice = service.getStats().getMaxPrice();
         assertEquals(new BigDecimal("300"), maxPrice);
     }
     @Test
     public void testCalculateMinVolume()throws Exception{
         double minVolume = service.getStats().getMinVolume();
         assertTrue(0.5 == minVolume);
     }
     @Test
     public void testCalculateMaxVolume()throws Exception{
         double maxVolume = service.getStats().getMaxVolume();
         assertTrue(1 == maxVolume);
     }
//     @Test
//     public void testCalculateAverageProductPrice()throws Exception{
//         BigDecimal avgPrice = service.calculateAverageProductPrice();
//         assertEquals(new BigDecimal("225"), avgPrice);
//     }
//     @Test
//     public void testCalculateAverageCalories()throws Exception{
//         double avgCalories = service.calculateAverageCalories();
//         assertTrue (110 == avgCalories);
//     }
     
     @Test
     public void testShouldBeRestocked() throws Exception{
         Slot lowStockSlot = service.getProductBySlotNumber(2);
         boolean lowStock = service.shouldBeRestocked(lowStockSlot);
         Slot highStockSlot = service.getProductBySlotNumber(1);
         boolean highStock = service.shouldBeRestocked(highStockSlot);
         
         assertTrue(lowStock);
         assertFalse(highStock);
     }

     @Test
     public void testGetProductsUnderAveragePrice()throws Exception{
         List<Slot> productsUnderAverage = service.getProductsUnderAveragePrice();
         Slot firstProductUnder = productsUnderAverage.get(0);
         
         assertEquals("Powerade",firstProductUnder.getProductName());
         assertEquals(15, firstProductUnder.getStock());
     }
    @Test
     public void testGetProductsOverAveragePrice()throws Exception{
         List<Slot> productsOverAverage = service.getProductsOverAveragePrice();
         Slot firstProductUnder = productsOverAverage.get(0);
         
         assertEquals("ExLax",firstProductUnder.getProductName());
         assertEquals(37, firstProductUnder.getStock());
     }
     @Test
     public void testGetDietFriendlyProducts()throws Exception{
         List<Slot> dietFriendlyProducts = service.getDietFriendlyProducts();
         Slot firstDietFriendlyProduct = dietFriendlyProducts.get(0);
         
         assertEquals("ExLax", firstDietFriendlyProduct.getProductName());
         assertEquals(37, firstDietFriendlyProduct.getStock());
     }
     
}
