/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class FruitSalad {
    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        
        String[] fruitSalad;
        
        int miscFruit = 0;
        int saladAppleCount = 0;
        int saladOrangeCount = 0;
        int kindsOfFruit = 0;
        
        fruitSalad = new String[fruit.length];
        
        int i = 0;
        for (String aFruit : fruit) {
            if (kindsOfFruit<=12){
                if (aFruit.contains("berry")){
                    fruitSalad[i]=aFruit;
                    kindsOfFruit++; i++;
                } else if (saladAppleCount<=2 && aFruit.contains("Apple") ){
                    fruitSalad[i]=aFruit;
                    kindsOfFruit++; i++; saladAppleCount++;
                } else if (aFruit.contains("Orange") && saladOrangeCount<=1) {
                    fruitSalad[i]=aFruit;
                    kindsOfFruit++; i++; saladOrangeCount++;
                } else if (!aFruit.contains("Tomato") && (!aFruit.contains("Apple")) && (!aFruit.contains("Orange"))){
                    fruitSalad[i]=aFruit;
                    kindsOfFruit++; i++; miscFruit++;
                }
            }
        }
        
        System.out.println("Total number of fruits: " + (miscFruit+saladAppleCount+saladOrangeCount));
        
        for (String theFruit : fruitSalad ) {
            System.out.println(theFruit!=null ? theFruit+" ":"");
        }
        
        
    }
}
