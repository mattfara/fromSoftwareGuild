/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.review.one.testingcode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author matt
 */
public class DateTime {
    
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
        //String to LocalDate --  read
        LocalDate testStringToLocalDate = LocalDate.parse("2010/05/02", formatter);
        //LocalDate to String -- write
        String testLocalDateToString = testStringToLocalDate.format(formatter);
                
        System.out.println(testLocalDateToString);
        
    }
    
}
