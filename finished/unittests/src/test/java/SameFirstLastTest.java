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
public class SameFirstLastTest {
    
    SameFirstLast same = new SameFirstLast();
    
    public SameFirstLastTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sameFirstLast method, of class SameFirstLast.
     */
    @Test
    public void test123() {
        assertFalse(same.sameFirstLast(new int[]{1,2,3}));
    }
    @Test
    public void test1231() {
        assertTrue(same.sameFirstLast(new int[]{1,2,3,1}));
    }
    @Test
    public void test121() {
        assertTrue(same.sameFirstLast(new int[]{1,2,1}));
    }
    @Test
    public void test11() {
        assertTrue(same.sameFirstLast(new int[]{1,1}));
    }
    @Test
    public void test1() {
        assertTrue(same.sameFirstLast(new int[]{1}));
    }
}
