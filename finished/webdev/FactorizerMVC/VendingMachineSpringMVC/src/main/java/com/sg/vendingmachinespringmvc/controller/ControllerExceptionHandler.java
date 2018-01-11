/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.ErrorMessage;
import java.util.List;
import org.springframework.http.HttpStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author matt
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    SlotController slotController = new SlotController();
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    
    public ErrorMessage processMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Your submission has the following errors: ");

        for (FieldError currentError : fieldErrors) {
            messageBuilder.append("[");
            messageBuilder.append(currentError.getField());
            messageBuilder.append(":");
            messageBuilder.append(currentError.getDefaultMessage());
            messageBuilder.append("] ");
        }

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(messageBuilder.toString());
        return errorMessage;
    }
    
    @ExceptionHandler(UpdateIntegrityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage processUpdateIntegrityException(UpdateIntegrityException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }
    
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ModelAndView processInsufficientFundsException(InsufficientFundsException e){
        
        ErrorMessage errorMessage = new ErrorMessage();
        ModelAndView mv = new ModelAndView();
        errorMessage.setMessage(e.getMessage());
        
        
        
        mv.addObject("message", e.getMessage());
        mv.setViewName("fullPage");
        
        
        return mv;
        
        /*w/ ModelAttribute Anno and Model object added to params, get no default constructor
            error for InsufficientFundsException. gonna try adding a default
        */
    }
    
    @ExceptionHandler(NoItemInventoryException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage processNoItemInventoryException(NoItemInventoryException e){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;    
    }
    
    @ExceptionHandler(NoSuchItemException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage processNoSuchItemException(NoSuchItemException e){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }
}