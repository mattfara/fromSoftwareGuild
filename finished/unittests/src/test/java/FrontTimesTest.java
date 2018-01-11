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
public class FrontTimesTest {
    
    FrontTimes front = new FrontTimes();
    
    public FrontTimesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

        // Given a String and a non-negative int n, we'll say that the 
    // front of the String is the first 3 chars, or whatever is there 
    // if the String is less than length 3. Return n copies of the front; 
    //
    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"
    @Test
    public void testChocolate2(){
        String result = front.frontTimes("Chocolate",2);
        assertEquals(result, "ChoCho");
    }
    @Test
    public void testChocolate3(){
        String result = front.frontTimes("Chocolate",3);
        assertEquals(result, "ChoChoCho");
    }
    @Test
    public void testAbc3(){
        String result = front.frontTimes("Abc",3);
        assertEquals(result, "AbcAbcAbc");
    }
    @Test
    public void testA4(){
        String result = front.frontTimes("A",4);
        assertEquals(result, "AAAA");
    }
}
