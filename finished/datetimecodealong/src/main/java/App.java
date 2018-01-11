
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
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
public class App {
    public static void main(String[] args) {
        //just for time now
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
        
        //to get 
        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);
        
        //also a way to set up a particular format pattern
        
        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String isoDate = ld.toString();
        System.out.println(isoDate);
        ld = LocalDate.parse(isoDate);
        System.out.println(ld);
        
        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);
        
        formatted = ld.format(DateTimeFormatter.ofPattern("yyyy--dd--MM"));
        System.out.println(formatted);
        
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        System.out.println(formatted);
     
        LocalDate userDate = readLocalDate("Enter a date");
    }
    
    public static LocalDate readLocalDate(String prompt) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        boolean isValid=false;
        LocalDate ld = LocalDate.now();
        while (!isValid){
            try{
                System.out.println(prompt);
            String userInput = sc.nextLine();
            ld = LocalDate.parse(userInput, formatter);
            isValid=true;
            } catch(DateTimeParseException ex){
                System.out.println("That's not a valid date");
            }
        }
        return ld;
    }
    
    
}
