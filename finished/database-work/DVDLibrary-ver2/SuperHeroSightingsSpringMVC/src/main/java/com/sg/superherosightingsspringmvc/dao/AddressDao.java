/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Address;
import java.util.List;

/**
 *
 * @author matt
 */
public interface AddressDao {
    public Address createAddress(Address address);
    
    public Address getAddressById(Integer addressId);
    
    public List<Address> getAllAddresses(int offset, int limit);
    
    public Address updateAddress(Address address);
    
    public Address deleteAddress(Address address);
}
