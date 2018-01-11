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
public class Diff21Test {
    
    Diff21 diff = new Diff21();
    
    public Diff21Test() {
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
    public void test23() {
        assertEquals(diff.diff21(23),4);
    }
    
    @Test
    public void test10() {
        assertEquals(diff.diff21(10),11);
    }
    
    @Test
    public void test21() {
        assertEquals(diff.diff21(21),0);
    }
}
