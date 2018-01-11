/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringOrderDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringOrderDaoInMemImpl;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoProductException;
import com.sg.flooringmastery.exception.NoStateException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public class FlooringOrderServiceImpl implements FlooringOrderService {
    
    FlooringOrderDao orderDao;
    FlooringProductService productService;
    FlooringTaxService taxService;
    FlooringUniqueOrderNumberService uniqueOrderNumService;

    public FlooringOrderServiceImpl(FlooringOrderDao orderDao, FlooringProductService productService,
            FlooringTaxService taxService, FlooringUniqueOrderNumberService uniqueOrderNumService) {
        this.orderDao = orderDao;
        this.productService = productService;
        this.taxService = taxService;
        this.uniqueOrderNumService = uniqueOrderNumService;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate dateOfInterest) throws FlooringPersistenceException {
       return orderDao.getAllOrdersByDate(dateOfInterest);
    }

    @Override
    public Order getOrderByNumber(Integer orderNum, LocalDate dateOfInterest) throws FlooringPersistenceException {
        return orderDao.getOrderByNum(orderNum, dateOfInterest);
    }

    @Override
    public Order addNewOrder(Order orderWithAllFieldsFilled, LocalDate dateOfInterest) throws FlooringPersistenceException {
        return orderDao.addOrder(orderWithAllFieldsFilled, dateOfInterest);
    }

    @Override
    public Order addEditedOrder(Order editedOrder, LocalDate oldDate, LocalDate newDate) throws FlooringPersistenceException {
        return orderDao.editOrder(editedOrder, oldDate, newDate);
    }

    @Override
    public Order removeOrder(Order removedOrder, LocalDate dateOfInterest) throws FlooringPersistenceException {
        return orderDao.removeOrder(removedOrder.getOrderNumber(), dateOfInterest);
    }

    @Override
    public Product getProductByType(Order partialOrder) throws NoProductException, FlooringPersistenceException {
           String productType = partialOrder.getProductType();
           Product productByType = productService.getProductByType(productType);
           return productByType;
    }

    @Override
    public Tax getTaxByState(Order partialOrder) throws NoStateException, FlooringPersistenceException {
           String state = partialOrder.getState();
           Tax taxByState = taxService.getTaxByState(state);
           return taxByState;
    }

    @Override
    public BigDecimal calculateMaterialCost(Order partialOrder, Product productByType) throws NoProductException {
    
        BigDecimal costPerSqFt = productByType.getCostPerSqFt();
        BigDecimal flooringArea = partialOrder.getFlooringArea();
        BigDecimal materialCost = costPerSqFt.multiply(flooringArea);
        materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
        return materialCost;
        
    }

    @Override
    public BigDecimal calculateLaborCost(Order partialOrder, Product productByType) throws NoProductException {
        BigDecimal laborCostPerSqFt = productByType.getLaborCostPerSqFt();
        BigDecimal flooringArea = partialOrder.getFlooringArea();
        BigDecimal laborCost = laborCostPerSqFt.multiply(flooringArea);
        laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);
        return laborCost;
    }

    @Override
    public BigDecimal calculateCostBeforeTax(BigDecimal materialCost, BigDecimal laborCost) {
        BigDecimal costBeforeTax = materialCost.add(laborCost);
        return costBeforeTax;
    }

    @Override
    public BigDecimal calculateTotalTax(Tax taxByState, BigDecimal costBeforeTax) throws NoStateException {
        BigDecimal taxRate = taxByState.getTaxRate();
        BigDecimal taxRateAsDecimal = taxRate.multiply(new BigDecimal("0.01"));
        BigDecimal totalTax = costBeforeTax.multiply(taxRateAsDecimal);
        //totalTax = totalTax.setScale(2, RoundingMode.HALF_UP);
        return totalTax;
    }

    @Override
    public BigDecimal calculateTotalCost(BigDecimal totalTax, BigDecimal costBeforeTax) {
        BigDecimal totalCost = costBeforeTax.add(totalTax);
        return totalCost;
    }

    @Override
    public Order fillRemainingOrderFields(Order partialNewOrder) 
            throws NoProductException, NoStateException, FlooringPersistenceException {
        
        Product productByType = getProductByType(partialNewOrder);
        Tax taxByState = getTaxByState(partialNewOrder);
        
        partialNewOrder.setTaxRate(taxByState.getTaxRate());
        partialNewOrder.setCostPerSqFt(productByType.getCostPerSqFt());
        partialNewOrder.setLaborCostPerSqFt(productByType.getLaborCostPerSqFt());

        
        BigDecimal materialCost = calculateMaterialCost(partialNewOrder, productByType);
        partialNewOrder.setMaterialCost(materialCost);
        
        BigDecimal laborCost = calculateLaborCost(partialNewOrder, productByType);
        partialNewOrder.setLaborCost(laborCost);        

        BigDecimal costBeforeTax = calculateCostBeforeTax(materialCost, laborCost);     

        BigDecimal totalTax = calculateTotalTax(taxByState, costBeforeTax);
        partialNewOrder.setTotalTax(totalTax);

       
        BigDecimal totalCost = calculateTotalCost(totalTax, costBeforeTax);
        partialNewOrder.setTotalCost(totalCost);
        
        Order fullyFormedOrder = partialNewOrder;
        
        return fullyFormedOrder;
        
    }

    @Override
    public void switchMode(boolean isTrainingMode) {
        if (isTrainingMode) {
            this.orderDao = new FlooringOrderDaoFileImpl("data/");
        } else {
            this.orderDao = new FlooringOrderDaoInMemImpl();
        }
    }

    
   
}
