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
public class SuperPersonOrganization {
    private Integer superPersonOrganizationId;
    private SuperPerson superPerson;
    private Organization organization;
    
    
    
    
    public Integer getSuperPersonOrganizationId() {
        return superPersonOrganizationId;
    }

    public void setSuperPersonOrganizationId(Integer superPersonOrganizationId) {
        this.superPersonOrganizationId = superPersonOrganizationId;
    }

    public SuperPerson getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(SuperPerson superPerson) {
        this.superPerson = superPerson;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.superPersonOrganizationId);
        hash = 23 * hash + Objects.hashCode(this.superPerson);
        hash = 23 * hash + Objects.hashCode(this.organization);
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
        final SuperPersonOrganization other = (SuperPersonOrganization) obj;
        if (!Objects.equals(this.superPersonOrganizationId, other.superPersonOrganizationId)) {
            return false;
        }
        if (!Objects.equals(this.superPerson, other.superPerson)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }
    
    
}
