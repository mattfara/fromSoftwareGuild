/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringcalculatorjspservlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@WebServlet(name = "FlooringCalculatorServlet", urlPatterns = {"/FlooringCalculatorServlet"})
public class FlooringCalculatorServlet extends HttpServlet {

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
        
        //collecting three pieces of info from the user from the form
        //calculate time required -- flooringArea * 1hr/20sq ft, rounded up to nearest quarter
        //calculate fifteen minute blocks -- timeRequired*4
        //calculate material cost -- $/sq ft * sq ft
        //calculate labor cost -- $86/hr or $21.5/15 min; $21.5/15 min * blocks of 15 min
        //calculate total cost -- material cost + labor cost
        
        //1. make variables that will hold the data from elsewhere
        
        BigDecimal flooringArea = new BigDecimal("0");
        BigDecimal totalTimeRequired = new BigDecimal("0");
        BigDecimal materialCost = new BigDecimal("0");
        BigDecimal laborCost = new BigDecimal("0");
        BigDecimal totalCost = new BigDecimal("0");
        BigDecimal fifteenMinuteBlocks = new BigDecimal("0");
        
        String widthInput = request.getParameter("width");
        String lengthInput = request.getParameter("length");
        String costPerSqFtInput = request.getParameter("costPerSqFt");
        
        
        
        double width = Double.parseDouble(widthInput);
        double length = Double.parseDouble(lengthInput);
        flooringArea = new BigDecimal(width*length);
        
        BigDecimal costPerSqFt = new BigDecimal(costPerSqFtInput);
        
        totalTimeRequired = flooringArea.divide(new BigDecimal ("20")); //might need to do some rounding here for display purposes
        fifteenMinuteBlocks = flooringArea.multiply(new BigDecimal("4")); //check this math
        fifteenMinuteBlocks = fifteenMinuteBlocks.setScale(0, RoundingMode.CEILING);
        
        laborCost = fifteenMinuteBlocks.multiply(new BigDecimal("21.5"));
        materialCost = costPerSqFt.multiply(flooringArea);
        
        totalCost = materialCost.add(laborCost);
        
        request.setAttribute("totalTimeRequired", totalTimeRequired);
        request.setAttribute("totalCost", totalCost);
        
        
        
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
