/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt
 */
public class CanHazTableTest {
    
    CanHazTable table = new CanHazTable();
    
    public CanHazTableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of canHazTable method, of class CanHazTable.
     */
    @Test
    public void testMe2Her10() {
        int result = table.canHazTable(2, 10);
        assertEquals(0,result);
    }
    @Test
    public void testMe10Her2() {
        int result = table.canHazTable(10, 2);
        assertEquals(0,result);
    }
    @Test
    public void testMe10Her10() {
        int result = table.canHazTable(10, 10);
        assertEquals(2,result);
    }
    @Test
    public void testMe2Her2() {
        int result = table.canHazTable(2, 2);
        assertEquals(0,result);
    }
    @Test
    public void testMe6Her6() {
        int result = table.canHazTable(6, 6);
        assertEquals(1,result);
    }
    @Test
    public void testMe5Her10() {
        int result = table.canHazTable(5, 10);
        assertEquals(2,result);
    }
    @Test
    public void testMe5Her2() {
        int result = table.canHazTable(5, 2);
        assertEquals(0,result);
    }
    @Test
    public void testMe5Her5() {
        int result = table.canHazTable(5, 5);
        assertEquals(1,result);
    }
    
//    public void testMeNeg2Her11() {
//        int result = table.canHazTable(-2, 11);
//        assertEquals("At least one number is out of range",result);
//    }
}
