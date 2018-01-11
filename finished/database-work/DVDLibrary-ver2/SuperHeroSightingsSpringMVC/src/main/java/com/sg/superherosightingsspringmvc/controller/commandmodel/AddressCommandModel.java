/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller.commandmodel;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author matt
 */
public class AddressCommandModel {

    private Integer addressId;

    @NotEmpty(message = "You must supply a street.") 
    @Length(max = 50, message = "Street must be no more than 50 characters in length.")
    private String street;

    @NotEmpty(message = "You must supply a city.") 
    @Length(max = 50, message = "City must be no more than 50 characters in length.")
    private String city;

    @NotEmpty(message = "You must supply a state.") 
    @Length(min=2, max = 2, message = "State must be two characters in length.")
    private String state;

    @NotEmpty(message = "You must supply a zipcode.") 
    @Length(min = 5, max = 10, message = " must be no more than 10 characters in length.")
    private String zipcode;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    

}
    
