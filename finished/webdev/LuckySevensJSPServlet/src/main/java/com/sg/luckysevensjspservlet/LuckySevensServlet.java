/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.luckysevensjspservlet;

import java.io.IOException;
import java.util.Random;
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
@WebServlet(name = "LuckySevensServlet", urlPatterns = {"/LuckySevensServlet"})
public class LuckySevensServlet extends HttpServlet {

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
        
        Random rand = new Random();
        
        
        String moneyInput = request.getParameter("money");
        int initialMoney = Integer.parseInt(moneyInput);
        
        
        int numberOfRolls = 0;
        int maxMoney = initialMoney;
        int rollWhenMoneyWasHighest = 0;
        int playMoney = initialMoney;
        
        int dice1, dice2;
        
        while (playMoney>0){
            dice1 = rand.nextInt(6)+1;
            dice2 = rand.nextInt(6)+1;
            int sum = dice1+dice2;
            
            playMoney = (sum==7) ? playMoney+4 : playMoney-1; //looks like shit....simple if/else would be better
            
            if (playMoney>maxMoney){
                maxMoney = playMoney;
                rollWhenMoneyWasHighest = numberOfRolls;
            }
            numberOfRolls++;
        }
        request.setAttribute("initialMoney",initialMoney);
        request.setAttribute("numberOfRolls", numberOfRolls);
        request.setAttribute("maxMoney", maxMoney);
        request.setAttribute("rollWhenMoneyWasHighest", rollWhenMoneyWasHighest);
        
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
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
        rd.forward(request, response);
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
