/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.serverinventory.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import newpackage.dto.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt
 */
public class ServerDaoTest {
    //ServerDao dao = new ServerDaoInMemImpl();
    public ServerDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
//    @Before
//    public void setUp() {
////        //usually we would be referencing the file here
////        List<Server> serverList = dao.getAllServers();
////        for (Server currentServer : serverList){
////            dao.removeServer(currentServer.getManufacturer());
//        }
//    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addServer method, of class ServerDao.
     */
    @Test
    public void testAddServer() {
    }

    /**
     * Test of getServer method, of class ServerDao.
     */
    @Test
    public void testGetServerByManufacturer() {
       
        //ARRANGE
        ServerDao dao = new ServerDaoInMemImpl();
        
        Server s1 = new Server();
        s1.setName("web01");
        s1.setIp("192.168.1.1");
        s1.setManufacturer("Dell");
        s1.setRam(8);
        s1.setNumProcessors(9);
        s1.setPurchaseDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
                
        dao.addServer(s1);
        
        Server s2 = new Server();
        s2.setName("db01");
        s2.setIp("192.168.3.45");
        s2.setManufacturer("HP");
        s2.setRam(16);
        s2.setNumProcessors(24);
        s2.setPurchaseDate(LocalDate.parse("2013-01-01", DateTimeFormatter.ISO_DATE));
                
        dao.addServer(s2);
        
        Server s3 = new Server();
        s3.setName("hr124");
        s3.setIp("192.168.32.111");
        s3.setManufacturer("IBM");
        s3.setRam(16);
        s3.setNumProcessors(12);
        s3.setPurchaseDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
                
        dao.addServer(s3);
        
        //ACT
        
        List<Server> results = dao.getServersByManufacturer("Dell");
        
        //ASSERT
        //we had one Dell in here
        //we need to make sure we have the right things inside
        //need both
        
        assertTrue(results.size() == 1);
        
        for (Server s : results){
            assertTrue(s.getManufacturer().equalsIgnoreCase("Dell"));
            //or assertEquals("Dell", s.getManufacturer());
        }
        
        
    }

    /**
     * Test of removeServer method, of class ServerDao.
     */
    @Test
    public void testRemoveServer() {
    }

    /**
     * Test of getAllServers method, of class ServerDao.
     */
    @Test
    public void testGetAllServers() {
    }

    /**
     * Test of getAllServersGroupByManufacturer method, of class ServerDao.
     */
    @Test
    public void testGetAllServersGroupByManufacturer() {
    }

    /**
     * Test of getServersByManufacturer method, of class ServerDao.
     */
    @Test
    public void testGetServersByManufacturer() {
    }

    /**
     * Test of getServersOlderThan method, of class ServerDao.
     */
    @Test
    public void testGetServersOlderThan() {
    }

    /**
     * Test of getServersOlderThanGroupByManufacturer method, of class ServerDao.
     */
    @Test
    public void testGetServersOlderThanGroupByManufacturer() {
    }

    /**
     * Test of getAverageServerAge method, of class ServerDao.
     */
    @Test
    public void testGetAverageServerAge() {
    }

    public class ServerDaoImpl implements ServerDao {

        public void addServer(Server server) {
        }

        public Server getServer(String name) {
            return null;
        }

        public void removeServer(String name) {
        }

        public List<Server> getAllServers() {
            return null;
        }

        public Map<String, List<Server>> getAllServersGroupByManufacturer() {
            return null;
        }

        public List<Server> getServersByManufacturer(String manufacturer) {
            return null;
        }

        public List<Server> getServersOlderThan(int ageInYears) {
            return null;
        }

        public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int AgeInYears) {
            return null;
        }

        public double getAverageServerAge() {
            return 0.0;
        }
    }
    
}
