/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.review.one.testingcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author matt
 */
public class Trie {
    public static void main(String[] args) {
        
        Map<String, Integer> myMap = new HashMap();
        myMap.put("Honey I Shrunk the Kids", 1992);
        myMap.put("Honey I Shrunk the Kids 2", 1994);
        
        Set<String> titles = myMap.keySet();
        
        for (String currentTitle : titles){
            if (currentTitle.contains("the")){System.out.println(currentTitle);}
        }
        
        
        
        
    }
}
