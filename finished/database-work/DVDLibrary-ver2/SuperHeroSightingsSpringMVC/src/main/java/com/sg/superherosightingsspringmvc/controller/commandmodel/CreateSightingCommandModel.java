/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller.commandmodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author matt
 */
public class CreateSightingCommandModel {

    //@NotEmpty(message = "A date must be provided")
    private Integer locationId;

    @NotEmpty(message = "You must supply a date.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;

    @Length(max = 65585, message = "Character limit exceeded.")
    private String description;

    @NotEmpty(message = "Must select at least one super person for a sighting")
    private int[] superPersons;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getSuperPersons() {
        return superPersons;
    }

    public void setSuperPersons(int[] superPersons) {
        this.superPersons = superPersons;
    }

}
