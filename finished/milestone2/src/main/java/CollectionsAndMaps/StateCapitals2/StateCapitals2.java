/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionsAndMaps.StateCapitals2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author matt
 */
public class StateCapitals2 {
    public static void main(String[] args) {
        int userMin;
        UserIO io = new UserIOImpl();
        Scanner sc = new Scanner(System.in);
        Map<String, Capital> statesAndCapitals = new HashMap<>();
        
        Capital montgomery = new Capital("Montgomery", 205000, 156);
        Capital juneau = new Capital("Juneau", 31000, 3255);
        Capital phoenix = new Capital("Phoenix", 144500, 517);

        statesAndCapitals.put("Alabama", montgomery);
        statesAndCapitals.put("Alaska", juneau);
        statesAndCapitals.put("Arizona", phoenix);
        
        Set<String> states = statesAndCapitals.keySet();
        
        
        
        for (String currentState : states){
          
            System.out.print(currentState +" - ");
            
            System.out.print(statesAndCapitals.get(currentState).getName() + " | ");
            System.out.print("Pop: "+statesAndCapitals.get(currentState).getPopulation() + " | ");
            System.out.println("Area: "+statesAndCapitals.get(currentState).getSquareMileage() + " sq mi");
        }
        
        
        userMin = io.readInt("What is the minimum population for which you'd like to see a state?");
        
        for (String currentState : states){
            if (statesAndCapitals.get(currentState).getPopulation() >= userMin) {
                System.out.print(currentState +" - ");
                System.out.print(statesAndCapitals.get(currentState).getName() + " | ");
                System.out.print("Pop: "+statesAndCapitals.get(currentState).getPopulation() + " | ");
                System.out.println("Area: "+statesAndCapitals.get(currentState).getSquareMileage() + " sq mi");
            }
        }
        
    
    }
 //I guess this could be broken out into a third class responsible for displaying shit       
 //might consider using overloaded methods here   
}
                
        
    
    
    //make a method that takes in user input
    //go fix the implementation we were using before
    //bring here
    //use the print method
    //use the readInt method
    
    