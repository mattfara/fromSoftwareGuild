/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.dao;

import com.tsguild.basics.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matt
 */
public class VendingMachineDaoTest {
    
    VendingMachineDao dao = new VendingMachineDaoFileImpl();
    
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before //empty out the dao for testing
    public void setUp() throws Exception{
        List<Slot> slotList = dao.getAllProducts();
        for (Slot currentSlot : slotList){
            dao.removeProduct(currentSlot.getSlotNum());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test //a get shouldn't necessarily reduce any inventory. only a buy should do that. get can be for admin use
    public void testAddAndGetProduct() throws Exception{
        Slot slot = new Slot(1);
        slot.setProductName("Coke");
        slot.setPrice(new BigDecimal("125"));
        slot.setStock(25);
        slot.setCalories(200);
        slot.setParentCompany("Coca-Cola");
        slot.setVolumeInLiters(0.75);
        
        dao.addProduct(slot.getSlotNum(), slot);
        Slot fromDao = dao.getProductBySlotNumber(slot.getSlotNum());
        assertEquals(slot.getPrice(), fromDao.getPrice());
    }
    
//    @Test
//    public void testBuyProduct() throws Exception {
//        //given a slotNum, we should return a Product
//        //the stock should decrement
//        
//        Slot slot = new Slot(1);
//        slot.setProductName("Coke");
//        slot.setPrice(new BigDecimal("125"));
//        slot.setStock(25);
//        dao.addProduct(slot.getSlotNum(), slot);
//        
//        Slot fromDao = dao.buyProduct(slot);
//        
//        assertEquals(24, fromDao.getStock());
//        
//        
//    }
//    
    
    
    @Test
    public void testRemoveProduct()throws Exception{
        Slot slot1 = new Slot(1);
        slot1.setProductName("Coke");
        slot1.setPrice(new BigDecimal("125"));
        slot1.setStock(46);
        slot1.setCalories(200);
        slot1.setParentCompany("Coca-Cola");
        slot1.setVolumeInLiters(0.75);
        dao.addProduct(slot1.getSlotNum(), slot1);
        
        Slot slot2 = new Slot(2);
        slot2.setProductName("Sprite");
        slot2.setPrice(new BigDecimal("100"));
        slot2.setStock(46);
        slot2.setCalories(200);
        slot2.setParentCompany("Coca-Cola");
        slot2.setVolumeInLiters(0.75);
        dao.addProduct(slot2.getSlotNum(), slot2);
        
        dao.removeProduct(slot1.getSlotNum());
        assertEquals(1, dao.getAllProducts().size());
        dao.removeProduct(slot2.getSlotNum());
        assertEquals(0, dao.getAllProducts().size());
    }
    
    @Test
    public void testGetAllProducts() throws Exception {
        Slot slot1 = new Slot(1);
        slot1.setProductName("Coke");
        slot1.setPrice(new BigDecimal("125"));
        slot1.setStock(46);
        slot1.setCalories(200);
        slot1.setParentCompany("Coca-Cola");
        slot1.setVolumeInLiters(0.75);
        dao.addProduct(slot1.getSlotNum(), slot1);
        
        Slot slot2 = new Slot(2);
        slot2.setProductName("Sprite");
        slot2.setPrice(new BigDecimal("100"));
        slot2.setStock(46);
        slot2.setCalories(200);
        slot2.setParentCompany("Coca-Cola");
        slot2.setVolumeInLiters(0.75);
        dao.addProduct(slot2.getSlotNum(), slot2);
        
        assertEquals(2, dao.getAllProducts().size());
    }
    
    @Test
    public void testUpdateProduct() throws Exception{
        Slot slot1 = new Slot(1);
        slot1.setProductName("Coke");
        slot1.setPrice(new BigDecimal("125"));
        slot1.setStock(46);
        slot1.setCalories(200);
        slot1.setParentCompany("Coca-Cola");
        slot1.setVolumeInLiters(0.75);
        dao.addProduct(slot1.getSlotNum(), slot1);
        
        Slot fromDao = dao.getProductBySlotNumber(slot1.getSlotNum());
        fromDao.setPrice(new BigDecimal("150"));
        fromDao.setStock(26);
        
        Slot updatedSlot = dao.updateProduct(fromDao);
        //dao.addProduct(fromDao.getSlotNum(), fromDao);
        
        
        assertEquals(new BigDecimal("150"), updatedSlot.getPrice());
        assertEquals(26, updatedSlot.getStock());
        
        
    }
}
