/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoProductException;
import com.sg.flooringmastery.exception.NoStateException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringOrderService {
    
    List<Order> getOrdersByDate(LocalDate dateOfInterest)throws FlooringPersistenceException;
    Order getOrderByNumber(Integer orderNum, LocalDate dateOfInterest)throws FlooringPersistenceException;
    Order addNewOrder(Order orderWithAllFieldsFilled, LocalDate dateOfInterest)throws FlooringPersistenceException;
    Order addEditedOrder(Order editedOrder, LocalDate oldDate, LocalDate newDate)throws FlooringPersistenceException;
    Order removeOrder(Order removedOrder, LocalDate dateOfInterest)throws FlooringPersistenceException;
    //void saveWork()
    Product getProductByType(Order partialOrder) throws NoProductException, FlooringPersistenceException;
    Tax getTaxByState(Order partialOrder) throws NoStateException, FlooringPersistenceException;
    
    BigDecimal calculateMaterialCost(Order partialOrder, Product productByType)throws NoProductException;
    BigDecimal calculateLaborCost(Order partialOrder, Product productByType) throws NoProductException; //multiplies two Order fields and returns
    BigDecimal calculateCostBeforeTax(BigDecimal materialCost, BigDecimal laborCost);
    BigDecimal calculateTotalTax(Tax taxByState,BigDecimal costBeforeTax) throws NoStateException;
    BigDecimal calculateTotalCost(BigDecimal totalTax, BigDecimal costBeforeTax);
    
    Order fillRemainingOrderFields(Order partialNewOrder) throws NoProductException, NoStateException, FlooringPersistenceException;
    
    public void switchMode(boolean isTrainingMode);
}
