/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine.dao;

import com.tsguild.basics.vendingmachine.dto.Slot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    private Slot slot1;
    private Slot slot2;
    private Map<Integer, Slot> slots = new HashMap();
    
    public VendingMachineDaoStubImpl(){
        slot1 = new Slot(1);
        slot1.setPrice(new BigDecimal("300"));
        slot1.setProductName("ExLax");
        slot1.setStock(37);
        slot1.setParentCompany("DiaReal");
        slot1.setCalories(20);
        slot1.setVolumeInLiters(1);
        
        slots.put(slot1.getSlotNum(), slot1);
        
        slot2 = new Slot(2);
        slot2.setPrice(new BigDecimal("150"));
        slot2.setProductName("Powerade");
        slot2.setStock(15);
        slot2.setParentCompany("Coca-Cola");
        slot2.setCalories(200);
        slot2.setVolumeInLiters(0.5);
        
        slots.put(slot2.getSlotNum(), slot2);
        
    }
    
    @Override
    public Slot getProductBySlotNumber(int slotNum) throws ProductNotFoundException {
        if (slotNum == 1){
            return slot1;
        } else if (slotNum == 2){
            return slot2;
        } else {
            throw new ProductNotFoundException("Proudct not found exception");
        } 
    }

//    @Override 
//    public Map<Integer, Slot> returnFullInventory() throws VendingMachinePersistenceException {
//        return slots;
//    }
    
    @Override
    public Slot addProduct(int slotNum, Slot slot) {
        if (slotNum == slot1.getSlotNum()) {
            return slot1;
        } else {
            return null;
        }
    }

//    @Override
//    public Slot buyProduct(Slot slot) {
//        if (slot.getSlotNum() == slot1.getSlotNum()) {
//            return slot1;
//        } else {
//            return null;
//        }
//    }

    @Override
    public Slot removeProduct(int slotNum) {
        if (slotNum == slot1.getSlotNum()) {
            return slot1;
        } else {
            return null;
        }
    }

    @Override
    public List<Slot> getAllProducts() {
        return new ArrayList<Slot>(slots.values());
    }

//    @Override
//    public Slot updateProduct(int slotNum, String productName) {
//        if (slotNum == slot1.getSlotNum()) {
//            return slot1;
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Slot updateProduct(int slotNum, BigDecimal price) {
//        if (slotNum == slot1.getSlotNum()) {
//            return slot1;
//        } else {
//            return null;
//        }
//    }

    @Override
    public Slot updateProduct(Slot slot) {
        if (slots.containsValue(slot)) {
            return slot1;
        } else {
            return null;
        }
    }
    
//    @Override
//    public BigDecimal getPrice(Slot slot){
//        return slot.getPrice();
//    }
//    @Override
//    public boolean doesProductExist(int slotNum) throws ProductNotFoundException{
//        return getProductBySlotNumber(slotNum) != null;
//    }
//    @Override
//    public boolean slotsStillOpen() throws VendingMachinePersistenceException{
//        return true;
//    }
//    @Override
//    public boolean isSlotTaken(int slotNum) throws VendingMachinePersistenceException{
//        return false;
//    }
}
