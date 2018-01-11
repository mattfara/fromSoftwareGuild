/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.model;

import java.util.Objects;

/**
 *
 * @author matt
 */
public class SuperPersonSighting {
    private Integer superPersonSightingId;
    private SuperPerson superPerson;
    private Sighting sighting;

    public Integer getSuperPersonSightingId() {
        return superPersonSightingId;
    }

    public void setSuperPersonSightingId(Integer superPersonSightingId) {
        this.superPersonSightingId = superPersonSightingId;
    }

    public SuperPerson getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(SuperPerson superPerson) {
        this.superPerson = superPerson;
    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.superPersonSightingId);
        hash = 53 * hash + Objects.hashCode(this.superPerson);
        hash = 53 * hash + Objects.hashCode(this.sighting);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuperPersonSighting other = (SuperPersonSighting) obj;
        if (!Objects.equals(this.superPersonSightingId, other.superPersonSightingId)) {
            return false;
        }
        if (!Objects.equals(this.superPerson, other.superPerson)) {
            return false;
        }
        if (!Objects.equals(this.sighting, other.sighting)) {
            return false;
        }
        return true;
    }
    
    
    
}
