/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

/**
 *
 * @author matt
 */
public class DifferentKettleOfFish {
    public static void main(String[] args) {

        for (int fish = 1; fish<10; fish++){
      
            if(fish == 3){
                System.out.println("RED fish!");
            }else if(fish == 4){
                System.out.println("BLUE fish!");
            } else{
                System.out.println(fish + " fish!");
            }

      
        }

    }
}
//they look the same to me. I'm not sure what I'm missing