package com.sg.interestcalculatorspringmvc.dto;
import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class YearlyReport {
    int year;
    BigDecimal startingPrincipal;
    BigDecimal interestEarned;
    BigDecimal endingPrincipal;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public BigDecimal getStartingPrincipal() {
        return startingPrincipal;
    }

    public void setStartingPrincipal(BigDecimal startingPrincipal) {
        this.startingPrincipal = startingPrincipal;
    }

    public BigDecimal getInterestEarned() {
        return interestEarned;
    }

    public void setInterestEarned(BigDecimal interestEarned) {
        this.interestEarned = interestEarned;
    }

    public BigDecimal getEndingPrincipal() {
        return endingPrincipal;
    }

    public void setEndingPrincipal(BigDecimal endingPrincipal) {
        this.endingPrincipal = endingPrincipal;
    }
    
}
