/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matt
 */
@Ignore
public class ContactListDaoTest {
    private ContactListDao dao;
    
    public ContactListDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() { //before every test we create a dao
// ask Spring for our DAO
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        dao = ctx.getBean("contactListDao", ContactListDao.class);
        
        // remove all of the Contacts
        List<Contact> contacts = dao.getAllContacts();
        for (Contact currentContact : contacts) {
            dao.removeContact(currentContact.getContactId());
        }
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of addContact method, of class ContactListDao.
     */
    @Test
    public void testAddGetDeleteContact() { //this is NOT a unit test! this is an integration test for multiple methods together in a pattern
        //arrange - set up givens
        Contact expectedContact = new Contact();
        expectedContact.setFirstName("Steve");
        expectedContact.setLastName("Smith");
        expectedContact.setCompany("Div IQ");
        expectedContact.setEmail("steve@deviq.com");
        expectedContact.setPhone("3305551212");
        //act - call method you are testing
        dao.addContact(expectedContact);
        Contact actualContact = dao.getContactById(expectedContact.getContactId());
        
        //assert -- assert that the actual matches expected
        assertEquals(actualContact,expectedContact);
        
        dao.removeContact(expectedContact.getContactId());
        
        assertNull(dao.getContactById(expectedContact.getContactId()));
    }

    /**
     * Test of getAllContacts method, of class ContactListDao.
     */
    @Test
    public void testGetAllContacts() {
    }

    /**
     * Test of getContactById method, of class ContactListDao.
     */
    @Test
    public void testGetContactById() {
    }

    /**
     * Test of updateContact method, of class ContactListDao.
     */
    @Test
    public void testUpdateContact() {
    }

    /**
     * Test of removeContact method, of class ContactListDao.
     */
    @Test
    public void testRemoveContact() {
    }

    /**
     * Test of searchContacts method, of class ContactListDao.
     */
    @Test
    public void testSearchContacts() { // DIDN'T GET ALL THIS INFO
        Contact expectedContact1 = new Contact();
        expectedContact1.setFirstName("Steve");
        expectedContact1.setLastName("Smith");
        expectedContact1.setCompany("DEv IQ");
        expectedContact1.setEmail("steve@deviq.com");
        expectedContact1.setPhone("3305551212");
        
        Contact expectedContact2 = new Contact();
        expectedContact2.setFirstName("Ken");
        expectedContact2.setLastName("Smith");
        expectedContact2.setCompany("DEv IQ");
        expectedContact2.setEmail("ken@deviq.com");
        expectedContact2.setPhone("3304441313");
        
        Contact expectedContact3 = new Contact();
        expectedContact3.setFirstName("Steve");
        expectedContact3.setLastName("Smith");
        expectedContact3.setCompany("SQL Guy");
        expectedContact3.setEmail("steve@deviq.com");
        expectedContact3.setPhone("3305551212");
        
        Contact expectedContact4 = new Contact();
        expectedContact4.setFirstName("Sally");
        expectedContact4.setLastName("Smith");
        expectedContact4.setCompany("TSG");
        expectedContact4.setEmail("sally.smith@tsg.com");
        expectedContact4.setPhone("3304441313");
        
        dao.addContact(expectedContact1);
        dao.addContact(expectedContact2);
        dao.addContact(expectedContact3);
        dao.addContact(expectedContact4);
        
        //Test: Ken's at DEV IQ
        Map<SearchTerm, String> criteria = new HashMap(); //only searching using the terms we care about
        criteria.put(SearchTerm.LAST_NAME, "Smith");
        criteria.put(SearchTerm.COMPANY, "Dev IQ");
        List<Contact> smithsAtDevIQ = dao.searchContacts(criteria);
        
        assertEquals(2, smithsAtDevIQ.size());
        assertEquals(expectedContact1, smithsAtDevIQ.get(0));
        
        //Test Steve
        criteria.remove(SearchTerm.COMPANY); //get rid of the one you don't need
        criteria.put(SearchTerm.FIRST_NAME, "Steve");
        List<Contact> steveSmiths = dao.searchContacts(criteria);
        
        
    }
    
}
