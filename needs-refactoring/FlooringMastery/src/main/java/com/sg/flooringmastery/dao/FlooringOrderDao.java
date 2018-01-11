/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author matt farabaugh
 */
public interface FlooringOrderDao {
    
    public Order addOrder (Order order, LocalDate date);
    
    public Order removeOrder (Integer orderNum, LocalDate date);
        
    public Order editOrder (Order order, LocalDate oldDate, LocalDate newDate);
    
    public Order getOrderByNum (Integer orderNum, LocalDate date);
        
    public List<Order> getAllOrdersByDate (LocalDate date);
    
    public void clearOrders();

}
