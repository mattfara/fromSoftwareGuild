/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class Scoping {
    public static void main(String[] args) {
        int age = 42;
        System.out.println(age);
        for (int i = 0; i<5; i++){
            System.out.println(i);
            System.out.println(age);
        }
        
        if (age<18){
            //System.out.println(i);//can't see i
            int yearsToWait = age-18;
        }
            
        
    }
}
