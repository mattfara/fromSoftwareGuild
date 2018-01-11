/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet.dao;

import com.sg.interestcalculatorjspservlet.dto.YearlyReport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class InterestCalculatorDaoImpl implements InterestCalculatorDao {

    List<YearlyReport> reports = new ArrayList();
    
    @Override
    public YearlyReport addYearlyReport(YearlyReport finishedReport) {
        reports.add(finishedReport);
        return finishedReport;
    }

    @Override
    public List<YearlyReport> getAllYearlyReports() {
        return reports;
    }
}
