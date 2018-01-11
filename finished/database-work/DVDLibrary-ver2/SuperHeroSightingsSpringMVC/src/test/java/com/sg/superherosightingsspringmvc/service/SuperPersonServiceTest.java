/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.controller.commandmodel.CreateSuperPersonCommandModel;
import com.sg.superherosightingsspringmvc.dao.SuperPersonOrganizationDao;
import com.sg.superherosightingsspringmvc.dao.SuperPersonPowerDao;
import com.sg.superherosightingsspringmvc.dao.SuperPersonSightingDao;
import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.CreateAndAddObjectsForServiceTests;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonOrganization;
import com.sg.superherosightingsspringmvc.model.SuperPersonPower;
import com.sg.superherosightingsspringmvc.model.SuperPersonSighting;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matt
 */
public class SuperPersonServiceTest {

    CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();
    CreateAndAddObjectsForServiceTests co = new CreateAndAddObjectsForServiceTests();

    private static SuperPersonService superPersonService;

    private static AddressService addressService;
    private static OrganizationService organizationService;
    private static PowerService powerService;
    private static SightingService sightingService;
    private static LocationService locationService;
    private static SuperPersonOrganizationDao superPersonOrganizationDao;
    private static SuperPersonPowerDao superPersonPowerDao;
    private static SuperPersonSightingDao superPersonSightingDao;

    public SuperPersonServiceTest() {
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        superPersonService = ctx.getBean("superPersonService", SuperPersonService.class);
        addressService = ctx.getBean("addressService", AddressService.class);
        organizationService = ctx.getBean("organizationService", OrganizationService.class);
        powerService = ctx.getBean("powerService", PowerService.class);
        locationService = ctx.getBean("locationService", LocationService.class);
        sightingService = ctx.getBean("sightingService", SightingService.class);
        superPersonOrganizationDao = ctx.getBean("superPersonOrganizationDao", SuperPersonOrganizationDao.class);
        superPersonPowerDao = ctx.getBean("superPersonPowerDao", SuperPersonPowerDao.class);
        superPersonSightingDao = ctx.getBean("superPersonSightingDao", SuperPersonSightingDao.class);
        tdh.clearTables();

    }

    @After
    public void tearDown() {
        tdh.clearTables();

    }

    @Test
    public void testAddGetDeleteSuperPerson() {
        //Arrange & Act
        SuperPerson sp = co.createAndAddSuperPerson();
        SuperPerson fromDao = superPersonService.getSuperPersonById(sp.getSuperPersonId());

        //Assert
        String result = c.compareObjects(sp, fromDao);
        assertEquals("", result);

        //Act
        superPersonService.deleteSuperPerson(sp);
        //Assert
        assertNull(superPersonService.getSuperPersonById(sp.getSuperPersonId()));

    }

    /**
     * Test of getAllSuperPersons method, of class SuperPersonService.
     */
    @Test
    public void testGetAllSuperPersons() {
        //Arrange
        SuperPerson sp1 = co.createAndAddSuperPerson();
        SuperPerson sp2 = co.createAndAddSuperPerson();

        //Act
        List<SuperPerson> listOfSp = superPersonService.getAllSuperPersons(0, 10);
        assertEquals(2, listOfSp.size());

        //Assert
        String result1 = c.compareObjects(sp1, listOfSp.get(0));
        String result2 = c.compareObjects(sp2, listOfSp.get(0));
        String result3 = c.compareObjects(sp1, listOfSp.get(1));
        String result4 = c.compareObjects(sp2, listOfSp.get(1));

        assertTrue(result1.equals("") || result2.equals(""));
        assertTrue(result3.equals("") || result4.equals(""));

    }

    /**
     * Test of updateSuperPerson method, of class SuperPersonService.
     */
    @Test
    public void testUpdateSuperPerson() {
        //Arrange
        SuperPerson sp = co.createAndAddSuperPerson();
        sp.setName("Trumpus"); //altering the object outside of db
        sp.setDescription("builds walls");
        sp.setIsGood(false);

        // Act
        SuperPerson updated = superPersonService.updateSuperPerson(sp);
        String result = c.compareObjects(sp, updated);

        // Assert
        assertEquals(result, "");
    }

