
import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class DaysToFriday {
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        
        MathOperators operate = new MathOperator();
    
        public void daysToFriday(){
            
            
            
            Scanner sc = new Scanner(System.in);
            System.out.println("What day of the week is it?");
            String input = sc.nextLine().topUperCase();
            
             ans;
            for (operate day : Day ){
                if day.valueOf(input){
                    
                }
            }
            
            Day.valueOf(input);
            
            
            
            
            switch(ans){
                case MONDAY: System.out.println("There are " + 11%7 + " days to Friday"); 
                            break;
                case TUESDAY: System.out.println("There are " + 10%7 + " days to Friday");
                            break;
                case WEDNESDAY: System.out.println("There are " + 9%7 + " days to Friday");
                            break;
                case THURSDAY: System.out.println("There are " + 8%7 + " days to Friday");
                            break;
                case FRIDAY: System.out.println("There are " + 7%7 + " days to Friday");
                            break;
                case SATURDAY: System.out.println("There are " + 6%7 + " days to Friday");
                            break;
                case SUNDAY: System.out.println("There are " + 5%7 + " days to Friday");
                            break;
                default: System.out.println("Unknown day....");
            }
        }
        
        
        
    }
}
