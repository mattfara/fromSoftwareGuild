/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.ui;

import com.tsguild.basics.addressbook.dto.Address;
import com.tsguild.basics.addressbook.dto.Person;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author matt
 */
public class AddressBookView {
    UserIO io;

    public AddressBookView(UserIO io){
        this.io=io;
    }

    public int printMenuGetSelection() {
        int menuSelection;
        io.println("   Main Menu");
        io.println("================");
        io.println("1. Add an Address");
        io.println("2. Remove an Address");
        io.println("3. Find address by last name");
        io.println("4. Count of addresses");
        io.println("5. All Addresses");
        io.println("6. Exit");
        io.println("================");
        menuSelection = io.readInt("Please enter the number you'd like ", 1,6);
        return menuSelection;
    }
    
    public void showNewAddressBanner(){
        io.println("===Adding a New Address===");
    }
    
    public Address getNewAddressInfo(){
        
        String streetAddress = io.readString("What is the street address of new contact?");
        String city = io.readString("What is the city of new contact?");
        String state = io.readString("What is the state of new contact?");
        String ZIPCode = io.readString("What is the ZIP code of new contact?");
        
        Address currentAddress = new Address();
        
        currentAddress.setStreetAddress(streetAddress);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZIPCode(ZIPCode);
        
        return currentAddress;
    }
    
    public Person getNewPersonInfo(){
        
        String firstName = io.readString("What is this person's first name? ");
        String lastName = io.readString("What is this person's last name? ");
        String phone = io.readString("What is this person's phone? ");
        String email = io.readString("What is this person's email? ");
        
        Person person = new Person();
        
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phone);
        person.setEmail(email);
        
        return person;
    }
    
    public void showAddedSuccessfullyBanner(){
        io.println("Address added successfully");
    }
    
    public void displayAddressList (List<Address> addressList){
        for (Address currentAddress : addressList){
            io.println("=====================================================================");
            io.println(currentAddress.getLastName() + ", " + currentAddress.getFirstName());
            io.println("---------------------------------------------------------------------");
            io.println(currentAddress.getStreetAddress());
            io.println(currentAddress.getCity() + ", " + currentAddress.getState() + " " + currentAddress.getZIPCode());
            
        }
        io.readString("Please press Enter to continue");
    }
    
    public void displayDisplayAllBanner(){
        io.println("===Display All Addresses===");
    }
    
    public void displayDisplayAddressBanner () {
        io.println("=== Display Address ===");
    }

    public String getContactLastName() {
        return io.readString("Please enter the last name of your contact");
    }

    public void displayAddress(Address address) {
        if (address != null) {
            io.println(address.getLastName() + ", " + address.getFirstName());
            io.println("------------------------------------------------------");
            io.println(address.getStreetAddress());
            io.println(address.getCity() + ", " + address.getState() + " " + address.getZIPCode());
            io.print("");
        } else {
            io.print("No such address.");
        }
            io.readString("Please hit enter to continue.");
    }

    public void displayRemovingAddress(){
        io.println("===Removing an Address===");
    }
    
    public void displayRemovedSuccessfully(){
        io.println("Address removed successfully");
    }
    
    public void displayCountingAddresses(){
        io.println("===Displaying Address Count===");
    }
    
    public void displayAddressCount(int addressCount){
        io.println("You have " + addressCount + (addressCount==1 ? " address" : " addresses") + " in your book now.");
    }
    
    public void displayExitMessage(){
        io.print("Thanks for checking some addresses");
    }
    
    public void displayUnknownCommandMessage(){
        io.println("Unknown Command!");
    }
    

    
    public void findAddressByLastName(Collection<Address> lastNameMatches){
        int collectionSize = lastNameMatches.size();
        if (collectionSize<1){io.println("There are no matches to that last name");}
        else {
            io.println("Last name matches: ");
            int counter = 1;
            for (Address currentAddress : lastNameMatches){
                io.print(Integer.toString(counter));
                displayAddress(currentAddress);
                counter++;
            }
            
            
        }
        
    }

    public void displayAddPersonBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person getPersonInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayRemovePersonBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int askForPersonToRemove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayPersonRemovedSuccessfullyBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayGetPeopleByAddressBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Address getAddressOfInterest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayListOfPeopleAtAddress(Address addressOfInterest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayListAllPeopleBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void listAllPeople(List<Person> allPeople) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayCountingEntries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
