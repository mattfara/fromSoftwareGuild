/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author matt
 */
public class MischeviousChildrenTest {
    
    MischeviousChildren children = new MischeviousChildren();
    
    public MischeviousChildrenTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testTrueTrue(){
        boolean result = children.areWeInTrouble(true,true);
        assertTrue(result);
    }
    @Test
    public void testTrueFalse(){
        boolean result = children.areWeInTrouble(true,false);
        assertFalse(result);
    }
    @Test
    public void testFalseTrue(){
        boolean result = children.areWeInTrouble(false,true);
        assertFalse(result);
    }
    @Test
    public void testFalseFalse(){
        boolean result = children.areWeInTrouble(false,false);
        assertTrue(result);
    }
    
    
}
