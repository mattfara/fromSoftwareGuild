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
public class MakePiTest {
    public static final int[] PI = {3,1,4,1,5,9,2,6,5,3,5,9};
    MakePi pi = new MakePi();
    
    public MakePiTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makePi method, of class MakePi.
     */
    @Test
    public void test3() {
        int[] result = pi.makePi(3);
        for (int i=0 ; i<3 ; i++){
            assertEquals(result[i],PI[i]);
        }           
    }
    @Test
    public void test7() {
        int[] result = pi.makePi(7);
        for (int i=0 ; i<7 ; i++){
            assertEquals(result[i],PI[i]);
        }
                
    }
}
