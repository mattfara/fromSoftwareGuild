/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringOrderDaoInMemImpl;
import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringProductDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringTaxDao;
import com.sg.flooringmastery.dao.FlooringTaxDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringUniqueOrderNumberDao;
import com.sg.flooringmastery.dao.FlooringUniqueOrderNumberDaoInMemImpl;
import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt farabaugh
 */
public class FlooringOrderServiceTest {
    
    // dependencies & directories for test
    FlooringOrderDao orderDao = new FlooringOrderDaoInMemImpl();
    FlooringProductDao productDao = new FlooringProductDaoFileImpl("test/");
    FlooringTaxDao taxDao = new FlooringTaxDaoFileImpl("test/");
    FlooringUniqueOrderNumberDao orderNumberDao = new FlooringUniqueOrderNumberDaoInMemImpl();
   
    // other service implementations
    FlooringProductService productService = new FlooringProductServiceImpl(productDao);
    FlooringTaxService taxService = new FlooringTaxServiceImpl(taxDao);
    FlooringUniqueOrderNumberService uniqueOrderNumService = 
        new FlooringUniqueOrderNumberServiceImpl(orderNumberDao);    
    
    // order service implementation
    FlooringOrderService orderService = new FlooringOrderServiceImpl(orderDao, productService,
        taxService, uniqueOrderNumService);    
    
    
    public FlooringOrderServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrdersByDate method, of class FlooringOrderService.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        

        List<Order> orderList;
        
        Order order1 = new Order(4);
        
        order1.setCustomerName("Croyle");
        order1.setFlooringArea(new BigDecimal("44.583"));

        order1.setState("OH");
        order1.setProductType("Laminate");

        order1.setTaxRate(new BigDecimal("5.75"));
        order1.setCostPerSqFt(new BigDecimal("1.13"));
        order1.setLaborCostPerSqFt(new BigDecimal("0.45"));
        order1.setMaterialCost(new BigDecimal("454.26"));
        order1.setLaborCost(new BigDecimal("236.47"));
        order1.setTotalTax(new BigDecimal("23.74"));
        order1.setTotalCost(new BigDecimal("684.35"));
        
        Order order2 = new Order(2);
        
        order2.setCustomerName("Croyle");
        order2.setFlooringArea(new BigDecimal("44.583"));

        order2.setState("OH");
        order2.setProductType("Laminate");

        order2.setTaxRate(new BigDecimal("5.75"));
        order2.setCostPerSqFt(new BigDecimal("1.13"));
        order2.setLaborCostPerSqFt(new BigDecimal("0.45"));
        order2.setMaterialCost(new BigDecimal("454.26"));
        order2.setLaborCost(new BigDecimal("236.47"));
        order2.setTotalTax(new BigDecimal("23.74"));
        order2.setTotalCost(new BigDecimal("684.35"));        
                
        Order order3 = new Order(3);
        
        order3.setCustomerName("Croyle");
        order3.setFlooringArea(new BigDecimal("44.583"));

        order3.setState("OH");
        order3.setProductType("Laminate");

        order3.setTaxRate(new BigDecimal("5.75"));
        order3.setCostPerSqFt(new BigDecimal("1.13"));
        order3.setLaborCostPerSqFt(new BigDecimal("0.45"));
        order3.setMaterialCost(new BigDecimal("454.26"));
        order3.setLaborCost(new BigDecimal("236.47"));
        order3.setTotalTax(new BigDecimal("23.74"));
        order3.setTotalCost(new BigDecimal("684.35"));        
        
                
        int size1;
        if (orderService.getOrdersByDate(LocalDate.parse("2013-06-01")) != null)
        {
             size1 = orderService.getOrdersByDate(LocalDate.parse("2013-06-01")).size();
        } else {
            size1 = 0;
        }
        
        int size2;
        if (orderService.getOrdersByDate(LocalDate.parse("1112-11-12")) != null)
        {
            size2 = orderService.getOrdersByDate(LocalDate.parse("1112-11-12")).size();
        } else {
            size2 = 0;
        }
        
        orderService.addNewOrder(order1, LocalDate.parse("2013-06-01"));
        orderService.addNewOrder(order2, LocalDate.parse("1112-11-12"));
        orderService.addNewOrder(order3, LocalDate.parse("1112-11-12"));
        
