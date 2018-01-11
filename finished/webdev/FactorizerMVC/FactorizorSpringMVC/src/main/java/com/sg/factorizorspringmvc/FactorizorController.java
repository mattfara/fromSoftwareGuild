/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.factorizorspringmvc;

import java.util.ArrayList;
import java.util.List;
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
public class FactorizorController {
    
    @RequestMapping(value="/factorNumber", method=RequestMethod.POST) //where does the HttpServletRequest argument come in?
    public String factorNumber(HttpServletRequest request, Map<String, Object> model){
        
        List<Integer> factorList = new ArrayList<>();
        int factorSum = 0;
        boolean isPerfect = false;
        boolean isPrime = false;
        
        String input = request.getParameter("numberToFactor");
        int numberToFactor = Integer.parseInt(input);
        
        for (int i = 1; i < numberToFactor; i++) {
        if (numberToFactor % i == 0) {
            // i goes into numberToFactor evenly so it
            // is a factor, add it to the list and add
            // it to the sum
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
        
        
        model.put("numberToFactor", numberToFactor); //this just puts any given object into model
        model.put("factors", factorList);
        model.put("isPrime", isPrime);
        model.put("isPerfect", isPerfect);
        
        return "result"; //we return to the logical name of the view we want to display this
    }
    
    
}
