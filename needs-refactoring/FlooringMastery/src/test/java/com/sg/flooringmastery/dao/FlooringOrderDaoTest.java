/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.exception.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author matt farabaugh
 */
public class FlooringOrderDaoTest {
    
    // FlooringOrderDao orderDao = new FlooringOrderDaoInMemImpl();
    FlooringOrderDao orderDao = new FlooringOrderDaoFileImpl("test/"); // test directory for files   
    
    public FlooringOrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        // write files back to original states!!!
    }

    @Before
    public void setUp() throws Exception {
        
        FileUtils.cleanDirectory(new File("test/orders/"));
        
        orderDao.clearOrders();
        
        String FILE_HEADER = "OrderNumber,CustomerName,State,"
                + "TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,"
                + "MaterialCost,LaborCost,Tax,Total";
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("test/orders/Orders_06012013.txt"));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not write order data.", e);
        }

        out.println(FILE_HEADER);
        out.println("1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88");

        out.flush();
        out.close();        
        
    }

    @After
    public void tearDown() throws Exception {
       setUp();
    }

    
    /**
     * Test of addOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testAddOrderAndGetOrderByNum() throws Exception {

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

        orderDao.addOrder(order1, order1Date);

        Order testOrder = orderDao.getOrderByNum(2, order1Date);

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

        orderDao.addOrder(order1, order1Date);


        testOrder = orderDao.getOrderByNum(3, order1Date);

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
     * Test of removeOrder method, of class FlooringOrderDao.
     */
    @Test
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

        orderDao.addOrder(order1, order1Date);

        orderDao.removeOrder(1, order1Date);

        assertNull(orderDao.getOrderByNum(1, order1Date));

        // removed something that doesn't exist
        // try -- fail
        // catch does not exist
        //
    }

    /**
     * Test of editOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        
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
        
        orderDao.addOrder(order2, orderDate2);
        
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
        orderDao.editOrder(order2, orderDate2, orderDate3);
        
        // order is not located at the old date
        assertNull(orderDao.getOrderByNum(2, orderDate2));
        // order is located at the new date
        assertNotNull(orderDao.getOrderByNum(2, orderDate3));

        // get edited order as testOrder object
        Order testOrder = orderDao.getOrderByNum(2, orderDate3);
        
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
     * Test of getAllOrdersByDate method, of class FlooringOrderDao.
     */
    @Test
    public void testGetAllOrdersByDate() throws Exception {
        
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
        if (orderDao.getAllOrdersByDate(LocalDate.parse("2013-06-01")) != null)
        {
             size1 = orderDao.getAllOrdersByDate(LocalDate.parse("2013-06-01")).size();
        } else {
            size1 = 0;
        }
        
        int size2;
        if (orderDao.getAllOrdersByDate(LocalDate.parse("1112-11-12")) != null)
        {
            size2 = orderDao.getAllOrdersByDate(LocalDate.parse("1112-11-12")).size();
        } else {
            size2 = 0;
        }
        
        orderDao.addOrder(order1, LocalDate.parse("2013-06-01"));
        orderDao.addOrder(order2, LocalDate.parse("1112-11-12"));
        orderDao.addOrder(order3, LocalDate.parse("1112-11-12"));
        
        orderList = orderDao.getAllOrdersByDate(LocalDate.parse("2013-06-01"));
        assertEquals(size1 + 1, orderList.size());

        orderList = orderDao.getAllOrdersByDate(LocalDate.parse("1112-11-12"));
        assertEquals(size2 + 2, orderList.size());
        
        orderList = orderDao.getAllOrdersByDate(LocalDate.parse("1113-11-11"));
        assertTrue(orderList == null || orderList.isEmpty());
        
    }
    
}
