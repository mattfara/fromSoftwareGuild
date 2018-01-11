/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.viewmodel;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import java.util.List;

/**
 *
 * @author matt
 */
public class SuperPersonViewModel {
    private SuperPerson superPerson;
    //private List<Location> locations;

    private List<Sighting> sightings;

    private List<Organization> organizations;

    private List<Power> powers;
    
    private List<Power> allKnownPowers;

    private List<Organization> allKnownOrganizations;

    public List<Organization> getAllKnownOrganizations() {
        return allKnownOrganizations;
    }

    public void setAllKnownOrganizations(List<Organization> allKnownOrganizations) {
        this.allKnownOrganizations = allKnownOrganizations;
    }
    
    
    
    public List<Power> getAllKnownPowers() {
        return allKnownPowers;
    }

    public void setAllKnownPowers(List<Power> allKnownPowers) {
        this.allKnownPowers = allKnownPowers;
    }
    
    
    
    
    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }
    
    
    public SuperPerson getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(SuperPerson superPerson) {
        this.superPerson = superPerson;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
    
    
    
}
