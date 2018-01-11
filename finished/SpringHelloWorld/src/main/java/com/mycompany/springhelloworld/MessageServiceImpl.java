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
public class MessageServiceImpl implements MessageService {
    
    @Override
    public String getMessage() {
        return "I am a message, Hello";
    }
    
}
