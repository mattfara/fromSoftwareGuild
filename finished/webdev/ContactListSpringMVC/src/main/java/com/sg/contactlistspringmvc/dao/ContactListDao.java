/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public interface ContactListDao {
    //CRUD recipe
    
    //C is create -- add
        public Contact addContact(Contact contact);
    //R is read -- get all, get by
        public List<Contact> getAllContacts();
        public Contact getContactById(long contactId);
    //U is update -- edit
        public void updateContact(Contact contact);
    //D is delete -- remove
        public void removeContact(long contactId);
    //S - Search  
        public List<Contact> searchContacts(Map<SearchTerm, String> criteria); //takes in one of the SearchTerm constants
}
