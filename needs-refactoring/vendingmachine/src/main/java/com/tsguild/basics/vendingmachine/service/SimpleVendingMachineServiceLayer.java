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

/**
 *
 * @author matt
 */
public interface SimpleVendingMachineServiceLayer {
    
    
    

    //crud pass through methods
    Slot addProduct (int slotNum, Slot slot) throws VendingMachinePersistenceException;
    Slot getProductBySlotNumber(int slotNum)throws ProductNotFoundException, VendingMachinePersistenceException;
    Slot updateProduct(Slot updatedSlot) throws ProductNotFoundException, VendingMachinePersistenceException;
    List<Slot> getAllProducts()throws VendingMachinePersistenceException;
    Slot removeProduct(int slotNum)throws VendingMachinePersistenceException, ProductNotFoundException;

    //basic business logic
    Change returnChange(BigDecimal change);
    Change buyProduct(Slot currentProduct) throws InsufficientFundsException, ProductOutOfStockException, VendingMachinePersistenceException;
    boolean slotsStillOpen() throws VendingMachinePersistenceException;
    boolean isSlotTaken(int slotNum) throws VendingMachinePersistenceException,ProductNotFoundException;
    void getUserMoney(BigDecimal userMoneyJustInserted, BigDecimal moneyAlreadyInMachine)throws VendingMachinePersistenceException;
    
    
    //checking customer usage
    void seeWhetherFundsAreSufficient(Slot slot) throws InsufficientFundsException, VendingMachinePersistenceException;
    void seeWhetherProductInStock(Slot slot) throws ProductOutOfStockException;
    void doesProductExist(int slotNum) throws ProductNotFoundException, VendingMachinePersistenceException;
    
    
    
    
}
