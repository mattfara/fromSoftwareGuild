/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.SearchTerm;
import com.sg.vendingmachinespringmvc.dao.VendingMachineSpringMVCDao;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Slot;
import com.sg.vendingmachinespringmvc.controller.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.controller.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.controller.NoSuchItemException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 *
 * @author matt
 */





public class VendingMachineSpringMVCSlotServiceImpl implements VendingMachineSpringMVCSlotService{
//MOVED these to separate service, but how will this affect calls to API later?
     
    BigDecimal money = new BigDecimal("0");
    long currentItemSelected;
    String message = "Make your selection";
    Change change = new Change(0);
    
    //i like this way - it is simple and explicit -- NO
    @Resource(name="jdbcDao")
    private VendingMachineSpringMVCDao dao;
    
//    @Inject
//    public VendingMachineSpringMVCSlotServiceImpl(VendingMachineSpringMVCDao dao){
//        this.dao=dao;
//    }
    
    
    
    @Override
    public BigDecimal getMoneyIn() {
        return money;
    }
    
    @Override
    public Slot addSlot(Slot slot) {
        return dao.addSlot(slot);
    }

    @Override
    public void removeSlot(long slotNum) {
        dao.removeSlot(slotNum);
    }

    @Override
    public void updateSlot(Slot slot) {
        dao.updateSlot(slot);
    }

    @Override
    public List<Slot> getAllSlots() {
        return dao.getAllSlots();
    }

    @Override
    public Slot getSlotBySlotNum(long slotNum) {
        return dao.getSlotBySlotNum(slotNum);
    }

    @Override
    public List<Slot> searchSlots(Map<SearchTerm, String> criteria) {
        return dao.searchSlots(criteria);
    }
    
    @Override
    public Change vend(BigDecimal money, long slotNum)
    throws InsufficientFundsException, NoItemInventoryException, NoSuchItemException{
        
    Slot slot = dao.getSlotBySlotNum(slotNum);
    
    if (slot == null) {
      throw new NoSuchItemException("We do not carry the requested item");
    }
    
    if (money.compareTo(slot.getPrice()) < 0){
      BigDecimal moneyShort = slot.getPrice().subtract(money);
      throw new InsufficientFundsException("Please deposit: " + moneyShort);
    }
    
    if (slot.getStock()< 1) {
      throw new NoItemInventoryException("SOLD OUT!!!");
    }
    
    slot.setStock(slot.getStock()-1);
    
    dao.updateSlot(slot);
    
    BigDecimal theChange = money.subtract(slot.getPrice());
    
    theChange = theChange.multiply(new BigDecimal(100));
    int pennies = theChange.intValue();
    setMessage("THANK YOU!!!");
    addMoney(new BigDecimal("0"));
    setSlotClicked(0);
    this.change = new Change(pennies);
    return new Change(pennies);
  }
    
  @Override
  public Change abandonOrder(BigDecimal money)throws InsufficientFundsException{
    
    if (money.compareTo(new BigDecimal("0"))==0){
        throw new InsufficientFundsException("No money deposited");
    } // gonna try moving this into the Controller to avoid getting stuck....
    
    money = money.multiply(new BigDecimal(100));
    int pennies = money.intValue();
    
    setMessage("Take your change");
    this.change = new Change(pennies);
    return new Change(pennies);
    
  }

    @Override
    public long getSlotClicked() {
            
        return this.currentItemSelected;
    }

    

    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public void addMoney(BigDecimal moneyInserted) {
        this.money = money.add(moneyInserted);
    }
    
    

    @Override
    public void setSlotClicked(long slotClicked) {
        this.currentItemSelected = slotClicked;
    }

    @Override
    public void setMessage(String message) {
        this.message=message;
    }

    @Override
    public Change getChange() {
        return this.change;
    }

    @Override
    public void setMoneyIn(BigDecimal money) {
        this.money = money;
    }
  
}
