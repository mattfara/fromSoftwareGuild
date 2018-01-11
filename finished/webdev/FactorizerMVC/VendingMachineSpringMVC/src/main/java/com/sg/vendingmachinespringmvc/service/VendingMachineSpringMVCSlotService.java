/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.controller.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.controller.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.controller.NoSuchItemException;
import com.sg.vendingmachinespringmvc.dao.SearchTerm;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Slot;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public interface VendingMachineSpringMVCSlotService {
    Slot addSlot(Slot slot);
    void removeSlot(long slotNum);
    void updateSlot(Slot slot);
    List<Slot> getAllSlots();
    Slot getSlotBySlotNum(long slotNum);
    List<Slot> searchSlots(Map<SearchTerm, String> criteria);
    Change vend(BigDecimal money, long slotNum)throws InsufficientFundsException, NoItemInventoryException, NoSuchItemException;
    Change abandonOrder(BigDecimal money)throws InsufficientFundsException;
            
    //Change updateChange(Change currentChange);


    public BigDecimal getMoneyIn();

    public String getMessage();
    
    public void setMessage(String message);
    public void setMoneyIn(BigDecimal money);
    public void addMoney(BigDecimal moneyInserted);

    public void setSlotClicked(long slotClicked);
    public long getSlotClicked();
    
    public Change getChange();
    
    
}
