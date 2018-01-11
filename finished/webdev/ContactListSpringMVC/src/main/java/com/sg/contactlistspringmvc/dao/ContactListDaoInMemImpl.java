/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author matt
 */
public class ContactListDaoInMemImpl implements ContactListDao {
    private Map<Long, Contact> contactMap = new HashMap();
    
    private static long contactIdCounter = 0;

    @Override
    public Contact addContact(Contact contact) {
        // set the new contact to current id
            contact.setContactId(contactIdCounter);
        // increment the counter
            contactIdCounter++;
        // add contact to map
            contactMap.put(contact.getContactId(), contact);
        //return contact
            return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        Collection<Contact> c = contactMap.values();
        return new ArrayList(c);
    }

    @Override
    public Contact getContactById(long contactId) {
        return contactMap.get(contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        contactMap.put(contact.getContactId(), contact);
    }

    @Override
    public void removeContact(long contactId) {
        contactMap.remove(contactId);
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {
        //get all search term values from map //using enums to make it easy to search by multiple fields 
            String firstNameSearchCriteria = criteria.get(SearchTerm.FIRST_NAME);
            String lastNameSearchCriteria = criteria.get(SearchTerm.LAST_NAME);
            String companySearchCriteria = criteria.get(SearchTerm.COMPANY);
            String emailSearchCriteria = criteria.get(SearchTerm.EMAIL);
            String phoneSearchCriteria = criteria.get(SearchTerm.PHONE);
        //build predicate condition for each of the criteria //find and replace (case sensitivity)
            Predicate<Contact> firstNameSearchPredicate;
            Predicate<Contact> lastNameSearchPredicate;
            Predicate<Contact> companySearchPredicate;
            Predicate<Contact> emailSearchPredicate;
            Predicate<Contact> phoneSearchPredicate;
            
        //define predicates
        //example is find all last name Smith at xyz corp
        //default empty value predicate
        Predicate<Contact> truePredicate = (c) -> {return true;}; //c is for contact
        
        //check each criterion - if empty or null, set to true predicate; otherwise, lambda expression
        // could make extra method for this?
        if (firstNameSearchCriteria ==null || firstNameSearchCriteria.isEmpty()){ //check for null pointer first!!!
            firstNameSearchPredicate = truePredicate;
        } else {
            firstNameSearchPredicate = (c) -> c.getFirstName().equalsIgnoreCase(firstNameSearchCriteria);
        }
        
        if (lastNameSearchCriteria ==null || lastNameSearchCriteria.isEmpty()){ //check for null pointer first!!!
            lastNameSearchPredicate = truePredicate;
        } else {
            lastNameSearchPredicate = (c) -> c.getLastName().equalsIgnoreCase(lastNameSearchCriteria);
        }
        
        if (companySearchCriteria ==null || companySearchCriteria.isEmpty()){ //check for null pointer first!!!
            companySearchPredicate = truePredicate;
        } else {
            companySearchPredicate = (c) -> c.getCompany().equalsIgnoreCase(companySearchCriteria);
        }
        
        if (emailSearchCriteria ==null || emailSearchCriteria.isEmpty()){ //check for null pointer first!!!
            emailSearchPredicate = truePredicate;
        } else {
            emailSearchPredicate = (c) -> c.getEmail().equalsIgnoreCase(emailSearchCriteria);
        }
        
        if (phoneSearchCriteria ==null || phoneSearchCriteria.isEmpty()){ //check for null pointer first!!!
            phoneSearchPredicate = truePredicate;
        } else {
            phoneSearchPredicate = (c) -> c.getPhone().equalsIgnoreCase(phoneSearchCriteria);
        }
        
        //filter map with predicates -- should return here
            return contactMap.values().stream()
                    .filter(firstNameSearchPredicate
                            .and(lastNameSearchPredicate)
                            .and(phoneSearchPredicate)
                            .and(emailSearchPredicate)
                            .and(companySearchPredicate))
                    .collect(Collectors.toList());
                    
    }
    //consider using this above
    private void setPredicate(Predicate<Contact> searchPredicate, String searchCriteria, Predicate<Contact> truePredicate){
        if (searchCriteria ==null || searchCriteria.isEmpty()){ //check for null pointer first!!!
            searchPredicate = truePredicate;
        } else {
            searchPredicate = (c) -> c.getPhone().equalsIgnoreCase(searchCriteria);
        }
    }
}
