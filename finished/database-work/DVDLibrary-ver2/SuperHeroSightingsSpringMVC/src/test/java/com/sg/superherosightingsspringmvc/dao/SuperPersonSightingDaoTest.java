/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonSighting;
import static java.lang.Integer.compare;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
public class SuperPersonSightingDaoTest {
    private static TearDownHelper tdh = new TearDownHelper();
    CompareObjects c = new CompareObjects();
    
    private static SuperPersonSightingDao superPersonSightingDao;
    private static SuperPersonDao superPersonDao;
    private static SightingDao sightingDao;
    private static AddressDao addressDao;
    private static LocationDao locationDao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    public SuperPersonSightingDaoTest() {
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
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        superPersonSightingDao = ctx.getBean("superPersonSightingDao", SuperPersonSightingDao.class);
        superPersonDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        tdh.clearTables();
    }
    @After
    public void tearDown() {
        tdh.clearTables();
    }
    /**
     * Test of createSuperPersonSighting method, of class
     * SuperPersonSightingDao.
     */
    @Test
    public void addGetDeleteSuperPersonSighting() {
        //arrange
        Address add1 = new Address();
        Location loc = new Location();
        Sighting sighting1 = new Sighting();
        
        
        SuperPerson sp1 = new SuperPerson();
        sp1.setName("Batman");
        sp1.setDescription("some rich dude with gadgets");
        sp1.setIsGood(true);
        SuperPerson createdSP1 = superPersonDao.createSuperPerson(sp1);
        add1.setStreet("123 Fake Street");
        add1.setCity("Faketown");
        add1.setState("OX");
        add1.setZipcode("12345");
        add1 = addressDao.createAddress(add1);
        loc.setDescription("Cool place for cool mutants");
        loc.setName("The Software Guild");
        loc.setAddress(add1);
        loc.setLatitude("23, 23");
        loc.setLongitude("23, 23");
        loc = locationDao.createLocation(loc);
        
        sighting1.setDate(LocalDate.parse("20171022", formatter));
        sighting1.setLocation(loc);
        sighting1.setDescription("SuperPerson was learning to code....");
        Sighting createdSighting1 = sightingDao.createSighting(sighting1);
        SuperPersonSighting sps = new SuperPersonSighting();
        sps.setSuperPerson(createdSP1);
        sps.setSighting(createdSighting1);
        sps = superPersonSightingDao.createSuperPersonSighting(sps);
        SuperPersonSighting fromDb = superPersonSightingDao.getSuperPersonSightingById(sps.getSuperPersonSightingId());
        String result = c.compareObjects(sps, fromDb);
        assertEquals(result, "");
        superPersonSightingDao.deleteSuperPersonSighting(superPersonSightingDao.getSuperPersonSightingById(sps.getSuperPersonSightingId()));
        assertNull(superPersonSightingDao.getSuperPersonSightingById(sps.getSuperPersonSightingId()));
    }
    /**
     * Test of getAllSuperPersonSightinges method, of class
     * SuperPersonSightingDao.
     */
    @Test
    public void testGetAllSuperPersonSightings() {
        SuperPerson sp1 = new SuperPerson();
        sp1.setName("Batman");
        sp1.setDescription("some rich dude with gadgets");
        sp1.setIsGood(true);
        SuperPerson createdSP1 = superPersonDao.createSuperPerson(sp1);        
        SuperPerson sp2 = new SuperPerson();
        sp2.setName("Trumpus");
        sp2.setDescription("builds walls");
        sp2.setIsGood(false);
        SuperPerson createdSP2 = superPersonDao.createSuperPerson(sp2);        
        Address add1 = new Address();
        add1.setStreet("123 Fake Street");
        add1.setCity("Faketown");
        add1.setState("OX");
        add1.setZipcode("12345");
        add1 = addressDao.createAddress(add1);
        Address add2 = new Address();
        add2.setStreet("123 Fake Street");
        add2.setCity("Faketown");
        add2.setState("OX");
        add2.setZipcode("12345");
        add2 = addressDao.createAddress(add2);
        Location loc = new Location();
        loc.setDescription("Cool place for cool mutants");
        loc.setName("The Software Guild");
        loc.setAddress(add1);
        loc.setLatitude("23, 23");
        loc.setLongitude("23, 23");
        loc = locationDao.createLocation(loc);
        //arrange
        Location loc2 = new Location();
        loc2.setDescription("The home of Pat Toner");
        loc2.setName("The Software Guild");
        loc2.setAddress(add2);
        loc2.setLatitude("223, 32");
        loc2.setLongitude("233, 23");
        loc2 = locationDao.createLocation(loc2);
        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("20171022", formatter));
        sighting1.setLocation(loc);
        sighting1.setDescription("SuperPerson was learning to code....");
        sighting1 = sightingDao.createSighting(sighting1);
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("20171021", formatter));
        sighting2.setLocation(loc2);
        sighting2.setDescription("SuperPerson was cruising....");
        sighting2 = sightingDao.createSighting(sighting2);
        SuperPersonSighting sps1 = new SuperPersonSighting();
        
        sps1.setSuperPerson(createdSP1);
        sps1.setSighting(sighting1);
        sps1 = superPersonSightingDao.createSuperPersonSighting(sps1);
        SuperPersonSighting sps2 = new SuperPersonSighting();
        sps2.setSuperPerson(createdSP2);
        sps2.setSighting(sighting2);
        sps2 = superPersonSightingDao.createSuperPersonSighting(sps2);
        List<SuperPersonSighting> spss = superPersonSightingDao.getAllSuperPersonSightings(0, 10);
        assertEquals(2, spss.size());
        String result1 = c.compareObjects(sps1, spss.get(0));
        String result2 = c.compareObjects(sps2, spss.get(0));
        String result3 = c.compareObjects(sps1, spss.get(1));
        String result4 = c.compareObjects(sps2, spss.get(1));

        assertTrue(result1.equals("") ^ result2.equals(""));
        assertTrue(result3.equals("") ^ result4.equals(""));
    }
}