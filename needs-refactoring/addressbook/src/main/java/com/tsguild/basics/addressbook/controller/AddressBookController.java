/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.controller;

import com.tsguild.basics.addressbook.dao.AddressDaoJdbcTemplateImpl;
import com.tsguild.basics.addressbook.dao.PersonAddressDaoJdbcTemplateImpl;
import com.tsguild.basics.addressbook.dao.PersonDaoJdbcTemplateImpl;
import com.tsguild.basics.addressbook.dto.Address;
import com.tsguild.basics.addressbook.dto.Person;
import com.tsguild.basics.addressbook.ui.AddressBookView;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author matt
 */

/*
    Here we could use the Controller to call the 
*/



public class AddressBookController {
    AddressBookView view;
    PersonDaoJdbcTemplateImpl personService;
    AddressDaoJdbcTemplateImpl addressService;
    PersonAddressDaoJdbcTemplateImpl personAddressDao;
    
    
    public AddressBookController(AddressBookView view, PersonDaoJdbcTemplateImpl personService, 
            AddressDaoJdbcTemplateImpl addressService, PersonAddressDaoJdbcTemplateImpl personAddressDao){
        this.personService = personService;
        this.addressService = addressService;
        this.personAddressDao = personAddressDao;
    }
    
    
    public void run(){
        boolean keepRunning = true;
        int menuSelection = 0;
        while (keepRunning){
            menuSelection = getMenuSelection();
        
    
            switch(menuSelection){
                case 1: addAddress();
                        break;
                case 2: removeAddress();
                        break;
                case 3: findAddressesByLastName();
                        break;
                case 4: countEntries();
                        break;
                case 5: listAllAddresses();
                        break;
                case 6: addPerson();
                        break;
                case 7: removePerson();
                        break;
                case 8: findPeopleByAddress();
                        break;
                case 9: listAllPeople();
                        break;
                case 10: keepRunning=false;
                        break;
                default: unknownCommand();
                        break;
            }
        }
        displayExitMessage();
    }

    private int getMenuSelection(){
        return view.printMenuGetSelection();
    }
    
    //@RequestMapping(//some endpoint)
    public void associatePersonToAddress(){
        Person p = personService.getPersonById();
        Address a = addressService.getAddressById();
        personService.addPersonToAddress(p,a);
        
    }
    
    private void addAddress() {
        view.showNewAddressBanner();
        Address newAddress = view.getNewAddressInfo();
        addressService.createAddress(newAddress);
        view.showAddedSuccessfullyBanner();
    }

    private void removeAddress() {
        view.displayRemovingAddress();
        String lastName = view.getContactLastName();
        dao.removeAddress(lastName);
        view.displayRemovedSuccessfully();
    }

    private void findAddressesByLastName() {
        view.displayDisplayAddressBanner();
        String lastName = view.getContactLastName();
        Collection<Address> matches = addressService.findAddressByLastName(lastName);
        view.findAddressByLastName(matches);
    }

    private void countEntries() {
        view.displayCountingEntries();
        int addressCount = personAddressDao.countEntries();
        view.displayAddressCount(addressCount);
    }

    private void listAllAddresses() {
        view.displayDisplayAllBanner();
        List<Address> addressList = addressService.listAllAddresses();
        view.displayAddressList(addressList);
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandMessage();
    }
    
    private void displayExitMessage(){
        view.displayExitMessage();
    }

    private void addPerson() {
        view.displayAddPersonBanner();
        Person newPerson = view.getPersonInfo();
        personService.createPerson(newPerson);
    }

    private void removePerson() {
        view.displayRemovePersonBanner();
        int personId = view.askForPersonToRemove();
        Person personToDelete = personService.getPerson(personId);
        personService.delete(personToDelete);
        view.displayPersonRemovedSuccessfullyBanner();
    }

    private void findPeopleByAddress() {
        view.displayGetPeopleByAddressBanner();
        //identify the address the user wants to start with
        //get the address
        //put the address into the personService
        //list them out for user
        
        Address addressOfInterest = view.getAddressOfInterest();
        personService.getPeopleByAddress(addressOfInterest);
        view.displayListOfPeopleAtAddress(addressOfInterest);
    }

    private void listAllPeople() {
        view.displayListAllPeopleBanner();
        List<Person> allPeople = personService.listAllPeople();
        view.listAllPeople(allPeople);
    }
}