    /**
     * Test of deleteSuperPerson method, of class SuperPersonService.
     */
    /**
     * Test of getAllSuperPersonsBySighting method, of class SuperPersonService.
     */
    @Test
    public void getAllSuperPersonsBySighting() {

        //Arrange
        SuperPerson sp1 = co.createAndAddSuperPerson();
        SuperPerson sp2 = co.createAndAddSuperPerson();
        SuperPerson sp3 = co.createAndAddSuperPerson();

        Sighting s1 = co.createAndAddSighting();
        Sighting s2 = co.createAndAddSighting();

        SuperPersonSighting sps1 = new SuperPersonSighting();
        sps1.setSuperPerson(sp1);
        sps1.setSighting(s1);
        sps1 = superPersonSightingDao.createSuperPersonSighting(sps1);

        SuperPersonSighting sps2 = new SuperPersonSighting();
        sps2.setSuperPerson(sp2);
        sps2.setSighting(s1);
        sps2 = superPersonSightingDao.createSuperPersonSighting(sps2);

        SuperPersonSighting sps3 = new SuperPersonSighting();
        sps3.setSuperPerson(sp3);
        sps3.setSighting(s2);
        sps3 = superPersonSightingDao.createSuperPersonSighting(sps3);

        //Act
        List<SuperPerson> list1 = superPersonService.getAllSuperPersonsBySighting(s1, 0, 10);
        List<SuperPerson> list2 = superPersonService.getAllSuperPersonsBySighting(s2, 0, 10);

        //Assert
        assertEquals(2, list1.size());
        assertEquals(1, list2.size());

        for (SuperPerson currentSuperPerson : list1) {

            String result1 = c.compareObjects(sp1, currentSuperPerson);
            String result2 = c.compareObjects(sp2, currentSuperPerson);

            assertTrue(result1.equals("") ^ result2.equals(""));
        }

        String result3 = c.compareObjects(sp3, list2.get(0));
        assertEquals("", result3);

    }

    /**
     * Test of getAllSuperPersonsBySightingLocation method, of class
     * SuperPersonService.
     */
    @Test
    public void testGetAllSuperPersonsBySightingLocation() {

        //Arrange
        SuperPerson sp1 = co.createAndAddSuperPerson();
        SuperPerson sp2 = co.createAndAddSuperPerson();
        SuperPerson sp3 = co.createAndAddSuperPerson();

        Sighting s1 = co.createAndAddSighting();
        Sighting s2 = co.createAndAddSighting();

        superPersonService.addSuperPersonToSighting(sp1, s1);
        superPersonService.addSuperPersonToSighting(sp2, s1);
        superPersonService.addSuperPersonToSighting(sp3, s2);

        //Act
        List<SuperPerson> superPersonsBySightingLocation1 = superPersonService.
                getAllSuperPersonsBySightingLocation(s1.getLocation(), 0, 10);
        List<SuperPerson> superPersonsBySightingLocation2 = superPersonService.
                getAllSuperPersonsBySightingLocation(s2.getLocation(), 0, 10);

        //Assert
        assertEquals(2, superPersonsBySightingLocation1.size());
        assertEquals(1, superPersonsBySightingLocation2.size());

        for (SuperPerson currentSuperPerson : superPersonsBySightingLocation1) {

            String result1 = c.compareObjects(sp1, currentSuperPerson);
            String result2 = c.compareObjects(sp2, currentSuperPerson);

            assertTrue(result1.equals("") ^ result2.equals(""));
        }

        String result3 = c.compareObjects(sp3, superPersonsBySightingLocation2.get(0));
        assertEquals("", result3);

    }

    /**
     * Test of getAllSuperPersonsByOrganization method, of class
     * SuperPersonService.
     */
    @Test
    public void testGetAllSuperPersonsByOrganization() {
        //Arrange
        SuperPerson sp1 = co.createAndAddSuperPerson();
        SuperPerson sp2 = co.createAndAddSuperPerson();
        SuperPerson sp3 = co.createAndAddSuperPerson();

        Organization o1 = co.createAndAddOrganization();
        Organization o2 = co.createAndAddOrganization();

        superPersonService.addSuperPersonToOrganization(sp1, o1);
        superPersonService.addSuperPersonToOrganization(sp2, o1);
        superPersonService.addSuperPersonToOrganization(sp3, o2);

        //Act
        List<SuperPerson> listOfSP1 = superPersonService.getAllSuperPersonsByOrganization(o1, 0, 10);
        List<SuperPerson> listOfSP2 = superPersonService.getAllSuperPersonsByOrganization(o2, 0, 10);

        //Assert
        assertEquals(2, listOfSP1.size());
        assertEquals(1, listOfSP2.size());

        for (SuperPerson currentSuperPerson : listOfSP1) {

            String result1 = c.compareObjects(sp1, currentSuperPerson);
            String result2 = c.compareObjects(sp2, currentSuperPerson);

            assertTrue(result1.equals("") ^ result2.equals(""));
        }

        String result3 = c.compareObjects(sp3, listOfSP2.get(0));
        assertEquals("", result3);

    }

