/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */

public class NewFizz {
    public static void main(String[] args){
            
        
        
		for(int i = 0; i < 31; i++) {
			String fizz = (i%3 == 0) ? "fizz" : "";
			String buzz = (i%5 == 0) ? "buzz" : "";
                        String num = Integer.toString(i);
			System.out.println(((fizz.equals("") && buzz.equals("")) ? num : fizz + buzz));
                        
		}

}
}

//			String output = (fizz.equals("") && buzz.equals("")) ? num : fizz + buzz;
//                        System.out.println(output);
