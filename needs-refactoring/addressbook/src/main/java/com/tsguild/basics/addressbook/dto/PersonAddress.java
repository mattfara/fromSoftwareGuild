/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.dto;

/**
 *
 * @author matt
 */
public class PersonAddress {
    private Integer personAddressId;
    private Person person;
    private Address address;

    public Integer getPersonAddressId() {
        return personAddressId;
    }

    public void setPersonAddressId(Integer personAddressId) {
        this.personAddressId = personAddressId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    
}
