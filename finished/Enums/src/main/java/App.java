
import java.util.Scanner;
import static javafx.scene.effect.BlendMode.MULTIPLY;
import static javafx.scene.input.KeyCode.DIVIDE;
import static javafx.scene.input.KeyCode.MINUS;
import static javafx.scene.input.KeyCode.PLUS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */

//loop through enum
//check if currentOperator.valueOf(userInput)
//

public class App {
    public static void main(String[] args) {
        IntMath math = new IntMath();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is number 1?");
        int operand1 = sc.nextInt();
        System.out.println("What is number 2?");
        int operand2 = sc.nextInt();
        
        
        for (MathOperator currentOperator : MathOperator.values()){
            int result = math.calculate(currentOperator, operand1, operand2);
            System.out.println(result);
        }
    }
    
        public enum MathOperator {
            PLUS, MINUS, MULTIPLY, DIVIDE
        }
    
}

