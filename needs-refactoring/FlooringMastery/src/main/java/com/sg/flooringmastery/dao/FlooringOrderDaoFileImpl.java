/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.exception.NoOrdersForDateException;
import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author matt farabaugh
 */
public class FlooringOrderDaoFileImpl implements FlooringOrderDao {
    // Orders Map, with all orders for one day loaded in
    private Map<Integer, Order> orderMap; // this is different from mem impl - holds 1 date
    private LocalDate currentDate;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy"); // format for file
    
    private static final String FILE_HEADER = "OrderNumber,CustomerName,State,"
            + "TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,"
            + "MaterialCost,LaborCost,Tax,Total";
    
    // String directory "test/" for test files and "data/" for app
    private String directory;
    private static final String DELIMITER = ",";

    public FlooringOrderDaoFileImpl(String directory) {
        this.orderMap = new HashMap<>();
        currentDate = LocalDate.now();
        this.directory = directory;
    }
    
    @Override
    public Order addOrder(Order order, LocalDate date) {
        
        try {
            readOrderFile(date);
        } catch (NoOrdersForDateException e) {
            // throw new blah blah blah
        } 
        
        orderMap.put(order.getOrderNumber(), order);
        
        try {
            writeOrderFile(date);
        } catch (FlooringPersistenceException e) {
            // throw new blah blah balh
        }
        
        return order;
    }

    @Override
    public Order getOrderByNum(Integer orderNum, LocalDate date) {
        
        try {
            readOrderFile(date);
        } catch (NoOrdersForDateException e) {
            // throw new blah blah blah
        }     
        
        return orderMap.get(orderNum);
    }
    
    @Override
    public Order removeOrder(Integer orderNumber, LocalDate date) {
        
        try {
            readOrderFile(date);
        } catch (NoOrdersForDateException e) {
            // throw new blah blah blah
        }         
        
        Order removedOrder = null;
        
        try {
            removedOrder = orderMap.get(orderNumber);
        } catch (NullPointerException e) {
            // throw new OrderDoesNotExistException;
        }
        
        orderMap.remove(orderNumber);
        
        try {
            writeOrderFile(date);
        } catch (FlooringPersistenceException e) {
            // throw new blah blah balh
        }    
        
        return removedOrder;
    }

    @Override
    public Order editOrder(Order editedOrder, LocalDate oldDate, LocalDate newDate) {
        
        try {
            readOrderFile(oldDate);
        } catch (NoOrdersForDateException e) {
            // throw new blah blah blah
        }     
        
        removeOrder(editedOrder.getOrderNumber(), oldDate); // does this need a try/catch?
        addOrder(editedOrder, newDate);
        
        try {
            writeOrderFile(newDate);
        } catch (FlooringPersistenceException e) {
            // throw new blah blah balh
        }        
        
        return editedOrder;
    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate date) {
        
        try {
            readOrderFile(date);
        } catch (NoOrdersForDateException e) {
            // throw new blah blah blah
        } 
        
        List<Order> ordersByDate = new ArrayList(orderMap.values());
        return ordersByDate;
    }
    
    @Override
    public void clearOrders() {
        orderMap.clear();
    }
    
    private void readOrderFile(LocalDate dateOfInterest) throws NoOrdersForDateException {
        
        clearOrders(); // called in every method...
        
        Scanner scanner;
        String dateOfInterestString = dateOfInterest.format(formatter);
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            // this is different from in mem impl!!
                            new FileReader(directory + "orders/Orders_" + dateOfInterestString + ".txt"))); // check!!!!!!
        } catch (FileNotFoundException e) {
            throw new NoOrdersForDateException (
                "No orders found for given date.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        Integer lineCounter = 0;
        // List<Order> currentOrders = new ArrayList<>();
        
        // each iteration creates one order (but first line is skipped over)
        while (scanner.hasNextLine()) {
            
            currentLine = scanner.nextLine();
            
            if (currentLine.length() == 0) {
                break;
            }
            
            lineCounter++;
            
            if (lineCounter == 1) {
                continue;
            }
            
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setFlooringArea(new BigDecimal(currentTokens[5]));
            currentOrder.setCostPerSqFt(new BigDecimal(currentTokens[6]));
            currentOrder.setLaborCostPerSqFt(new BigDecimal(currentTokens[7]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTotalTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));
            
            orderMap.put(currentOrder.getOrderNumber(), currentOrder);
            
        }
        
      //  orderMap.put(dateOfInterest, currentOrders);
        
        scanner.close();
    }    
    
    private void writeOrderFile(LocalDate dateOfInterest) throws FlooringPersistenceException {
        
        PrintWriter out;
        
        String dateOfInterestString = dateOfInterest.format(formatter);
        
        try {
            out = new PrintWriter(new FileWriter(directory + "orders/Orders_" + dateOfInterestString + ".txt"));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not save order data.", e);
        }
        
        List<Order> orderList = new ArrayList(orderMap.values());
        
        out.println(FILE_HEADER);
        
        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTaxRate().toString() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getFlooringArea().toString() + DELIMITER
                    + currentOrder.getCostPerSqFt().toString() + DELIMITER
                    + currentOrder.getLaborCostPerSqFt().toString() + DELIMITER
                    + currentOrder.getMaterialCost().toString() + DELIMITER
                    + currentOrder.getLaborCost().toString() + DELIMITER
                    + currentOrder.getTotalTax().toString() + DELIMITER
                    + currentOrder.getTotalCost().toString());
            out.flush();
        }
        
        out.close();        
    }
    
}
