/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import java.util.List;

/**
 *
 * @author matt
 */
public interface PowerService {
    public Power createPower(Power power);
    public Power getPowerById(Integer powerId);
    public List<Power> getAllPowers(int offset, int limit);
    public Power updatePower(Power power);
    //public Power deletePower(Integer powerId);
    public Power deletePower(Power power);
    public List<Power> getAllPowersBySuperPerson(SuperPerson superPerson, int offset, int limit);
    
}
