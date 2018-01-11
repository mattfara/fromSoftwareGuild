/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.helpers.ApplicationContextHelper;
import com.sg.superherosightingsspringmvc.helpers.CompareObjects;
import com.sg.superherosightingsspringmvc.helpers.TearDownHelper;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
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
public class SuperPersonDaoTest {
    private static SuperPersonDao dao;
    CompareObjects c = new CompareObjects();
    private static TearDownHelper tdh = new TearDownHelper();
    
    public SuperPersonDaoTest() {
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
        dao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        tdh.clearTables();
    
    }
    @After
    public void tearDown() {
        tdh.clearTables();
    }
    @Test
    public void testAddGetDeleteSuperPerson() {
// Arrange - set up your givens
        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);
// Act - call the method you're testing
        sp = dao.createSuperPerson(sp);
        SuperPerson fromDb = dao.getSuperPersonById(sp.getSuperPersonId());
        String result = c.compareObjects(sp, fromDb);
// Assert - assert that the actual matches the expected
        assertEquals(result, "");
        dao.deleteSuperPerson(dao.getSuperPersonById(sp.getSuperPersonId()));
        assertNull(dao.getSuperPersonById(sp.getSuperPersonId()));
    }
    @Test
    public void updateSuperPerson() {
// Arrange 
        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);
        SuperPerson added = dao.createSuperPerson(sp);
        added.setName("Trumpus");
        added.setDescription("builds walls");
        added.setIsGood(false);
// Act
        SuperPerson updated = dao.updateSuperPerson(added);
        String result = c.compareObjects(added, updated);
// Assert
        assertEquals(result, "");
    }
    @Test
    public void getAllSuperPersones() {
// Arrange  
        SuperPerson sp1 = new SuperPerson();
        sp1.setName("Batman");
        sp1.setDescription("some rich dude with gadgets");
        sp1.setIsGood(true);
        
        SuperPerson sp2 = new SuperPerson();
        sp2.setName("Trumpus");
        sp2.setDescription("builds walls");
        sp2.setIsGood(false);
// next 2 rows commented out b/c we removed dummy data from DB        
// want number of SuperPersones in DB, then use as OFFSET in Act        
//        Integer numInDb = dao.getAllSuperPersones(0, Integer.MAX_VALUE).size();
        SuperPerson createdSP1 = dao.createSuperPerson(sp1);
        SuperPerson createdSP2 = dao.createSuperPerson(sp2);
// Act
        List<SuperPerson> superPersons = dao.getAllSuperPersons(0, 100);
// Assert
        assertEquals(2, superPersons.size());
        String result1 = c.compareObjects(createdSP1, superPersons.get(0));
        String result2 = c.compareObjects(createdSP2, superPersons.get(0));
        String result3 = c.compareObjects(createdSP1, superPersons.get(1));
        String result4 = c.compareObjects(createdSP2, superPersons.get(1));
        // don't know what order they'll be in
        assertTrue(result1.equals("") || result2.equals(""));
        assertTrue(result3.equals("") || result4.equals(""));
    }
// need to create create data for all tables involved in the query
// will call on DAOs that we haven't implemented yet, but unit tests shouldn't rely on others
// should we just test these from the service?    
    @Test
    public void getAllSuperPersonsBySighting() {
        
    }
}