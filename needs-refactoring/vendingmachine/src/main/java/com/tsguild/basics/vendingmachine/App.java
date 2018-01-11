
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.vendingmachine;

import com.tsguild.basics.vendingmachine.controller.VendingMachineController;
import com.tsguild.basics.vendingmachine.service.StatsPersistence;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matt
 */
public class App {
    //need to use dependnecy injection here
    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl(); 
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
//
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
//        VendingMachineView myView = new VendingMachineView(myIo);
//
//        VendingMachineController controller = new VendingMachineController(myService, myView);
//        //starting the program
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        

        controller.run();
    }
    
}

















