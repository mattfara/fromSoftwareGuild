/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionsAndMaps;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author matt
 */

//why do they print out of order?
//must have something to do with 
public class StateCapitals {
    public static void main(String[] args) {
        HashMap<String, String> statesAndCapitals = new HashMap<>();
        statesAndCapitals.put("Alabama","Montgomery");
        statesAndCapitals.put("Alaksa","Juneau");
        statesAndCapitals.put("Arizon", "Phoenix");
        statesAndCapitals.put("Arkansas","Little Rock");
        statesAndCapitals.put("California","Sacramento");
        
        Set<String> states = statesAndCapitals.keySet();
        Collection<String> capitals = statesAndCapitals.values();
        
        System.out.println("STATES");
        System.out.println("======");
        
        for (String currentState : states){
            System.out.println(currentState);
        }
        
        System.out.println("CAPITALS");
        System.out.println("========");
        
        for (String currentCapital : capitals ){
            System.out.println(currentCapital);
        }
        
        System.out.println("STATE/CAPITAL PAIRS");
        System.out.println("===================");
        
        for (String currentState : states){
            System.out.println("The capital of "+currentState+" is " + statesAndCapitals.get(currentState));
        }
        
        
        
                
        
    }
    
    
    
    
}
