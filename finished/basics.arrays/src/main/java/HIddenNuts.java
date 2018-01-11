/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
import java.util.Random;
public class HIddenNuts {
    public static void main(String[] args) {

        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        System.out.println("The nut has been hidden ...");

        
	for (int i = 0; i < hidingSpots.length; i++){
            if (hidingSpots[i]!=null){
              System.out.println("Found it! It's in spot #"+i);
          }
        }	
        
        
    }
}