        orderList = orderService.getOrdersByDate(LocalDate.parse("2013-06-01"));
        assertEquals(size1 + 1, orderList.size());

        orderList = orderService.getOrdersByDate(LocalDate.parse("1112-11-12"));
        assertEquals(size2 + 2, orderList.size());
        
        orderList = orderService.getOrdersByDate(LocalDate.parse("1113-11-11"));
        assertTrue(orderList == null || orderList.isEmpty());        
    }

    /**
     * Test of getOrderByNumberber method, of class FlooringOrderService.
     */
    @Test // works
    public void testGetOrderByNumberber() throws Exception {
        LocalDate order1Date = LocalDate.parse("2013-06-01");

        Order order1 = new Order(2); /// Duplicate OrderNumbers are not yet checked!!!

        order1.setCustomerName("Croyle");
        order1.setFlooringArea(new BigDecimal("44.583"));

        order1.setState("OH");
        order1.setProductType("Laminate");

        order1.setTaxRate(new BigDecimal("5.75"));
        order1.setCostPerSqFt(new BigDecimal("1.13"));
        order1.setLaborCostPerSqFt(new BigDecimal("0.45"));
        order1.setMaterialCost(new BigDecimal("454.26"));
        order1.setLaborCost(new BigDecimal("236.47"));
        order1.setTotalTax(new BigDecimal("23.74"));
        order1.setTotalCost(new BigDecimal("684.35"));

        orderService.addNewOrder(order1, order1Date);

        Order testOrder = orderService.getOrderByNumber(2, order1Date);

        // Compare Strings
        assertTrue(order1.getCustomerName().equals(testOrder.getCustomerName()));
        assertTrue(order1.getState().equals(testOrder.getState()));
        assertTrue(order1.getProductType().equals(testOrder.getProductType()));
        // Compare BigDecimals
        assertTrue(order1.getFlooringArea().compareTo(testOrder.getFlooringArea()) == 0);
        assertTrue(order1.getTaxRate().compareTo(testOrder.getTaxRate()) == 0);
        assertTrue(order1.getCostPerSqFt().compareTo(testOrder.getCostPerSqFt()) == 0);
        assertTrue(order1.getLaborCostPerSqFt().compareTo(testOrder.getLaborCostPerSqFt()) == 0);
        assertTrue(order1.getMaterialCost().compareTo(testOrder.getMaterialCost()) == 0);
        assertTrue(order1.getLaborCost().compareTo(testOrder.getLaborCost()) == 0);
        assertTrue(order1.getTotalTax().compareTo(testOrder.getTotalTax()) == 0);
        assertTrue(order1.getTotalCost().compareTo(testOrder.getTotalCost()) == 0);

        /// Begin Test 2


        order1Date = LocalDate.parse("1113-06-01");

        order1 = new Order(3);

        order1.setCustomerName("Croyle");
        order1.setFlooringArea(new BigDecimal("44.583"));

        order1.setState("OH");
        order1.setProductType("Laminate");

        order1.setTaxRate(new BigDecimal("5.75"));
        order1.setCostPerSqFt(new BigDecimal("1.13"));
        order1.setLaborCostPerSqFt(new BigDecimal("0.45"));
        order1.setMaterialCost(new BigDecimal("454.26"));
        order1.setLaborCost(new BigDecimal("236.47"));
        order1.setTotalTax(new BigDecimal("23.74"));
        order1.setTotalCost(new BigDecimal("684.35"));

        orderService.addNewOrder(order1, order1Date);


        testOrder = orderService.getOrderByNumber(3, order1Date);

        // Compare Strings
        assertTrue(order1.getCustomerName().equals(testOrder.getCustomerName()));
        assertTrue(order1.getState().equals(testOrder.getState()));
        assertTrue(order1.getProductType().equals(testOrder.getProductType()));
        // Compare BigDecimals
        assertTrue(order1.getFlooringArea().compareTo(testOrder.getFlooringArea()) == 0);
        assertTrue(order1.getTaxRate().compareTo(testOrder.getTaxRate()) == 0);
        assertTrue(order1.getCostPerSqFt().compareTo(testOrder.getCostPerSqFt()) == 0);
        assertTrue(order1.getLaborCostPerSqFt().compareTo(testOrder.getLaborCostPerSqFt()) == 0);
        assertTrue(order1.getMaterialCost().compareTo(testOrder.getMaterialCost()) == 0);
        assertTrue(order1.getLaborCost().compareTo(testOrder.getLaborCost()) == 0);
        assertTrue(order1.getTotalTax().compareTo(testOrder.getTotalTax()) == 0);
        assertTrue(order1.getTotalCost().compareTo(testOrder.getTotalCost()) == 0);         
    }

    /**
     * Test of addNewOrder method, of class FlooringOrderService.
     */
    @Test // works 
    public void testAddNewOrder() throws Exception {
    }

    /**
     * Test of addEditedOrder method, of class FlooringOrderService.
     */
    @Test // works 
    public void testAddEditedOrder() throws Exception {
        
        LocalDate orderDate2 = LocalDate.parse("1111-11-11");
        
        Order order2 = new Order(2);
                
        order2.setCustomerName("Croyle");
        order2.setFlooringArea(new BigDecimal("44.583"));
        
        order2.setState("OH");
        order2.setProductType("Laminate");
        
        order2.setTaxRate(new BigDecimal("5.75"));
        order2.setCostPerSqFt(new BigDecimal("1.13"));
        order2.setLaborCostPerSqFt(new BigDecimal("0.45"));
        order2.setMaterialCost(new BigDecimal("454.26"));
        order2.setLaborCost(new BigDecimal("236.47"));
        order2.setTotalTax(new BigDecimal("23.74"));
        order2.setTotalCost(new BigDecimal("684.35"));
        
        orderService.addNewOrder(order2, orderDate2);
        
        LocalDate orderDate3 = LocalDate.parse("1212-12-11");
        
        order2.setCustomerName("Doyle");
        order2.setFlooringArea(new BigDecimal("44.583"));
        
        order2.setState("MI");
        order2.setProductType("Laminate");
        
        order2.setTaxRate(new BigDecimal("5.75"));
        order2.setCostPerSqFt(new BigDecimal("1.13"));
        order2.setLaborCostPerSqFt(new BigDecimal("0.65"));
        order2.setMaterialCost(new BigDecimal("454.26"));
        order2.setLaborCost(new BigDecimal("236.47"));
        order2.setTotalTax(new BigDecimal("23.72"));
        order2.setTotalCost(new BigDecimal("684.35"));
        
        // change order1 date and several fields
        orderService.addEditedOrder(order2, orderDate2, orderDate3);
        
        // order is not located at the old date
        assertNull(orderService.getOrderByNumber(2, orderDate2));
        // order is located at the new date
        assertNotNull(orderService.getOrderByNumber(2, orderDate3));

        // get edited order as testOrder object
        Order testOrder = orderService.getOrderByNumber(2, orderDate3);
        
        // Compare Strings
        assertTrue(order2.getCustomerName().equals(testOrder.getCustomerName()));
        assertTrue(order2.getState().equals(testOrder.getState()));
        assertTrue(order2.getProductType().equals(testOrder.getProductType()));
        // Compare BigDecimals
        assertTrue(order2.getFlooringArea().compareTo(testOrder.getFlooringArea()) == 0);
        assertTrue(order2.getTaxRate().compareTo(testOrder.getTaxRate()) == 0);
        assertTrue(order2.getCostPerSqFt().compareTo(testOrder.getCostPerSqFt()) == 0);
        assertTrue(order2.getLaborCostPerSqFt().compareTo(testOrder.getLaborCostPerSqFt()) == 0);
        assertTrue(order2.getMaterialCost().compareTo(testOrder.getMaterialCost()) == 0);
        assertTrue(order2.getLaborCost().compareTo(testOrder.getLaborCost()) == 0);
        assertTrue(order2.getTotalTax().compareTo(testOrder.getTotalTax()) == 0);
        assertTrue(order2.getTotalCost().compareTo(testOrder.getTotalCost()) == 0); 
        
    }

    /**
     * Test of removeOrder method, of class FlooringOrderService.
     */
    @Test // works
    public void testRemoveOrder() throws Exception {

        LocalDate order1Date = LocalDate.parse("1111-11-11");

        Order order1 = new Order(1);

        order1.setCustomerName("Croyle");
        order1.setFlooringArea(new BigDecimal("44.583"));

        order1.setState("OH");
        order1.setProductType("Laminate");

        order1.setTaxRate(new BigDecimal("5.75"));
        order1.setCostPerSqFt(new BigDecimal("1.13"));
        order1.setLaborCostPerSqFt(new BigDecimal("0.45"));
        order1.setMaterialCost(new BigDecimal("454.26"));
        order1.setLaborCost(new BigDecimal("236.47"));
        order1.setTotalTax(new BigDecimal("23.74"));
        order1.setTotalCost(new BigDecimal("684.35"));

        orderService.addNewOrder(order1, order1Date);

        orderService.removeOrder(order1, order1Date);

        assertNull(orderService.getOrderByNumber(1, order1Date));        
        
    }

    /**
     * Test of getProductByType method, of class FlooringOrderService.
     */
    @Test
    public void testGetProductByType() throws Exception {
    }

    /**
     * Test of getTaxByState method, of class FlooringOrderService.
     */
    @Test
    public void testGetTaxByState() throws Exception {
    }

    /**
     * Test of calculateMaterialCost method, of class FlooringOrderService.
     */
    @Test // works
    public void testCalculateMaterialCost() throws Exception {
    }

    /**
     * Test of calculateLaborCost method, of class FlooringOrderService.
     */
    @Test
    public void testCalculateLaborCost() throws Exception {
    }

    /**
     * Test of calculateCostBeforeTax method, of class FlooringOrderService.
     */
    @Test
    public void testCalculateCostBeforeTax() {
    }

    /**
     * Test of calculateTotalTax method, of class FlooringOrderService.
     */
    @Test
    public void testCalculateTotalTax() throws Exception {
    }

    /**
     * Test of calculateTotalCost method, of class FlooringOrderService.
     */
    @Test
    public void testCalculateTotalCost() {
    }

    public void setUserInputFieldsForOrder(Order partialOrder) {
        partialOrder.setCustomerName("Wise");
        partialOrder.setState("OH");
        partialOrder.setProductType("Wood");
        partialOrder.setFlooringArea(new BigDecimal("100.00"));
    }
    
    /**
     * Test of fillRemainingOrderFields method, of class FlooringOrderService.
     */
    @Test
    public void testFillRemainingOrderFields() throws Exception {
        
        Order partialOrder = new Order(uniqueOrderNumService.getNewUniqueOrderNumber().getOrderNumber());
        setUserInputFieldsForOrder(partialOrder);
        
        Order fullyFormedOrder = orderService.fillRemainingOrderFields(partialOrder);
        BigDecimal taxRate = fullyFormedOrder.getTaxRate();
        BigDecimal materialCostPerSqFt = fullyFormedOrder.getCostPerSqFt();
        BigDecimal laborCostPerSqFt = fullyFormedOrder.getLaborCostPerSqFt();
        BigDecimal materialCost = fullyFormedOrder.getMaterialCost();
        BigDecimal laborCost = fullyFormedOrder.getLaborCost();
        BigDecimal totalTax = fullyFormedOrder.getTotalTax();
        BigDecimal totalCost = fullyFormedOrder.getTotalCost();
        
        assertTrue(new BigDecimal("6.25").compareTo(taxRate) == 0);
        assertTrue(new BigDecimal("5.15").compareTo(materialCostPerSqFt) == 0);
        assertTrue(new BigDecimal("4.75").compareTo(laborCostPerSqFt) == 0);
        assertTrue(new BigDecimal("515.00").compareTo(materialCost) == 0);
        assertTrue(new BigDecimal("475.00").compareTo(laborCost) == 0);
        assertTrue(new BigDecimal("61.875")
                .compareTo(totalTax) == 0);
        assertTrue(new BigDecimal("1051.875")
                .compareTo(totalCost) == 0);

        
    }

    /**
     * Test of switchMode method, of class FlooringOrderService.
     */
    @Test
    public void testSwitchMode() {
    }
    
}
