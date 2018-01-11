/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorspringmvc.service;

import com.sg.interestcalculatorspringmvc.dto.YearlyReport;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author matt
 */
public class InterestCalculatorServiceImpl implements InterestCalculatorService{
    Calendar now = Calendar.getInstance();
    int currentYear = now.get(Calendar.YEAR);
    List<YearlyReport> reports = new ArrayList(); 
    
    
    
    @Override //the YearlyReport should come here with a starting principal in place
    public YearlyReport fillOutYearlyReport(YearlyReport report, BigDecimal startingPrincipal, BigDecimal quarterlyInterestRate) {
        report.setYear(currentYear);
        report.setStartingPrincipal(startingPrincipal);
        BigDecimal endingPrincipal = calculateEndingPrincipalForYear(startingPrincipal, quarterlyInterestRate);
        report.setEndingPrincipal(endingPrincipal);
        BigDecimal interestEarned = calculateInterestEarnedForYear(startingPrincipal, endingPrincipal);
        report.setInterestEarned(interestEarned);
        currentYear++;
        return report;
    }

    @Override // bug is HERE
    public BigDecimal calculateEndingPrincipalForYear(BigDecimal startingPrincipal, BigDecimal quarterlyInterestRate) {
        BigDecimal endingPrincipal = startingPrincipal;
        
        BigDecimal bigInterest = quarterlyInterestRate.divide(new BigDecimal("100"));
        BigDecimal multiplier = new BigDecimal("1").add(bigInterest);
        
        for (int quarter = 0; quarter<4; quarter++){
            endingPrincipal = endingPrincipal.multiply(multiplier);
        }
        
        return endingPrincipal.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateInterestEarnedForYear(BigDecimal startingPrincipal, BigDecimal endingPrincipal) {
        return endingPrincipal.subtract(startingPrincipal);
    }
    
    @Override
    public YearlyReport addYearlyReport(YearlyReport finishedReport){
        reports.add(finishedReport);
        return finishedReport;
    }
    
    @Override
    public List<YearlyReport> getAllYearlyReports(){
        return this.reports;
    }
    
}
