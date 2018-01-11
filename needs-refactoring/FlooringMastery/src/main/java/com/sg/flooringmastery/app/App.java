/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.app;

import com.sg.flooringmastery.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matt farabaugh
 */
public class App {
    public static void main(String[] args) {
        
//        UserIO myIO = new UserIOConsoleImpl(); // your IO console impl here!
//        FlooringView myView = new FlooringView(myIO);
//        
//        FlooringOrderDao myOrderDao = new FlooringOrderDaoFileImpl("data/");
//        FlooringUniqueOrderNumberDao myOrderNumberDao = new FlooringUniqueOrderNumberDaoFileImpl();
//        FlooringProductDao myProductDao = new FlooringProductDaoFileImpl("data/");
//        FlooringTaxDao myTaxDao = new FlooringTaxDaoFileImpl("data/");
//        
//        FlooringProductService myProductService = new FlooringProductServiceImpl(myProductDao);
//        FlooringTaxService myTaxService = new FlooringTaxServiceImpl(myTaxDao);
//        FlooringUniqueOrderNumberService myOrderNumberService = 
//                new FlooringUniqueOrderNumberServiceImpl(myOrderNumberDao);
//        FlooringOrderService myOrderService = new FlooringOrderServiceImpl(myOrderDao,
//            myProductService, myTaxService, myOrderNumberService);
//        
//        FlooringController controller = new FlooringController(myView,
//            myOrderService, myOrderNumberService);
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        FlooringController controller = 
                ctx.getBean("controller", FlooringController.class);
        
        controller.run();
        
        
        
    }
    
    
}
