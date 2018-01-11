/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */

import java.util.Scanner;
public class WindowMaster {

    public static void main(String[] args) {
        float height;
        float width;
        String stringHeight = "";
        String stringWidth = "";
        
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("What is the height of your window in feet?");
        stringHeight = myScanner.nextLine();
        
        System.out.println("What is the width of your window in feet?");
        stringWidth = myScanner.nextLine();
        
        height = Integer.parseInt(stringHeight);
        width = Integer.parseInt(stringWidth);
        
        float area = height*width;
        float perimeter = height*2 + width*2;
        
        System.out.println("The area of your window is " + area);
        System.out.println("The perimeter of your window is " + perimeter);
        
        float totalCost = (area*3.5f + perimeter*2.25f);

  	System.out.println("The total cost of your window is " + totalCost);
        
        
    }
    
}