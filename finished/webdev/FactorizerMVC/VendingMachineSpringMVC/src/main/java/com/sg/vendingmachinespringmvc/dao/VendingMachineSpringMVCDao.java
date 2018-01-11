/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Slot;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public interface VendingMachineSpringMVCDao {
    
    Slot addSlot(Slot slot);
    void removeSlot(long slotNum);
    void updateSlot(Slot slot);
    List<Slot> getAllSlots();
    Slot getSlotBySlotNum(long slotNum);
    
    List<Slot> searchSlots(Map<SearchTerm, String> criteria);
    
}
