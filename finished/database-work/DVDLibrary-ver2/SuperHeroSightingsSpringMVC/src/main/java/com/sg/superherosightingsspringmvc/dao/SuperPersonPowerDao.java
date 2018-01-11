/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonPower;
import java.util.List;

/**
 *
 * @author matt
 */
public interface SuperPersonPowerDao {
    public SuperPersonPower createSuperPersonPower(SuperPersonPower superPersonPower);
    
    public SuperPersonPower getSuperPersonPowerById(Integer superPersonPowerId);
    
    public List<SuperPersonPower> getAllSuperPersonPowers(int offset, int limit);
    
    //public SuperPersonPower updateSuperPersonPower(SuperPersonPower superPersonPower);
    
    public SuperPersonPower deleteSuperPersonPower(SuperPersonPower superPersonPower);
    
    public SuperPersonPower getSuperPersonPowerBySuperPersonAndPower(SuperPerson superPerson, Power power);
}
