/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springhelloworld;
/**
 *
 * @author matt
 */
public class MessageController {
    private MessageService service;
    
    public MessageController(MessageService service){
        this.service = service;
    }
    
    public void printMessage(){
        System.out.println(service.getMessage());
    }
}