    /**
     * Test of addSuperPersonToPower method, of class SuperPersonService.
     */
    @Test
    public void testAddSuperPersonToPower_SuperPerson_Power() {
        //Arrange
        SuperPerson sp = co.createAndAddSuperPerson();
        Power pow = co.createAndAddPower();

        //Act
        SuperPersonPower spp = superPersonService.addSuperPersonToPower(sp, pow);
        SuperPersonPower fromDao = superPersonPowerDao.getSuperPersonPowerById(spp.getSuperPersonPowerId());

        //Assert
        String result = c.compareObjects(spp, fromDao);
        assertEquals("", result);

    }

    /**
     * Test of addSuperPersonToSighting method, of class SuperPersonService.
     */
    @Test
    public void testAddSuperPersonToSighting_SuperPerson_Sighting() {
        //Arrange
        SuperPerson sp = co.createAndAddSuperPerson();
        Sighting s = co.createAndAddSighting();

        //Act
        SuperPersonSighting sps = superPersonService.addSuperPersonToSighting(sp, s);

        SuperPersonSighting fromDao = superPersonSightingDao.
                getSuperPersonSightingById(sps.getSuperPersonSightingId());

        //Assert
        String result = c.compareObjects(sps, fromDao);
        assertEquals("", result);
    }

    @Test
    public void testAddSuperPersonToOrganization_SuperPerson_Organization() {
        //Arrange
        SuperPerson sp = co.createAndAddSuperPerson();
        Organization org = co.createAndAddOrganization();

        //Act
        SuperPersonOrganization spo = superPersonService.addSuperPersonToOrganization(sp, org);
        SuperPersonOrganization fromDao = superPersonOrganizationDao.getSuperPersonOrganizationById(spo.getSuperPersonOrganizationId());

        //Assert
        String result = c.compareObjects(spo, fromDao);
        assertEquals("", result);

    }

    @Test
    public void testDeleteSuperPersonFromPower_SuperPerson_Power() {

        SuperPerson sp = co.createAndAddSuperPerson();
        Power pow = co.createAndAddPower();

        SuperPersonPower spp = superPersonService.addSuperPersonToPower(sp, pow);
        Integer sppId = spp.getSuperPersonPowerId();
        superPersonService.deletePowerFromSuperPerson(sp, pow);

        assertNull(superPersonPowerDao.getSuperPersonPowerById(sppId));

    }

    @Test
    public void testDeleteSuperPersonFromOrganization_SuperPerson_Organization() {

        SuperPerson sp = co.createAndAddSuperPerson();
        Organization org = co.createAndAddOrganization();

        SuperPersonOrganization spo = superPersonService.addSuperPersonToOrganization(sp, org);
        Integer spoId = spo.getSuperPersonOrganizationId();
        superPersonService.deleteOrganizationFromSuperPerson(sp, org);

        assertNull(superPersonOrganizationDao.getSuperPersonOrganizationById(spoId));

    }

    /**
     * Test of addSuperPersonToOrganization method, of class SuperPersonService.
     */
    /**
     * Test of deleteSuperPersonFromOrganization method, of class
     * SuperPersonService.
     */
    /**
     * Test of deleteSuperPersonFromSighting method, of class
     * SuperPersonService.
     */
    @Test
    public void testDeleteSuperPersonFromSighting_SuperPerson_Sighting() {
        SuperPerson sp = co.createAndAddSuperPerson();
        Sighting sighting = co.createAndAddSighting();

        SuperPersonSighting sps = superPersonService.addSuperPersonToSighting(sp, sighting);
        Integer spsId = sps.getSuperPersonSightingId();
        superPersonService.deleteSightingFromSuperPerson(sp, sighting);

        assertNull(superPersonOrganizationDao.getSuperPersonOrganizationById(spsId));

    }

