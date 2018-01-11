/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import java.util.List;

/**
 *
 * @author matt
 */
public interface OrganizationDao {
    public Organization createOrganization(Organization organization);
    
    public Organization getOrganizationById(Integer organizationId);
    
    public List<Organization> getAllOrganizations(int offset, int limit);
    
    public Organization updateOrganization(Organization organization);
    
    public Organization deleteOrganization(Organization organization);
    
    public List<Organization> getAllOrganizationsBySuperPerson(SuperPerson superperson, int offset, int limit);
}
