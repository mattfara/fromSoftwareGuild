/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class FruitBasket {
    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
        int orangeCount = 0;
        int appleCount = 0;
        int fruitCount = fruit.length;
        String[] oranges = new String[61];
        String[] apples = new String[61];
        
        
        System.out.println("Total number of fruit in basket: " + fruitCount);
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i].equals("Orange")){
                orangeCount++;
                oranges[i]="Orange";
            } else {
                appleCount++;
                apples[i]="Apple";
            }
        }
        
        System.out.println("Number of apples: " + appleCount);
        System.out.println("Number of oranges: " + orangeCount);
        
    }
}
