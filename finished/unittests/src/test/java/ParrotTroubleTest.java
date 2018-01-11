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
public class ParrotTroubleTest {
  
    ParrotTrouble p = new ParrotTrouble();
    
    public ParrotTroubleTest() {
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
    public void testTrue6() {
        assertTrue(p.parrotTrouble(true, 6));
    }
    
    @Test
    public void testTrue7() {
        assertFalse(p.parrotTrouble(true, 7));
    }
    
    @Test
    public void testFalse6() {
        assertFalse(p.parrotTrouble(false, 6));
    }
    
    @Test
    public void testFalse7() {
        assertFalse(p.parrotTrouble(false, 7));
    }
    
    @Test
    public void testFalse20() {
        assertFalse(p.parrotTrouble(false, 20));
    }
    
    @Test
    public void testTrue20() {
        assertFalse(p.parrotTrouble(false, 20));
    }
    
    @Test
    public void testFalse21() {
        assertFalse(p.parrotTrouble(false, 21));
    }
    
    @Test
    public void testTrue21() {
        assertTrue(p.parrotTrouble(true, 21));
    }
}
