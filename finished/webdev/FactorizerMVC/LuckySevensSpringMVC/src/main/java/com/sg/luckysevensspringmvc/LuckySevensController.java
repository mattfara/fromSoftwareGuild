/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.luckysevensspringmvc;

import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author matt
 */
@Controller
public class LuckySevensController {
    @RequestMapping(value="play", method=RequestMethod.POST)
    public String play(HttpServletRequest request, 
                               Map<String, Object> model){
    
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
        model.put("initialMoney",initialMoney);
        model.put("numberOfRolls", numberOfRolls);
        model.put("maxMoney", maxMoney);
        model.put("rollWhenMoneyWasHighest", rollWhenMoneyWasHighest);
        
        return "result";
    }
        
}
