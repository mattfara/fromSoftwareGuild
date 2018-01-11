/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.basics.methods;

/**
 *
 * @author matt
 */
import java.util.Random;
public class BarelyControlledChaos {
    public static void main(String[] args) {

        String color = getColor(); // call color method here 
        String animal = getAnimal(); // call animal method again here 
        String colorAgain = getColor(); // call color method again here 
        int weight = getNum(5,200); // call number method, 
            // with a range between 5 - 200 
        int distance = getNum(10,20); // call number method, 
            // with a range between 10 - 20 
        int number = getNum(10000,20000); // call number method, 
            // with a range between 10000 - 20000 
        int time = getNum(2,6); // call number method, 
            // with a range between 2 - 6            
    
        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal 
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over " 
            + number + " " + colorAgain + " poppies for nearly " 
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, " 
            + "let me tell you!");
    } 
    
        public static String getColor(){
            Random rand = new Random();
            int animalNum = rand.nextInt(4);
            String color = "";
            
            switch (animalNum){
                case 0: color="black";
                        break;
                case 1: color="brown";
                        break;
                case 2: color= "yellow"; 
                        break;
                case 3: color= "purple";
                        break;
                case 4: color= "blue"; 
                        break;
            }
            return color;
        }
    
        public static String getAnimal(){
            Random rand = new Random();
            String animal = "";
            
            switch (rand.nextInt(4)){
                case 0: animal=  "lion";
                        break;
                case 1: animal="tiger";
                        break;
                case 2: animal="bear"; 
                        break;
                case 3: animal= "human";
                        break;
                case 4: animal= "monkey"; 
                        break;
            }
            return animal;
        } 

        public static int getNum(int min, int max){
            Random rand = new Random();
            return rand.nextInt((max-min)+1) +min;
       }
    
    
    }
    

