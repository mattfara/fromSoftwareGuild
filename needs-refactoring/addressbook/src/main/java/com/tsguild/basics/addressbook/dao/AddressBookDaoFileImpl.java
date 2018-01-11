/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.dao;

import com.tsguild.basics.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author matt
 */
public class AddressBookDaoFileImpl implements AddressBookDao {
    Map<String, Address> addresses = new HashMap();
    public static final String ADDRESS_FILE = "addresses.txt";
    public static final String DELIMITER = "::";
    
    
    @Override
    public Address addAddress(String id, Address address) {
        Address newAddress = addresses.put(id, address);
        return newAddress;
    }
    
    public List<Address> getAllAddresses(){
        return new ArrayList<Address>(addresses.values());
    }
    
    @Override
    public Address removeAddress(String id) {
        Address removedAddress = addresses.remove(id);
        return removedAddress;    
    }

    @Override
    public List<Address> findAddressByLastName(String lastName) {
        List<Address> addressesList = getAllAddresses();
        
        for (Address currentAddress:addressesList){
            if (!lastName.equals(currentAddress.getLastName())){
                addressesList.remove(currentAddress);
            }
        }
        return addressesList;
    }
    
    @Override
    public Address findAddressById(String id){
        return addresses.get(id);
    }
    
    @Override
    public int countAddresses() {
        return addresses.size();
    }

    @Override
    public List<Address> listAllAddresses() {
        return new ArrayList<Address>(addresses.values());
    }
    
    public void loadAddresses() throws AddressBookDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ADDRESS_FILE)));
        } catch(FileNotFoundException e){
            throw new AddressBookDaoException("Could not load roster data into memory", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Address currentAddress = new Address(currentTokens[0]);
            currentAddress.setFirstName(currentTokens[1]);
            currentAddress.setLastName(currentTokens[2]);
            currentAddress.setStreetAddress(currentTokens[3]);
            currentAddress.setCity(currentTokens[4]);
            currentAddress.setState(currentTokens[5]);
            currentAddress.setZIPCode(currentTokens[6]);
        }
        
    }
    
    public void writeAddresses() throws AddressBookDaoException{
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(ADDRESS_FILE));
        }catch(IOException e){
            throw new AddressBookDaoException(
	                "Could not save student data.", e);
        }
        
        List<Address> addressList = this.getAllAddresses();
        
        for (Address currentAddress:addressList){
            out.println(currentAddress.getId() + DELIMITER + currentAddress.getFirstName()+ DELIMITER + currentAddress.getLastName() + DELIMITER + currentAddress.getStreetAddress() + DELIMITER + currentAddress.getCity() + DELIMITER + currentAddress.getState() + DELIMITER + currentAddress.getZIPCode());
            out.flush();
        }
        out.close();
    }
    
}
