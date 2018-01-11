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
public class SleepingInTest {
    
    SleepingIn sleep = new SleepingIn();
    
    public SleepingInTest() {
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

    /**
     * Test of canSleepIn method, of class SleepingIn.
     */
    @Test
    public void testNotWeekdayNotVacation() {
        assertTrue(sleep.canSleepIn(false, false));
    }
    
    @Test
    public void testWeekdayNotVacation() {
        assertFalse(sleep.canSleepIn(true, false));
    }
    
    @Test
    public void testNotWeekdayVacation() {
        assertTrue(sleep.canSleepIn(false, true));
    }
    
    @Test
    public void testWeekdayVacation() {
        assertTrue(sleep.canSleepIn(true, true));
    }
    
}
