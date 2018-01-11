/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.service;

import com.tsguild.basics.vendingmachine.dao.ProductNotFoundException;
import com.tsguild.basics.vendingmachine.dao.VendingMachinePersistenceException;
import com.tsguild.basics.vendingmachine.dto.Change;
import com.tsguild.basics.vendingmachine.dto.InventoryStats;
import com.tsguild.basics.vendingmachine.dto.Machine;
import com.tsguild.basics.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
//should re-name this
//would need to change interface, java package, beans, 
public interface VendingMachineServiceLayer extends SimpleVendingMachineServiceLayer {
    //this interface is for machines with more capabilities
    
    
    
    
    
    

    

    
    //business logic
    
    
    
    
    List<Slot> getDietFriendlyProducts() throws VendingMachinePersistenceException;
    //admin methods
    
    boolean shouldBeRestocked(Slot slot)throws VendingMachinePersistenceException;
    
    void calculateMinCalories() throws VendingMachinePersistenceException;
    void calculateMaxCalories() throws VendingMachinePersistenceException;
    void calculateAverageCalories() throws VendingMachinePersistenceException;
    List<Slot> getProductsUnderAverageCalories() throws VendingMachinePersistenceException;
    List<Slot> getProductsOverAverageCalories() throws VendingMachinePersistenceException;
    
    
    void calculateMinVolume() throws VendingMachinePersistenceException;
    void calculateMaxVolume() throws VendingMachinePersistenceException;
    void calculateAverageVolumeInLiters () throws VendingMachinePersistenceException;
    List<Slot> getProductsUnderAverageSize() throws VendingMachinePersistenceException;
    List<Slot> getProductsOverAverageSize() throws VendingMachinePersistenceException;
    
    void calculateMaxPrice() throws VendingMachinePersistenceException;
    void calculateMinPrice() throws VendingMachinePersistenceException;
    void calculateAverageProductPrice() throws VendingMachinePersistenceException;
    List<Slot> getProductsUnderAveragePrice() throws VendingMachinePersistenceException;
    List<Slot> getProductsOverAveragePrice() throws VendingMachinePersistenceException;
    
    void updateInventoryStats() throws VendingMachinePersistenceException;
    
    //getters and setters
    Machine getMachine();
    InventoryStats getStats();
    void setStats(InventoryStats newStats);
} 

