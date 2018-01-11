/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.NoOrdersForDateException;
import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author matt farabaugh
 */
public class FlooringOrderDaoInMemImpl implements FlooringOrderDao {
    
    private Map<LocalDate, List<Order>> orderMap;
    private LocalDate currentDate;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy"); // format for file
    
    private static final String DELIMITER = ",";
    
    public FlooringOrderDaoInMemImpl () {
        this.orderMap = new HashMap<>(); // why???
        currentDate = LocalDate.now();
    }
    
    @Override
    public Order addOrder(Order order, LocalDate date) {
        
        try {
            if (!orderMap.containsKey(date)) {
                readOrderFile(date); // throw FilePersistenceException
            }
        } catch (NoOrdersForDateException e) {
            // throw a NoOrdersForDateException    
        }
        
        List<Order> listOfOrdersToAddTo = new ArrayList<>();
        
        //try {
            
        listOfOrdersToAddTo = orderMap.get(date);

        if (listOfOrdersToAddTo == null) {
            listOfOrdersToAddTo = new ArrayList<>(Arrays.asList(order));
        } else {
            listOfOrdersToAddTo.add(order);
        }

        orderMap.put(date, listOfOrdersToAddTo);
        return order;
            
        //} catch (NullPointerException ex) {
            // throw new exception
        //}
        //return null;
    }

    @Override
    public Order removeOrder(Integer orderNum, LocalDate date) {
        List<Order> ordersToRemoveFrom = new ArrayList<>();
        
        try {
            if (!orderMap.containsKey(date)) {
                readOrderFile(date); // throw FilePersistenceException
            }
        } catch (NoOrdersForDateException e) {
            // throw a NoOrdersForDateException    
        }    
              
        try {
            ordersToRemoveFrom = orderMap.get(date); // get list from map
            for (Order currentOrder : ordersToRemoveFrom) {
                if (currentOrder.getOrderNumber().equals(orderNum)) {
                    ordersToRemoveFrom.remove(currentOrder);
                    orderMap.put(date, ordersToRemoveFrom);
                    return currentOrder;  // return removed object to service layer
                } 
            }
        } catch (NullPointerException ex) {
            // throw new NoOrderNumberException
        }
        return null;
    }

    @Override
    public Order editOrder(Order order, LocalDate oldDate, LocalDate newDate) {
        
        
        // probably not necessary
        try {
            if (!orderMap.containsKey(oldDate)) {
                readOrderFile(oldDate); // throw FilePersistenceException
            }
        } catch (NoOrdersForDateException e) {
            // throw a NoOrdersForDateException    
        }          
        
        try {
            if (!orderMap.containsKey(newDate)) {
                readOrderFile(newDate); // throw FilePersistenceException
            }
        } catch (NoOrdersForDateException e) {
            // throw a NoOrdersForDateException    
        }  
        
        // if oldDate != newDate, order is moved from one row in hash map
        // to another row
        
        // COULD call remove, then call add ONLY!!!!!
        
//        List<Order> ordersToRemoveFrom = new ArrayList<>();
//        ordersToRemoveFrom = orderMap.get(oldDate);
//        List<Order> newOrders = new ArrayList<>();
        
//        try {
//            newOrders = orderMap.get(newDate);
//        } catch (NullPointerException e) {
//            // Throw new NoOrdersForDateException
//        }
            
        removeOrder(order.getOrderNumber(), oldDate);
        addOrder(order, newDate);
        
//        try {
//            newOrders.add(order);
//            orderMap.put(newDate, newOrders);
//            return order;
//        } catch (NullPointerException ex) {
//            
//        }
        return order;
    }

    @Override
    public Order getOrderByNum(Integer orderNum, LocalDate date) {
        
        List<Order> currentOrders  = new ArrayList<>();
        
        try {
            if (!orderMap.containsKey(date)) {
                readOrderFile(date); // throw FilePersistenceException
            }
        } catch (NoOrdersForDateException e) {
            // throw a NoOrdersForDateException    
        }    
        
        currentOrders = orderMap.get(date);
        
        try {
            for (Order currentOrder : currentOrders) {
                if (currentOrder.getOrderNumber().equals(orderNum)) {
                    return currentOrder;
                } 
            } 
                    
        } catch (NullPointerException ex) {
            
        }
        return null;    
    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate date) {
    
        try {
            if (!orderMap.containsKey(date)) {
                readOrderFile(date); // throw FilePersistenceException
            }
        } catch (NoOrdersForDateException e) {
            // throw a NoOrdersForDateException    
        } 

        return orderMap.get(date);
  
    }
    
    @Override
    public void clearOrders() {
        orderMap.clear();
    }
    
    private void readOrderFile(LocalDate dateOfInterest) throws NoOrdersForDateException {
        
        Scanner scanner;
        String dateOfInterestString = dateOfInterest.format(formatter);
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("data/orders/Orders_" + dateOfInterestString + ".txt"))); // check!!!!!!
        } catch (FileNotFoundException e) {
            throw new NoOrdersForDateException (
                "No orders found for given date.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        Integer lineCounter = 0;
        List<Order> currentOrders = new ArrayList<>();
        
        
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
            
            currentOrders.add(currentOrder);
        }
        
        orderMap.put(dateOfInterest, currentOrders);
        
        scanner.close();
    }    
    
}
