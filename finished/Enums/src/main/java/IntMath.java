/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */
public class IntMath{
    App myApp = new App();
//    public static int calculate(MathOperator operator, int operand1, int operand2) {
//       
//        switch(operator) {
//            case PLUS:
//                return operand1 + operand2;
//            case MINUS:
//                return operand1 - operand2;
//            case MULTIPLY:
//                return operand1 * operand2;
//            case DIVIDE:
//                return operand1 / operand2;
//            default:
//                throw new UnsupportedOperationException();
//        }
//    }

    int calculate(App.MathOperator currentOperator, int operand1, int operand2) {
        switch(currentOperator) {
            case PLUS:
                return operand1 + operand2;
            case MINUS:
                return operand1 - operand2;
            case MULTIPLY:
                return operand1 * operand2;
            case DIVIDE:
                return operand1 / operand2;
            default:
                throw new UnsupportedOperationException();
        }
    }
    
    public enum MathOperator {
            PLUS, MINUS, MULTIPLY, DIVIDE
        }
    
}