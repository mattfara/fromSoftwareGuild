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
public class SuperPersonPower {
    private Integer superPersonPowerId;
    private SuperPerson superPerson;
    private Power power;

    public Integer getSuperPersonPowerId() {
        return superPersonPowerId;
    }

    public void setSuperPersonPowerId(Integer superPersonPowerId) {
        this.superPersonPowerId = superPersonPowerId;
    }

    public SuperPerson getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(SuperPerson superPerson) {
        this.superPerson = superPerson;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.superPersonPowerId);
        hash = 89 * hash + Objects.hashCode(this.superPerson);
        hash = 89 * hash + Objects.hashCode(this.power);
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
        final SuperPersonPower other = (SuperPersonPower) obj;
        if (!Objects.equals(this.superPersonPowerId, other.superPersonPowerId)) {
            return false;
        }
        if (!Objects.equals(this.superPerson, other.superPerson)) {
            return false;
        }
        if (!Objects.equals(this.power, other.power)) {
            return false;
        }
        return true;
    }
    
    
    
}
