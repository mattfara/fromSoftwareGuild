/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringcalculatorspringmvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author matt
 */

@Controller
public class FlooringController {
    @RequestMapping(value="/calculateFlooringCost", method=RequestMethod.POST)
        public String calculateFlooringCost(HttpServletRequest request, Map<String, Object> model){    
            
        
            
        BigDecimal flooringArea = new BigDecimal("0");
        BigDecimal totalTimeRequired = new BigDecimal("0");
        BigDecimal materialCost = new BigDecimal("0");
        BigDecimal laborCost = new BigDecimal("0");
        BigDecimal totalCost = new BigDecimal("0");
        BigDecimal fifteenMinuteBlocks = new BigDecimal("0");
        
        String widthInput = request.getParameter("width");  //GET INCOMING PARAMETERS
        String lengthInput = request.getParameter("length");
        String costPerSqFtInput = request.getParameter("costPerSqFt");
        
        
        
        double width = Double.parseDouble(widthInput); //DO SOME CALCULATIONS
        double length = Double.parseDouble(lengthInput);
        flooringArea = new BigDecimal(width*length);
        
        BigDecimal costPerSqFt = new BigDecimal(costPerSqFtInput);
        
        totalTimeRequired = flooringArea.divide(new BigDecimal ("20")); //might need to do some rounding here for display purposes
        fifteenMinuteBlocks = flooringArea.multiply(new BigDecimal("4")); //check this math
        fifteenMinuteBlocks = fifteenMinuteBlocks.setScale(0, RoundingMode.CEILING);
        
        laborCost = fifteenMinuteBlocks.multiply(new BigDecimal("21.5"));
        materialCost = costPerSqFt.multiply(flooringArea);
        
        totalCost = materialCost.add(laborCost);
        
        model.put("totalTimeRequired", totalTimeRequired); //SET VALUES ON MODEL MAP
        model.put("totalCost", totalCost);
        
        return "result"; //FORWARD CONTROL TO ANOTHER JSP
        }
}
