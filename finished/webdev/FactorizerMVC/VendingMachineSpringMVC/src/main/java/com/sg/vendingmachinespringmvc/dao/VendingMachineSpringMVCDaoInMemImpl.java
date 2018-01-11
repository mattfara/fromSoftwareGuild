/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Slot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public class VendingMachineSpringMVCDaoInMemImpl implements VendingMachineSpringMVCDao {

    private Map<Long, Slot> slotMap = new HashMap();
    private static long slotNumCounter = 0;
    
    //make a constructor that holds all the stub data
    public VendingMachineSpringMVCDaoInMemImpl(){
        Slot slot1 = new Slot();
        slot1.setStock(3);
        slot1.setProductName("Fritos");
        slot1.setPrice(new BigDecimal("0.75"));
        slot1.setCalories(100);
        addSlot(slot1);
        
        Slot slot2 = new Slot();
        slot2.setStock(7);
        slot2.setProductName("Payday");
        slot2.setPrice(new BigDecimal("1.00"));
        slot2.setCalories(200);
        addSlot(slot2);
        
        Slot slot3 = new Slot();
        slot3.setStock(0);
        slot3.setProductName("Whoppers");
        slot3.setPrice(new BigDecimal("1.10"));
        slot3.setCalories(300);
        addSlot(slot3);
        
        Slot slot4 = new Slot();
        slot4.setStock(0);
        slot4.setProductName("Doritos");
        slot4.setPrice(new BigDecimal("2.30"));
        slot4.setCalories(400);
        addSlot(slot4);
        
    }
    
    @Override
    public Slot addSlot(Slot slot) {
        slot.setSlotNum(slotNumCounter);
        slotNumCounter++;
        slotMap.put(slot.getSlotNum(), slot);
        return slot;
    }

    @Override
    public void removeSlot(long slotNum) {
        slotMap.remove(slotNum);
    }

    @Override
    public void updateSlot(Slot slot) {
        slotMap.put(slot.getSlotNum(), slot);
    }

    @Override
    public List<Slot> getAllSlots() {
        Collection<Slot> c = slotMap.values();
        return new ArrayList(c);
    }

    @Override
    public Slot getSlotBySlotNum(long slotNum) {
        return slotMap.get(slotNum);
    }

    @Override
    public List<Slot> searchSlots(Map<SearchTerm, String> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
