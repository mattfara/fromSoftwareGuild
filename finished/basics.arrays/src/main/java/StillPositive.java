/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */

public class StillPositive {
    public static void main(String[] args) {
        System.out.println("Gotta stay positive!");
        int[] numbers = {389, -447, 26, -485, 712, -884, 94, -64, 868, -776, 227, -744, 422, -109, 259, -500, 278, -219, 799, -311};
        
        for (int num : numbers){
            if (num>0) {System.out.println(num);} //assuming 0 isn't positive
        }
        //this is an enhanced for loop, which is applicable because I don't need to track the index of the elements in the array
        
    }
}
