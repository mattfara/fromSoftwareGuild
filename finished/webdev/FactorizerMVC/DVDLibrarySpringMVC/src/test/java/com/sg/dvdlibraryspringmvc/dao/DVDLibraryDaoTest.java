/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.dto.DVD;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matt
 */
public class DVDLibraryDaoTest {
    
    private DVDLibraryDao dao;
    
    public DVDLibraryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DVDLibraryDao.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testAddDVD() {
    }

    /**
     * Test of updateDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testUpdateDVD() {
    }

    /**
     * Test of removeDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testRemoveDVD() {
    }

    /**
     * Test of getAllDVDs method, of class DVDLibraryDao.
     */
    @Test
    public void testGetAllDVDs() {
    }

    /**
     * Test of getDVDById method, of class DVDLibraryDao.
     */
    @Test
    public void testGetDVDById() {
    }

    /**
     * Test of searchDVDs method, of class DVDLibraryDao.
     */
    @Test
    public void testSearchDVDs() {
    }
    
}
