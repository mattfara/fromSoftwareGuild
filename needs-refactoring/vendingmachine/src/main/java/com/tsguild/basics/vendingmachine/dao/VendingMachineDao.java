/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.dao;

import com.tsguild.basics.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public interface VendingMachineDao {
    
    public Slot getProductBySlotNumber(int slotNum) throws ProductNotFoundException,VendingMachinePersistenceException;
    public Slot addProduct(int slotNum, Slot slot)throws VendingMachinePersistenceException;
    //public Slot buyProduct(Slot slot)throws VendingMachinePersistenceException;
    public Slot removeProduct(int slotNum)throws VendingMachinePersistenceException, ProductNotFoundException;
    public List<Slot> getAllProducts()throws VendingMachinePersistenceException;
    public Slot updateProduct(Slot updatedSlot)throws ProductNotFoundException, VendingMachinePersistenceException;
//    public Slot updateProduct(int slotNum, BigDecimal price)throws ProductNotFoundException, VendingMachinePersistenceException;
//    public Slot updateProduct(int slotNum, int stock)throws ProductNotFoundException, VendingMachinePersistenceException;
    //public BigDecimal getPrice(Slot slot) throws VendingMachinePersistenceException;
    //public boolean doesProductExist(int slotNum) throws ProductNotFoundException, VendingMachinePersistenceException;
    //public Map<Integer, Slot> returnFullInventory() throws VendingMachinePersistenceException;
    //public boolean slotsStillOpen() throws VendingMachinePersistenceException;
    //public boolean isSlotTaken(int slotNum) throws VendingMachinePersistenceException;
}
