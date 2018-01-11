/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.Power;
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
public class PowerServiceTest {
        private static TearDownHelper tearDown = new TearDownHelper();

    CompareObjects compare = new CompareObjects();

    private static PowerService powerService;

    public PowerServiceTest() {
    }
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // ask Spring for our Service
         tearDown.clearTables();
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        powerService = ctx.getBean("powerService", PowerService.class);
        
    }

    @After
    public void tearDown() {
        tearDown.clearTables();
    }

    @Test
    public void addGetDeletePower() {
        // arrange Street, City, State, Zipcode
        Power pow = new Power();
        pow.setName("Super Sneezing");
        // act
        powerService.createPower(pow);
        Power actualPower = powerService.getPowerById(pow.getPowerId());
        // assert

        String result = compare.compareObjects(pow, actualPower);
        assertEquals(result, "");
        // act
        powerService.deletePower(powerService.getPowerById(pow.getPowerId()));
        // assert
        assertNull(powerService.getPowerById(pow.getPowerId()));
    }

    @Test
    public void updatePower() {
        // arrange Street, City, State, Zipcode
        Power pow = new Power();
        pow.setName("Super Sneezing");
        Power added = powerService.createPower(pow);
        added.setName("Super Sleezing");
        //act
        Power updated = powerService.updatePower(added);
        //assert
        assertEquals(added, updated);
    }

    @Test

    public void getAllPowers() {

        // arrange Street, City, State, Zipcode
        Power pow1 = new Power();
        pow1.setName("Super Sneezing");
        Power pow2 = new Power();
        pow2.setName("Super Sleezing");

        //Integer numInDb = powerService.getAllPoweres(0, Integer.MAX_VALUE).size();
        Power createdPow1 = powerService.createPower(pow1);
        Power createdPow2 = powerService.createPower(pow2);

        //act
        List<Power> powers = powerService.getAllPowers(0, 10);

        //assert
        assertEquals(2, powers.size());
        String result1 = compare.compareObjects(createdPow1, powers.get(0));
        String result2 = compare.compareObjects(createdPow2, powers.get(0));
        String result3 = compare.compareObjects(createdPow1, powers.get(1));
        String result4 = compare.compareObjects(createdPow2, powers.get(1));
        assertTrue(result1.equals("") || result2.equals(""));
        assertTrue(result3.equals("") || result4.equals(""));

    }
}
