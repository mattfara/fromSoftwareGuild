/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Slot;
import com.sg.vendingmachinespringmvc.service.VendingMachineSpringMVCSlotService;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author matt
 */
@Controller
public class SlotController {
    
    @Inject//uses bean name -- more explicit than @Inject and cleaner than method injection
    VendingMachineSpringMVCSlotService service;    
    
    @RequestMapping(value="/showFullPage", method=RequestMethod.GET)
    public String showFullPage(Model model){
        //get the current item selected
        long currentItemSelected = service.getSlotClicked();
        
        //get the current money in
        BigDecimal moneyIn = service.getMoneyIn();
        //get any messages to display
        String message = service.getMessage();
        //get all the slots
        List<Slot> allSlots = service.getAllSlots();
        Change change = service.getChange();
        int quarters = change.getQuarters();
        int dimes = change.getDimes();
        int nickels = change.getNickels();
        int pennies = change.getPennies();
        
        
        // // put these on the page
        model.addAttribute("moneyIn", moneyIn);
        model.addAttribute("currentItem", currentItemSelected);
        model.addAttribute("message", message);
        model.addAttribute("allSlots", allSlots);
        //set change on the model to display on the page
        model.addAttribute("quarters", quarters);
        model.addAttribute("dimes",dimes);
        model.addAttribute("nickels",nickels);
        model.addAttribute("pennies",pennies);
        
        return "fullPage";
        
        //how to keep re-loading a single page without this starting page?
        
    }
    
//    @RequestMapping(value="/insertMoney", method=RequestMethod.GET)
//    public String insertMoney(HttpServletRequest request){
//        //save parameter from jsp as variable
//        BigDecimal moneyInserted = new BigDecimal(request.getParameter("coinClicked"));
//        //pass into service
//        service.addMoney(moneyInserted);
//        return "redirect:/showFullPage";
//    }
    //this way with @RequestParam is better, since it allows for testing
    @RequestMapping(value="/insertMoney", method=RequestMethod.GET)
    public String insertMoney(HttpServletRequest request, @RequestParam String coinClicked){
        BigDecimal moneyValue = new BigDecimal("0");
        switch(coinClicked){
            case "dollar": moneyValue=new BigDecimal("1"); break;
            case "quarter": moneyValue=new BigDecimal(".25"); break;
            case "dime": moneyValue=new BigDecimal(".1"); break;
            case "nickel": moneyValue=new BigDecimal(".05"); break;
            default: moneyValue=new BigDecimal("0"); break;
        }
        
        
        service.addMoney(moneyValue);
        return "redirect:/showFullPage";
    }
    
    @RequestMapping(value="/clickSlot", method=RequestMethod.GET)
    public String clickSlot(HttpServletRequest request){
        //save parameter from jsp as variable
        long slotClicked = Long.parseLong(request.getParameter("slotNum"));
        //pass into service
        service.setSlotClicked(slotClicked);
        return "redirect:/showFullPage";
    }    
    
  @RequestMapping(value="/vend", method=RequestMethod.GET)
  public String vendSlot(HttpServletRequest request, Model model)
    throws InsufficientFundsException, NoItemInventoryException, NoSuchItemException{
      
    BigDecimal money = service.getMoneyIn();
    long slotNum = service.getSlotClicked();

    Change change = service.vend(money, slotNum);
    service.setMoneyIn(new BigDecimal("0"));
    return "redirect:/showFullPage";
  }
    
  @RequestMapping(value="/coinReturn", method=RequestMethod.GET)
  public String abandonOrder(HttpServletRequest request)
    throws InsufficientFundsException{
    BigDecimal money = service.getMoneyIn();
    
    Change change = service.abandonOrder(money);
    service.setMoneyIn(new BigDecimal("0"));
    return "redirect:/showFullPage";
  }
  
}
