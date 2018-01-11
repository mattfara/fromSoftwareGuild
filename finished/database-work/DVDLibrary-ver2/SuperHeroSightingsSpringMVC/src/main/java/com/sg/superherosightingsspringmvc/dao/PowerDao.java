/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import java.util.List;

/**
 *
 * @author matt
 */
public interface PowerDao {
    public Power createPower(Power power);
    
    public Power getPowerById(Integer powerId);
    
    public List<Power> getAllPowers(int offset, int limit);
    
    public Power updatePower(Power power);
    
    public Power deletePower(Power power);
    
    public List<Power> getAllPowersBySuperPerson(SuperPerson sp, int offset, int limit);
    
}
