/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt
 */
public class StringTimesTest {
    
    public StringTimesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testHi2(){
        StringTimes string = new StringTimes();
        String result = string.stringTimes("Hi", 2);
        assertEquals(result, "HiHi");
    }
    @Test
    public void testHi3(){
        StringTimes string = new StringTimes();
        String result = string.stringTimes("Hi", 3);
        assertEquals(result, "HiHiHi");
    }
    @Test
    public void testHi1(){
        StringTimes string = new StringTimes();
        String result = string.stringTimes("Hi", 1);
        assertEquals(result, "Hi");
    }
    @Test
    public void testHi0(){
        StringTimes string = new StringTimes();
        String result = string.stringTimes("Hi", 0);
        assertEquals(result, "");
    }
    @Test
    public void testHiNeg3(){
        StringTimes string = new StringTimes();
        String result = string.stringTimes("Hi", -3);
        assertEquals(result, "You entered a negative number");
    }
    
   // @Test
//    public void testHiNonInteger() throws NumberFormatException {
//        StringTimes string = new StringTimes();
//        String result = string.stringTimes("Hi", 2.3353);
//        assertEquals(result, "You didn't enter an integer");
//    }
    
    //what about incorrect input types, like doubles or letters? netbeans doesn't even want me to try
}
