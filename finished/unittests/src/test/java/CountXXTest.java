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
public class CountXXTest {
    
    CountXX count = new CountXX();
    
    public CountXXTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testabcxx(){
        assertEquals(count.countXX("abcxx"),1);
    }
    
    @Test
    public void testxxx(){
        assertEquals(count.countXX("xxx"),2);
    }
    
    @Test
    public void testxxxx(){
        assertEquals(count.countXX("xxxx"),3);
    }
}
