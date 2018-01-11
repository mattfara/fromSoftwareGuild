/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.dao.AddressDao;
import com.sg.superherosightingsspringmvc.dao.LocationDao;
import com.sg.superherosightingsspringmvc.dao.OrganizationDao;
import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
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
public class OrganizationDaoTest {
    private static OrganizationDao organizationDao;
    private static LocationDao locationDao;
    private static AddressDao addressDao;
    
    CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();
    
    public OrganizationDaoTest() {
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
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        tdh.clearTables();
        
    }
    @After
    public void tearDown() {
        tdh.clearTables();
    }
    @Test
    public void addGetDeleteOrganization() {
        //arrange
        //how is the ID being set? 
        Organization org = new Organization();
        
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

        loc.setLatitude(" 23, 23");
        loc.setLongitude(" 23, 23");

        loc = locationDao.createLocation(loc);
        
        org.setDescription("Cool place for cool mutants!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.TRUE);
        org.setPhone("2159739182");
        org.setLocation(loc);
        org = organizationDao.createOrganization(org);
        Organization actualOrg = organizationDao.getOrganizationById(org.getOrganizationId());
        String result = c.compareObjects(org, actualOrg);
        
        assertEquals(result, "");
        organizationDao.deleteOrganization(organizationDao.getOrganizationById(org.getOrganizationId()));
        assertNull(organizationDao.getOrganizationById(org.getOrganizationId()));
    }
    
    @Test
    public void updateOrganization() {
        //arrange
        //how is the ID being set? 
        Organization org = new Organization();
        
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
        Location addedLoc = locationDao.createLocation(loc);
        
        
        org.setDescription("Cool place for cool mutants!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.TRUE);
        org.setPhone("2159739182");
        org.setLocation(addedLoc);
        Organization added = organizationDao.createOrganization(org);
        
        
        
        org.setDescription("Cool place for cool people with coding powers!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.FALSE);
        org.setPhone("2159739182");

        Organization updated = organizationDao.updateOrganization(org);
        
        String result = c.compareObjects(added, updated);
        
        assertEquals(result, "");
        
        
    }
    @Test
    public void getAllOrganizations() {

        Organization org = new Organization();
        
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
        
        org.setDescription("Cool place for cool mutants!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.TRUE);
        org.setPhone("2159739182");
        org.setLocation(loc);
        

        Organization org2 = new Organization();

        org2.setDescription("Cool place for cool people with coding powers!");
        org2.setName("The Software Guild");
        org2.setIsGood(Boolean.FALSE);
        org2.setPhone("2159739182");
        org2.setLocation(loc2);
        
        Organization createdOrg1 = organizationDao.createOrganization(org);
        Organization createdOrg2 = organizationDao.createOrganization(org2);

        List<Organization> organizations = organizationDao.getAllOrganizations(0, 10);
        assertEquals(2, organizations.size());
        
        String result1 = c.compareObjects(createdOrg1, organizations.get(0));
        String result2 = c.compareObjects(createdOrg2, organizations.get(0));
        String result3 = c.compareObjects(createdOrg1, organizations.get(1));
        String result4 = c.compareObjects(createdOrg2, organizations.get(1));
        
        assertTrue(result1.equals("") || result2.equals(""));
        
        assertTrue(result3.equals("") || result4.equals(""));
        

    }
}