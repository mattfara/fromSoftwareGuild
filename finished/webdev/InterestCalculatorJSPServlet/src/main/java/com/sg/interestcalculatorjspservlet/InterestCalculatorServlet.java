/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet;

import com.sg.interestcalculatorjspservlet.dto.YearlyReport;
import com.sg.interestcalculatorjspservlet.service.InterestCalculatorService;
import com.sg.interestcalculatorjspservlet.service.InterestCalculatorServiceImpl;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matt
 */
@WebServlet(name = "InterestCalculatorServlet", urlPatterns = {"/InterestCalculatorServlet"})
public class InterestCalculatorServlet extends HttpServlet {
    InterestCalculatorService service = new InterestCalculatorServiceImpl();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
            request.setAttribute("yearsToInvest", yearsInput);
            request.setAttribute("interestRate", interestRateInput);
            request.setAttribute("initialPrincipal", initialPrincipalInput);
            request.setAttribute("yearlyReports", yearlyReports);
        
            RequestDispatcher rd = request.getRequestDispatcher("result.jsp"); 
            rd.forward(request, response);
        }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
        


    }

    

