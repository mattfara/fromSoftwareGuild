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
public class PlayOutsideUnitTest {
    
    PlayOutside play = new PlayOutside();
    
    public PlayOutsideUnitTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPlayOutside70AndNotSummer(){
        assertTrue(play.playOutside(70,false));
    }
    @Test
    public void testPlayOutside95AndNotSummer(){
        assertFalse(play.playOutside(95,false));
    }
    @Test
    public void testPlayOutside95AndSummer(){
        boolean result = play.playOutside(70, false);
        assertTrue(result);
    }
    
    public void testPlayOutside59AndSummer(){
        boolean result = play.playOutside(59, false);
        assertFalse(result);
    }
    
    
}
