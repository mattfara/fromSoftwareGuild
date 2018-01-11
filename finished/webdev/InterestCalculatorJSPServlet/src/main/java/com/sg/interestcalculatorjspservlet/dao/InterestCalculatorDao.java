/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet.dao;

import com.sg.interestcalculatorjspservlet.dto.YearlyReport;
import java.util.List;

/**
 *
 * @author matt
 */
public interface InterestCalculatorDao {
    public YearlyReport addYearlyReport(YearlyReport finishedReport);
    public List<YearlyReport> getAllYearlyReports();
}
