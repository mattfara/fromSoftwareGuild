/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.factorizorjspservlet;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "FactorizorServlet", urlPatterns = {"/FactorizorServlet"})//can have multiple url patterns; container reads this
public class FactorizorServlet extends HttpServlet {

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
     
        List<Integer> factorList = new ArrayList();
        
        int factorSum=0;
        boolean isPrime = false;
        boolean isPerfect = false;
        
        String input = request.getParameter("numberToFactor"); //this is how the servlet reaches into index.jsp
                                                        
        
        int numberToFactor = Integer.parseInt(input);  //lines 45 to 60 is calculation -- work we do w/ jsp input
        
        for (int i = 1; i < numberToFactor; i++) {
        if (numberToFactor % i == 0) {
            factorList.add(i);
            factorSum += i;
        }
    }

    if (factorSum == numberToFactor) {
        isPerfect = true;
    }

    if (factorSum == 1) {
        isPrime = true;
    }
    
    //the next 4 lines are setting k:v pairs inside the request object
    request.setAttribute("numberToFactor", numberToFactor);
    request.setAttribute("factors", factorList); //the new name in quotes will be used in the result.jsp
    request.setAttribute("isPrime", isPrime);
    request.setAttribute("isPerfect", isPerfect);
    RequestDispatcher rd = request.getRequestDispatcher("result.jsp"); //forward control to another jsp -- now result.jsp has the ball
    rd.forward(request, response); //i guess this uses tomcat to bring the two together, as the server...
        
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
    @Override //without this, got a 405 method not supported
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
