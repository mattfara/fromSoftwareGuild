/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorspringmvc;

import com.sg.interestcalculatorspringmvc.dto.YearlyReport;
import com.sg.interestcalculatorspringmvc.service.InterestCalculatorService;
import com.sg.interestcalculatorspringmvc.service.InterestCalculatorServiceImpl;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author matt
 */
@Controller
public class InterestCalculatorController {
        @RequestMapping(value="/calculateInvestment", method=RequestMethod.POST)
        public String calculateInvestment(HttpServletRequest request, Map<String, Object> model)
            throws ServletException, IOException {

            InterestCalculatorService service = new InterestCalculatorServiceImpl();
            /*step 1 -- get the starting values from the other page
                --annual interest rate 
                --initial principal
                --years
            */
        
            String interestRateInput = request.getParameter("interest-rate");
            String initialPrincipalInput = request.getParameter("starting-principal");
            String yearsInput = request.getParameter("years");
            
            BigDecimal interestRate = new BigDecimal(interestRateInput);
            BigDecimal quarterlyInterestRate = interestRate.divide(new BigDecimal("4"));
            BigDecimal initialPrincipal = new BigDecimal(initialPrincipalInput);
            int yearsToInvest = Integer.parseInt(yearsInput);
            
            /* step 2 -- set up variables that will inform each object
                --yearStartPrincipal
                --interestEarned
                --yearEndPrincipal
            */
            
            BigDecimal yearStartPrincipal = initialPrincipal; //maybe only need this one to pass in
            
            for (int currentYear = 0; currentYear<yearsToInvest; currentYear++){
                YearlyReport currentYearReport = new YearlyReport();
                service.fillOutYearlyReport(currentYearReport, yearStartPrincipal, quarterlyInterestRate);
                service.addYearlyReport(currentYearReport);
                yearStartPrincipal = currentYearReport.getEndingPrincipal();
            }
            
            List<YearlyReport> yearlyReports = service.getAllYearlyReports();
            model.put("yearsToInvest", yearsInput);
            model.put("interestRate", interestRateInput);
            model.put("initialPrincipal", initialPrincipalInput);
            model.put("yearlyReports", yearlyReports);
            
            return "result";
        }

}
