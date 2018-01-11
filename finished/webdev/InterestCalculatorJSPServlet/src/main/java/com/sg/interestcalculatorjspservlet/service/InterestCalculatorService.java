/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet.service;

import com.sg.interestcalculatorjspservlet.dto.YearlyReport;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author matt
 */
public interface InterestCalculatorService {
     
    public YearlyReport fillOutYearlyReport(YearlyReport report, BigDecimal startingPrincipal, BigDecimal quarterlyInterestRate);
    public BigDecimal calculateEndingPrincipalForYear(BigDecimal startingPrincipal, BigDecimal quarterlyInterestRate);
    public BigDecimal calculateInterestEarnedForYear(BigDecimal startingPrincipal, BigDecimal endingPrincipal);
    public YearlyReport addYearlyReport(YearlyReport finishedReport);
    public List<YearlyReport> getAllYearlyReports();
}
