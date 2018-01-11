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
public class ABBATest {
    
    ABBA abba = new ABBA();
    
    public ABBATest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testHiBye(){
        String result = abba.abba("Hi","Bye");
        assertEquals(result, "HiByeByeHi");
    }
    @Test
    public void testYoAlice(){
        String result = abba.abba("Yo","Alice");
        assertEquals(result, "YoAliceAliceYo");
    }
    @Test
    public void testWhatUp(){
        String result = abba.abba("What","Up");
        assertEquals(result, "WhatUpUpWhat");
    }
    
    @Test
    public void testBlankBlank(){
        String result = abba.abba("","");
        assertEquals(result, "");
    }
}
