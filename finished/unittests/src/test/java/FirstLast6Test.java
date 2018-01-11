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
public class FirstLast6Test {
    
    FirstLast6 fl = new FirstLast6();
    
    public FirstLast6Test() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test126(){
        assertTrue(fl.firstLast6(new int[]{1,2,6}));
    }
    
    @Test
    public void test6123(){
        assertTrue(fl.firstLast6(new int[]{6,1,2,3}));
    }
    @Test
    public void test13And6123(){
        assertFalse(fl.firstLast6(new int[]{13,6,1,2,3}));
    }
    @Test
    public void test6(){
        assertTrue(fl.firstLast6(new int[]{6}));
    }
    @Test
    public void test1(){
        assertFalse(fl.firstLast6(new int[]{1}));
    }
    
}
