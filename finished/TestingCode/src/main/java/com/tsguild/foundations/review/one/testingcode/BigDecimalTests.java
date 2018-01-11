/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.review.one.testingcode;

import java.math.BigDecimal;

/**
 *
 * @author matt
 */
public class BigDecimalTests {
    public static void main(String[] args) {
        BigDecimal test = new BigDecimal("01");
        System.out.println(test);
        
        test = new BigDecimal("00");
        System.out.println(test);
    }
}
