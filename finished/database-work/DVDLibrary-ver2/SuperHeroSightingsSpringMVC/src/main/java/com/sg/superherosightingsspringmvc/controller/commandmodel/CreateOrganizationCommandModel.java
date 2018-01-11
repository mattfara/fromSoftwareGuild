/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller.commandmodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author matt
 */
public class CreateOrganizationCommandModel {

    @NotEmpty(message = "You must supply a name.")
    @Length(max = 50, message = "Name must be no more than 50 characters in length.")
    public String name;

    @Length(max = 65585, message = "Character limit exceeded.")
    public String description;

    @NotEmpty(message = "You must supply a phone number.")
    @Length(min = 3, max = 15, message = "Phone number must be between three and 15 numbers.")
    public String phone;

    public String reputation;

    //not seen in corresponding model
    public Integer locationId;

    public int[] superPersons;
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public int[] getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(int[] superPersons) {
        this.superPersons = superPersons;
    }

}
