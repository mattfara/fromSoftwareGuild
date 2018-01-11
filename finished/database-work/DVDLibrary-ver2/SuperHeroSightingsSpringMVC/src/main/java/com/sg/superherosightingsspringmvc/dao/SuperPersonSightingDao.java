/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonSighting;
import java.util.List;

/**
 *
 * @author matt
 */
public interface SuperPersonSightingDao {
    public SuperPersonSighting createSuperPersonSighting(SuperPersonSighting superPersonSighting);
    public SuperPersonSighting getSuperPersonSightingById(Integer superPersonSightingId);
    public List<SuperPersonSighting> getAllSuperPersonSightings(int offset, int limit);
    //public SuperPersonSighting updateSuperPersonSighting(SuperPersonSighting superPersonSighting);
    public SuperPersonSighting deleteSuperPersonSighting(SuperPersonSighting superPersonSighting);
    public SuperPersonSighting getSuperPersonSightingBySuperPersonAndSighting(SuperPerson superPerson, Sighting sighting);
}
