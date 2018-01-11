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
public class MakeTagsTest {
    
    MakeTags make = new MakeTags();
    public MakeTagsTest() {
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
     * Test of makeTags method, of class MakeTags.
     */
    @Test
    public void testIAndYay() {
        assertEquals(make.makeTags("i", "Yay"), "<i>Yay</i>");
    }
    
    @Test
    public void testIAndHello() {
        assertEquals(make.makeTags("i", "Hello"), "<i>Hello</i>");
    }
    
    @Test
    public void testCiteAndYay() {
        assertEquals(make.makeTags("cite", "Yay"), "<cite>Yay</cite>");
    }
}
