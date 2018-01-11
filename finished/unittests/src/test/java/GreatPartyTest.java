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
public class GreatPartyTest {
    
    //instantiated at the top such that not necessary to instantiate
    //in each inidividual test
    GreatParty party = new GreatParty();
    
    public GreatPartyTest() {
    }
    
    
    //runs once before full test
    @BeforeClass
    public static void setUpClass() {
    }
    
    //runs once after full test
    @AfterClass
    public static void tearDownClass() {
    }
    
    //used before a single test
    //not necessary for stateless
    @Before
    public void setUp() {
    }
    
    ////used after a single test
    //not necessary for stateless
    @After
    public void tearDown() {
    }

    /**
     * Test of greatParty method, of class GreatParty.
     */
    @Test
    public void test30False(){
        assertFalse(party.greatParty(30,false));
    }
    
    public void test39True(){
        assertFalse(party.greatParty(39,true));
    }
    
    public void test39False(){
        assertFalse(party.greatParty(39,false));
    }
    
    public void test40True(){
        assertTrue(party.greatParty(40,true));
    }
    
    public void test40False(){
        assertTrue(party.greatParty(40,false));
    }
    
    @Test public void test50True(){
        assertTrue(party.greatParty(50,false));    
    }
    
    @Test
    public void test60True(){
        assertTrue(party.greatParty(60,true));
    }
    
    @Test
    public void test60False(){
        assertTrue(party.greatParty(60,false));
    }
    
    @Test
    public void test61True(){
        assertTrue(party.greatParty(61, true));
    }
    
    @Test
    public void test61False(){
        assertFalse(party.greatParty(61, false));
    }
    
    
    @Test public void test70True(){
        assertTrue(party.greatParty(70,true));    
    }
    
    
    
}
