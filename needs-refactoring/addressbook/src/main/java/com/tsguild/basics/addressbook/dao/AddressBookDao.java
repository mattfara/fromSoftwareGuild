/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.dao;

import com.tsguild.basics.addressbook.dto.Address;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author matt
 */
public interface AddressBookDao {
    Address addAddress(String id, Address address);
    Address removeAddress(String id);
    List<Address> findAddressByLastName(String lastName);
    Address findAddressById(String id);
    int countAddresses();
    List<Address> listAllAddresses();
}
