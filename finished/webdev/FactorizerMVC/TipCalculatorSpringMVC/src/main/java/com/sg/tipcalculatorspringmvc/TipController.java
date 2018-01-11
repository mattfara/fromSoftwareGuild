/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.tipcalculatorspringmvc;

import java.math.BigDecimal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author matt
 */
@Controller
public class TipController {
    
    @RequestMapping(value="calculateTip", method=RequestMethod.POST) //value for value attribute must be called in the form in index.jsp!!!
    public String calculateTip (HttpServletRequest request, 
                               Map<String, Object> model){
        
        String billInput = request.getParameter("bill");
        String tipInput = request.getParameter("tip");
        
        BigDecimal bill = new BigDecimal(billInput);
        BigDecimal tip = new BigDecimal(tipInput);
        tip = tip.divide(new BigDecimal("100"));
        
        BigDecimal extraMoney = bill.multiply(tip);
        
        BigDecimal total = bill.add(extraMoney);
        
        model.put("originalAmount", billInput);
        model.put("tipPercent", tipInput);
        model.put("tipAmount", extraMoney);
        model.put("total", total);
        
        return "result";

    }
}