    @Test
    public void testBuildSuperPersonFromCommandModel() {
        //arrange
        CreateSuperPersonCommandModel cspcm = co.createAndReturnCommandModel();

        //act
        SuperPerson newSuperPerson = superPersonService.buildSuperPersonFromCommandModel(cspcm);

        //assert -- maybe put this into the helper method too -- easier to repeat later
        assertEquals(cspcm.getDescription(), newSuperPerson.getDescription());
        assertEquals(cspcm.getName(), newSuperPerson.getName());
        assertEquals(true, newSuperPerson.getIsGood());

    }

    @Test
    public void testAddSuperPersonToOrganizations() {
        //arrange
        SuperPerson testSP = co.createAndAddSuperPerson();
        Organization org1 = co.createAndAddOrganization();
        Organization org2 = co.createAndAddOrganization();
        Organization org3 = co.createAndAddOrganization();

        Integer[] orgs = new Integer[3];
        orgs[0] = org1.getOrganizationId();
        orgs[1] = org2.getOrganizationId();
        orgs[2] = org3.getOrganizationId();

        //act
        List<SuperPersonOrganization> spos = superPersonService.addSuperPersonToOrganizations(testSP.getSuperPersonId(), orgs);
        List<SuperPerson> spList1 = superPersonService.getAllSuperPersonsByOrganization(org1, 0, Integer.MAX_VALUE);
        List<SuperPerson> spList2 = superPersonService.getAllSuperPersonsByOrganization(org2, 0, Integer.MAX_VALUE);
        List<SuperPerson> spList3 = superPersonService.getAllSuperPersonsByOrganization(org3, 0, Integer.MAX_VALUE);
        SuperPerson fromDao1 = spList1.get(0);
        SuperPerson fromDao2 = spList2.get(0);
        SuperPerson fromDao3 = spList3.get(0);

        //assert -- maybe put this into the helper method too -- easier to repeat later
        String result1 = c.compareObjects(testSP, fromDao1);
        String result2 = c.compareObjects(testSP, fromDao2);
        String result3 = c.compareObjects(testSP, fromDao3);

        assertEquals("", result1);
        assertEquals("", result2);
        assertEquals("", result3);
    }

    @Test
    public void testAddSuperPersonToPowers() {
        SuperPerson testSP = co.createAndAddSuperPerson();
        Power power1 = co.createAndAddPower();
        Power power2 = co.createAndAddPower();
        Power power3 = co.createAndAddPower();

        Integer[] powers = new Integer[3];
        powers[0] = power1.getPowerId();
        powers[1] = power2.getPowerId();
        powers[2] = power3.getPowerId();

        //act
        List<SuperPersonPower> spos = superPersonService.addSuperPersonToPowers(testSP.getSuperPersonId(), powers);
        List<SuperPerson> spList1 = superPersonService.getAllSuperPersonsByPower(power1, 0, Integer.MAX_VALUE);
        List<SuperPerson> spList2 = superPersonService.getAllSuperPersonsByPower(power2, 0, Integer.MAX_VALUE);
        List<SuperPerson> spList3 = superPersonService.getAllSuperPersonsByPower(power3, 0, Integer.MAX_VALUE);
        SuperPerson fromDao1 = spList1.get(0);
        SuperPerson fromDao2 = spList2.get(0);
        SuperPerson fromDao3 = spList3.get(0);

        //assert -- maybe put this into the helper method too -- easier to repeat later
        String result1 = c.compareObjects(testSP, fromDao1);
        String result2 = c.compareObjects(testSP, fromDao2);
        String result3 = c.compareObjects(testSP, fromDao3);

        assertEquals("", result1);
        assertEquals("", result2);
        assertEquals("", result3);
    }

    @Test
    public void testGetAllSuperPersonsByPower() {
        //arrange
        SuperPerson newSP1 = co.createAndAddSuperPerson();
        SuperPerson newSP2 = co.createAndAddSuperPerson();
        Power newPower = co.createAndAddPower();
        superPersonService.addSuperPersonToPower(newSP1.getSuperPersonId(), newPower.getPowerId());
        superPersonService.addSuperPersonToPower(newSP2.getSuperPersonId(), newPower.getPowerId());

        //act
        List<SuperPerson> listOfSPs = superPersonService.getAllSuperPersonsByPower(newPower, 0, Integer.MAX_VALUE);
        //assert
        assertTrue(2 == listOfSPs.size());

        for (SuperPerson currentSuperPerson : listOfSPs) {

            String result1 = c.compareObjects(newSP1, currentSuperPerson);
            String result2 = c.compareObjects(newSP2, currentSuperPerson);

            assertTrue(result1.equals("") ^ result2.equals(""));
        }

    }

}
