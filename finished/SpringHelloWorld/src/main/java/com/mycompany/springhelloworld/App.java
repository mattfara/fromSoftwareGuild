/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springhelloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matt
 */
public class App {
    public static void main(String[] args) {
        //this line connects to Spring
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        
        
    //instead of an instantiation, we tell ApplicationContext to do it. Interface.class
    MessageController controller = ctx.getBean("myController", MessageController.class );
    
    controller.printMessage();

    }
}
