/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
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
public class LocationDaoTest {

    private static LocationDao locationDao;
    private static AddressDao addressDao;
    CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();
    
    
    public LocationDaoTest() {
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly=false)
    @BeforeClass
    public static void setUpClass() {
        //        ApplicationContext ctx
//                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    }

    @AfterClass
    public static void tearDownClass() {
                
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        tdh.clearTables();

    }

    @After
    public void tearDown() {
        tdh.clearTables();
    }

    @Test
    public void addGetDeleteLocation() {
        //arrange
        Location loc = new Location();

        Address add1 = new Address();
        add1.setStreet("123 Fake Street");
        add1.setCity("Faketown");
        add1.setState("OX");
        add1.setZipcode("12345");

        Address addedAddress = addressDao.createAddress(add1);

        loc.setDescription("Cool place for cool mutants!");

        loc.setName("The Software Guild");

        loc.setAddress(add1);

        loc.setLatitude("23, 23");
        loc.setLongitude("23, 23");

        loc = locationDao.createLocation(loc);

        Location actualLoc = locationDao.getLocationById(loc.getLocationId());

        String result = c.compareObjects(loc, actualLoc);
        assertEquals(result, "");

        locationDao.deleteLocation(locationDao.getLocationById(loc.getLocationId()));

        assertNull(locationDao.getLocationById(loc.getLocationId()));
    }

    @Test
    public void updateLocation() {

        //First Location 
        Location loc = new Location();

        Address add1 = new Address();
        add1.setStreet("123 Fake Street");
        add1.setCity("Faketown");
        add1.setState("OX");
        add1.setZipcode("12345");

        Address addedAddress = addressDao.createAddress(add1);

        loc.setDescription("Cool place for cool mutants!");
        loc.setName("The Software Guild");
        loc.setAddress(add1);
        loc.setLatitude("23, 32");
        loc.setLongitude("23, 23");
        Location added = locationDao.createLocation(loc);

        loc.setDescription("Terrible place for awful coders and no lives!");
        loc.setName("The Software Guild");
        loc.setLatitude("25, 32");
        loc.setLongitude("39, 23");

        Location updated = locationDao.updateLocation(loc);

        String result = c.compareObjects(added, updated);
        assertEquals(result, "");

    }

    @Test
    public void getAllLocations() {
        //arrange
        //how is the ID being set? 
        Location loc = new Location();
        Address add1 = new Address();
        add1.setStreet("123 Fake Street");
        add1.setCity("Faketown");
        add1.setState("OX");
        add1.setZipcode("12345");

        Address addedAddress = addressDao.createAddress(add1);

        Address add2 = new Address();
        add2.setStreet("123 Fake Street");
        add2.setCity("Faketown");
        add2.setState("OX");
        add2.setZipcode("12345");

        Address addedAddress2 = addressDao.createAddress(add2);

        loc.setDescription("Cool place for cool mutants");
        loc.setName("The Software Guild");
        loc.setAddress(add1);
        loc.setLatitude("23, 23");
        loc.setLongitude("23, 23");
        Location createdLoc1 = locationDao.createLocation(loc);
        //arrange

        Location loc2 = new Location();
        
        loc2.setLocationId(2);
        loc2.setDescription("The home of Pat Toner");
        loc2.setName("The Software Guild");
        loc2.setAddress(add2);
        loc2.setLatitude("223, 32");
        loc2.setLongitude("233, 23");

        Location createdLoc2 = locationDao.createLocation(loc2);

        List<Location> locations = locationDao.getAllLocations(0, 10);
        assertEquals(2, locations.size());

        String result1 = c.compareObjects(createdLoc1, locations.get(0));
        String result2 = c.compareObjects(createdLoc2, locations.get(0));
        String result3 = c.compareObjects(createdLoc1, locations.get(1));
        String result4 = c.compareObjects(createdLoc2, locations.get(1));

        assertTrue(result1.equals("") || result2.equals(""));

        assertTrue(result3.equals("") || result4.equals(""));

    }
}
