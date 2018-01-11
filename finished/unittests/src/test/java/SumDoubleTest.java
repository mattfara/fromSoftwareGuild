/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt
 */
public class SumDoubleTest {
    
    SumDouble sum = new SumDouble();
    
    public SumDoubleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test12() {
        assertEquals(sum.sumDouble(1, 2),3);
    }
    
    @Test
    public void test32() {
        assertEquals(sum.sumDouble(3, 2),5);
    }
    
    @Test
    public void test22() {
        assertEquals(sum.sumDouble(2, 2),8);
    }
}
