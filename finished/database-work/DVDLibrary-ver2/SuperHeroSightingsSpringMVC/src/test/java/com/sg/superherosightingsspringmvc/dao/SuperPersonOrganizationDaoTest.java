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
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonOrganization;
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
public class SuperPersonOrganizationDaoTest {
    
    private static TearDownHelper tearDown = new TearDownHelper();
    
    CompareObjects compare = new CompareObjects();
    
    private SuperPersonOrganizationDao dao;
    private SuperPersonDao superPersonDao;
    private OrganizationDao organizationDao;
    private LocationDao locationDao;
    private AddressDao addressDao;
    
    public SuperPersonOrganizationDaoTest() {
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
         tearDown.clearTables();
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        dao = ctx.getBean("superPersonOrganizationDao", SuperPersonOrganizationDao.class);
        superPersonDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        
        
    }
    @After
    public void tearDown() {
        tearDown.clearTables();
    }
    /**
     * Test of createSuperPersonOrganization method, of class SuperPersonOrganizationDao.
     */
    @Test
    public void addGetDeleteSuperPersonOrganization() {
        //arrange
        SuperPersonOrganization spo = new SuperPersonOrganization();
        
        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Saddam Hussein");
        superPerson.setDescription("Super hero");
        superPerson.setIsGood(false);
        superPersonDao.createSuperPerson(superPerson);
        
        Address address = new Address();
        address.setCity("Cleveland");
        address.setState("OH");
        address.setStreet("123 Hell Road");
        address.setZipcode("42069");
        addressDao.createAddress(address);
        
        Location location = new Location();
        location.setName("Iraq");
        location.setDescription("A utopian city");
        location.setAddress(address);
        location.setLatitude("100");
        location.setLongitude("100");
        locationDao.createLocation(location);
        
        Organization organization = new Organization();
        organization.setName("The Guys");
        organization.setDescription("A bunch of real ones");
        organization.setIsGood(false);
        organization.setLocation(location);
        organization.setPhone("3309067777");
        organizationDao.createOrganization(organization);
        
        
        spo.setSuperPerson(superPerson);
        spo.setOrganization(organization);        
        spo = dao.createSuperPersonOrganization(spo);
        SuperPersonOrganization fromDb = dao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId());
        
        String result = compare.compareObjects(spo, fromDb);
        assertEquals(result, "");
        
        dao.deleteSuperPersonOrganization(dao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId()));
        assertNull(dao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId()));
    }
    /**
     * Test of getAllSuperPersonOrganizationes method, of class SuperPersonOrganizationDao.
     */
    @Test
    public void testGetAllSuperPersonOrganizations() {
        
        
        SuperPersonOrganization spo1 = new SuperPersonOrganization();
        SuperPersonOrganization spo2 = new SuperPersonOrganization();
        
        SuperPerson superPerson = new SuperPerson();
        superPerson.setName("Saddam Hussein");
        superPerson.setDescription("Super hero");
        superPerson.setIsGood(false);
        superPersonDao.createSuperPerson(superPerson);
        
        Address address = new Address();
        address.setCity("Cleveland");
        address.setState("OH");
        address.setStreet("123 Hell Road");
        address.setZipcode("42069");
        addressDao.createAddress(address);
        
        Location location = new Location();
        location.setName("Iraq");
        location.setDescription("A utopian city");
        location.setAddress(address);
        location.setLatitude("100");
        location.setLongitude("100");
        locationDao.createLocation(location);
        
        Organization organization = new Organization();
        organization.setName("The Guys");
        organization.setDescription("A bunch of real ones");
        organization.setIsGood(false);
        organization.setLocation(location);
        organization.setPhone("3309067777");
        organizationDao.createOrganization(organization);
        
        SuperPerson superPerson2 = new SuperPerson();
        superPerson2.setName("Saddam Hussein");
        superPerson2.setDescription("Super hero");
        superPerson2.setIsGood(false);
        superPersonDao.createSuperPerson(superPerson2);
        
        Address address2 = new Address();
        address2.setCity("Cleveland");
        address2.setState("OH");
        address2.setStreet("123 Hell Road");
        address2.setZipcode("42069");
        addressDao.createAddress(address2);
        
        Location location2 = new Location();
        location2.setName("Iraq");
        location2.setDescription("A utopian city");
        location2.setAddress(address2);
        location2.setLatitude("100");
        location2.setLongitude("100");
        locationDao.createLocation(location2);
        
        Organization organization2 = new Organization();
        organization2.setName("The Guys");
        organization2.setDescription("A bunch of real ones");
        organization2.setIsGood(false);
        organization2.setLocation(location2);
        organization2.setPhone("3309067777");
        organizationDao.createOrganization(organization2);
        


        
        spo1.setSuperPerson(superPerson);
        spo1.setOrganization(organization);        
        spo1 = dao.createSuperPersonOrganization(spo1);

        
        spo2.setSuperPerson(superPerson2);
        spo2.setOrganization(organization2);        
        spo2 = dao.createSuperPersonOrganization(spo2); 
        
        List<SuperPersonOrganization> superPersonOrganizations = dao.getAllSuperPersonOrganizations(0, 10);
        
        String result1 = compare.compareObjects(spo1, superPersonOrganizations.get(0));
        String result2 = compare.compareObjects(spo1, superPersonOrganizations.get(1));
        String result3 = compare.compareObjects(spo2, superPersonOrganizations.get(0));
        String result4 = compare.compareObjects(spo2, superPersonOrganizations.get(1));
        
        
        assertEquals(2, superPersonOrganizations.size());
        assertTrue(result1.equals("") || result3.equals(""));
        assertTrue(result2.equals("") || result4.equals(""));
    }
}