/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonPower;
import static java.lang.Integer.compare;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
public class SuperPersonPowerDaoTest {

    private static SuperPersonPowerDao superPersonPowerDao;
    private static PowerDao powerDao;
    private static SuperPersonDao superPersonDao;

    private static TearDownHelper tdh = new TearDownHelper();
    
    CompareObjects c = new CompareObjects();
    
    public SuperPersonPowerDaoTest() {
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly=false)
   @BeforeClass
    public static void setUpClass() {
        tdh.clearTables();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        superPersonPowerDao = ctx.getBean("superPersonPowerDao", SuperPersonPowerDao.class);
        superPersonDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        powerDao = ctx.getBean("powerDao", PowerDao.class);
        tdh.clearTables();
    }

    @After
    public void tearDown() {
        tdh.clearTables();
    }

    /**
     * Test of createSuperPersonPower method, of class SuperPersonPowerDao.
     */
    @Test
    public void addGetDeleteSuperPersonPower() {
        //arrange
        SuperPersonPower spp = new SuperPersonPower();

        SuperPerson sp1 = new SuperPerson();
        sp1.setName("Batman");
        sp1.setDescription("some rich dude with gadgets");
        sp1.setIsGood(true);
        sp1 = superPersonDao.createSuperPerson(sp1);
        
        Power pow1 = new Power();
        pow1.setName("Super Sneezing");
        pow1 =powerDao.createPower(pow1);
        
        spp.setSuperPerson(sp1);
        spp.setPower(pow1);
        spp = superPersonPowerDao.createSuperPersonPower(spp);
        SuperPersonPower fromDb = superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId());
        
        String result = c.compareObjects(spp, fromDb);
        
        assertEquals(result, "");

        superPersonPowerDao.deleteSuperPersonPower(superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId()));
        assertNull(superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId()));
    }

    /**
     * Test of getAllSuperPersonPoweres method, of class SuperPersonPowerDao.
     */
    @Test
    public void testGetAllSuperPersonPowers() {

        SuperPersonPower spp1 = new SuperPersonPower();

        SuperPerson sp1 = new SuperPerson();
        sp1.setName("Batman");
        sp1.setDescription("some rich dude with gadgets");
        sp1.setIsGood(true);
        sp1 = superPersonDao.createSuperPerson(sp1);
        
        Power pow1 = new Power();
        pow1.setName("Super Sneezing");
        pow1 =powerDao.createPower(pow1);
        
        spp1.setSuperPerson(sp1);
        spp1.setPower(pow1);
        spp1 = superPersonPowerDao.createSuperPersonPower(spp1);

        SuperPersonPower spp2 = new SuperPersonPower();

            SuperPerson sp2 = new SuperPerson();
        sp2.setName("Batman");
        sp2.setDescription("some rich dude with gadgets");
        sp2.setIsGood(true);
        sp2 = superPersonDao.createSuperPerson(sp2);
        
        Power pow2 = new Power();
        pow2.setName("Super Sneezing");
        pow2 =powerDao.createPower(pow2);
        
        spp2.setSuperPerson(sp2);
        spp2.setPower(pow2);
        spp2 = superPersonPowerDao.createSuperPersonPower(spp2);

        List<SuperPersonPower> superPersonPowers = superPersonPowerDao.getAllSuperPersonPowers(0, 10);
        assertEquals(2, superPersonPowers.size());
        
        SuperPersonPower firstSPP = superPersonPowers.get(0);
        /*
        PowerId: spp1 188 get(0) 188
                 spp2 189        189
        
        
        */
        
        
        SuperPersonPower secondSPP = superPersonPowers.get(1);
        
        String result1 = c.compareObjects(spp1, superPersonPowers.get(0));
        String result2 = c.compareObjects(spp2, superPersonPowers.get(0));
        String result3 = c.compareObjects(spp1, superPersonPowers.get(1));
        String result4 = c.compareObjects(spp2, superPersonPowers.get(1));
        
        assertTrue(result1.equals("") ^ result2.equals(""));
        assertTrue(result3.equals("") ^ result4.equals(""));
        
    }
}
