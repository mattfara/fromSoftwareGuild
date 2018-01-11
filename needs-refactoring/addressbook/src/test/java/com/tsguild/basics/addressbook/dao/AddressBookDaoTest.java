/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.dao;

import com.tsguild.basics.addressbook.dto.Address;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matt
 */
public class AddressBookDaoTest {
    
    AddressBookDao dao = new AddressBookDaoFileImpl();
    
    
    public AddressBookDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Address> addressList = dao.listAllAddresses();
        for (Address currentAddress:addressList){
            dao.removeAddress(currentAddress.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressBookDao.
     */
    @Test
    public void testAddGetAddress() {
        Address address = new Address("1");
        address.setFirstName("Fred");
        address.setLastName("Hampton");
        address.setStreetAddress("225 Brick Way");
        address.setCity("Boston");
        address.setState("MA");
        address.setZIPCode("53351");
        
        dao.addAddress(address.getId(), address);
        
        Address fromDao = dao.findAddressById(address.getId());
        
        assertEquals(address, fromDao);
        
        
        
    }

    /**
     * Test of removeAddres method, of class AddressBookDao.
     */
    @Test
    public void testRemoveAddres() {
        Address address1 = new Address("1");
        address1.setFirstName("Frank");
        address1.setLastName("Booth");
        address1.setStreetAddress("914 Kunt street");
        address1.setCity("Akron");
        address1.setState("OH");
        address1.setZIPCode("44311");
        dao.addAddress(address1.getId(), address1);
        
        Address address2 = new Address("2");
        address1.setFirstName("Dave");
        address1.setLastName("Pint");
        address1.setStreetAddress("914 Bunt street");
        address1.setCity("Akron");
        address1.setState("OH");
        address1.setZIPCode("44311");
        dao.addAddress(address2.getId(), address2);
        
        dao.removeAddress(address1.getId());
        assertEquals(1, dao.countAddresses());
        
        dao.removeAddress(address2.getId());
        assertEquals(0, dao.countAddresses());
        
    }

    /**
     * Test of findAddressByLastName method, of class AddressBookDao.
     */
    @Test
    public void testFindAddressByLastName() {
        Address address1 = new Address("1");
        address1.setFirstName("Frank");
        address1.setLastName("Booth");
        address1.setStreetAddress("914 Runt street");
        address1.setCity("Akron");
        address1.setState("OH");
        address1.setZIPCode("44311");
        dao.addAddress(address1.getId(), address1);
        
        Address address2 = new Address("2");
        address2.setFirstName("Dave");
        address2.setLastName("Booth");
        address2.setStreetAddress("914 Bunt street");
        address2.setCity("Akron");
        address2.setState("OH");
        address2.setZIPCode("44311");
        dao.addAddress(address2.getId(), address2);
        
        int sizeOfList = dao.findAddressByLastName(address1.getLastName()).size();
        
        assertEquals(2, sizeOfList);
        
    }

    /**
     * Test of countAddresses method, of class AddressBookDao.
     */
    @Test
    public void testCountAddresses() {
        Address address1 = new Address("1");
        address1.setFirstName("Frank");
        address1.setLastName("Booth");
        address1.setStreetAddress("914 Runt street");
        address1.setCity("Akron");
        address1.setState("OH");
        address1.setZIPCode("44311");
        dao.addAddress(address1.getId(), address1);
        
        Address address2 = new Address("2");
        address2.setFirstName("Dave");
        address2.setLastName("Booth");
        address2.setStreetAddress("914 Bunt street");
        address2.setCity("Akron");
        address2.setState("OH");
        address2.setZIPCode("44311");
        dao.addAddress(address2.getId(), address2);
    
        int sizeOfList = dao.countAddresses();
        assertEquals(2, sizeOfList);
    }

    /**
     * Test of listAllAddresses method, of class AddressBookDao.
     */
    @Test
    public void testListAllAddresses() {
        //maybe cycle through each address in the book
        //see if each one matches the originals
        
        Address address1 = new Address("1");
        address1.setFirstName("Frank");
        address1.setLastName("Booth");
        address1.setStreetAddress("914 Runt street");
        address1.setCity("Akron");
        address1.setState("OH");
        address1.setZIPCode("44311");
        dao.addAddress(address1.getId(), address1);
        
        Address address2 = new Address("2");
        address2.setFirstName("Dave");
        address2.setLastName("Booth");
        address2.setStreetAddress("914 Bunt street");
        address2.setCity("Akron");
        address2.setState("OH");
        address2.setZIPCode("44311");
        dao.addAddress(address2.getId(), address2);
    
        List<Address> theseAddresses = new ArrayList();
        theseAddresses.add(address1);
        theseAddresses.add(address2);
        
        List<Address> fromDao = dao.listAllAddresses();
        
        for (int currentAddress=0; currentAddress<theseAddresses.size(); currentAddress++){
            Address methodAddress = theseAddresses.get(currentAddress);
            Address daoAddress = fromDao.get(currentAddress);
            
            assertEquals(methodAddress.getId(), daoAddress.getId());
            assertEquals(methodAddress.getLastName(), daoAddress.getLastName());
            assertEquals(methodAddress.getFirstName(), daoAddress.getFirstName());
            assertEquals(methodAddress.getStreetAddress(), daoAddress.getStreetAddress());
            assertEquals(methodAddress.getCity(), daoAddress.getCity());
            assertEquals(methodAddress.getState(), daoAddress.getState());
            assertEquals(methodAddress.getZIPCode(), daoAddress.getZIPCode());
        }
        
        
    }
}
