/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.CreateAndAddObjectsForServiceTests;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
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
public class OrganizationServiceTest {

    private static OrganizationService organizationService;
    private static LocationService locationService;
    private static AddressService addressService;
    private static SuperPersonService superPersonService;
    CreateAndAddObjectsForServiceTests co = new CreateAndAddObjectsForServiceTests();

    CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();

    public OrganizationServiceTest() {
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        organizationService = ctx.getBean("organizationService", OrganizationService.class);
        locationService = ctx.getBean("locationService", LocationService.class);
        addressService = ctx.getBean("addressService", AddressService.class);
        superPersonService = ctx.getBean("superPersonService", SuperPersonService.class);
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

        Address addedAddress = addressService.createAddress(add1);

        loc.setDescription("Cool place for cool mutants!");

        loc.setName("The Software Guild");

        loc.setAddress(add1);

        loc.setLatitude(" 23, 23");
        loc.setLongitude(" 23, 23");

        loc = locationService.createLocation(loc);

        org.setDescription("Cool place for cool mutants!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.TRUE);
        org.setPhone("2159739182");
        org.setLocation(loc);
        org = organizationService.createOrganization(org);
        Organization actualOrg = organizationService.getOrganizationById(org.getOrganizationId());
        String result = c.compareObjects(org, actualOrg);

        assertEquals(result, "");
        organizationService.deleteOrganization(organizationService.getOrganizationById(org.getOrganizationId()));
        assertNull(organizationService.getOrganizationById(org.getOrganizationId()));
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

        Address addedAddress = addressService.createAddress(add1);

        loc.setDescription("Cool place for cool mutants!");
        loc.setName("The Software Guild");
        loc.setAddress(add1);
        loc.setLatitude("23, 32");
        loc.setLongitude("23, 23");
        Location addedLoc = locationService.createLocation(loc);

        org.setDescription("Cool place for cool mutants!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.TRUE);
        org.setPhone("2159739182");
        org.setLocation(addedLoc);
        Organization added = organizationService.createOrganization(org);

        org.setDescription("Cool place for cool people with coding powers!");
        org.setName("The Software Guild");
        org.setIsGood(Boolean.FALSE);
        org.setPhone("2159739182");

        Organization updated = organizationService.updateOrganization(org);

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

        Address addedAddress = addressService.createAddress(add1);

        Address add2 = new Address();
        add2.setStreet("123 Fake Street");
        add2.setCity("Faketown");
        add2.setState("OX");
        add2.setZipcode("12345");

        Address addedAddress2 = addressService.createAddress(add2);

        loc.setDescription("Cool place for cool mutants");
        loc.setName("The Software Guild");
        loc.setAddress(add1);
        loc.setLatitude("23, 23");
        loc.setLongitude("23, 23");
        Location createdLoc1 = locationService.createLocation(loc);
        //arrange

        Location loc2 = new Location();

        loc2.setLocationId(2);
        loc2.setDescription("The home of Pat Toner");
        loc2.setName("The Software Guild");
        loc2.setAddress(add2);
        loc2.setLatitude("223, 32");
        loc2.setLongitude("233, 23");

        Location createdLoc2 = locationService.createLocation(loc2);

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

        Organization createdOrg1 = organizationService.createOrganization(org);
        Organization createdOrg2 = organizationService.createOrganization(org2);

        List<Organization> organizations = organizationService.getAllOrganizations(0, 10);
        assertEquals(2, organizations.size());

        String result1 = c.compareObjects(createdOrg1, organizations.get(0));
        String result2 = c.compareObjects(createdOrg2, organizations.get(0));
        String result3 = c.compareObjects(createdOrg1, organizations.get(1));
        String result4 = c.compareObjects(createdOrg2, organizations.get(1));

        assertTrue(result1.equals("") || result2.equals(""));

        assertTrue(result3.equals("") || result4.equals(""));

    }

    @Test
    public void testGetAllOrganizationsBySuperPerson() {
        //make some people
        SuperPerson sp1 = co.createAndAddSuperPerson();
        SuperPerson sp2 = co.createAndAddSuperPerson();

        //make some orgs
        Organization o1 = co.createAndAddOrganization();
        Organization o2 = co.createAndAddOrganization();

        //associate them 
        superPersonService.addSuperPersonToOrganization(sp1, o1);
        superPersonService.addSuperPersonToOrganization(sp2, o1);
        superPersonService.addSuperPersonToOrganization(sp2, o2);

        //pull them out lists
        List<Organization> listOfOrg1 = organizationService.getAllOrganizationsBySuperPerson(sp1, 0, 10);
        List<Organization> listOfOrg2 = organizationService.getAllOrganizationsBySuperPerson(sp2, 0, 10);

        //assertions
        assertEquals(1, listOfOrg1.size());
        assertEquals(2, listOfOrg2.size());
        for (Organization currentOrg : listOfOrg2) {

            String result1 = c.compareObjects(o1, currentOrg);
            String result2 = c.compareObjects(o2, currentOrg);

            assertTrue(result1.equals("") ^ result2.equals(""));
        }

        String result3 = c.compareObjects(o1, listOfOrg1.get(0));
        assertEquals("", result3);

    }

}
