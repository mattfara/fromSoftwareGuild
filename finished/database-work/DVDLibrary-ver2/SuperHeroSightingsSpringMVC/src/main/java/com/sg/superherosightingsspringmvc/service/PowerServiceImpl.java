/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.PowerDao;
import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import java.util.List;

/**
 *
 * @author matt
 */
public class PowerServiceImpl implements  PowerService{
 PowerDao powerDao;
    SuperPersonService superPersonService;
    
    public PowerServiceImpl(PowerDao powerDao, SuperPersonService superPersonService){
        this.powerDao = powerDao;
        this.superPersonService = superPersonService;
    }

    @Override
    public Power createPower(Power power) {
        return powerDao.createPower(power);
    }

    @Override
    public Power getPowerById(Integer powerId) {
        return powerDao.getPowerById(powerId);
    }

    @Override
    public List<Power> getAllPowers(int offset, int limit) {
        return powerDao.getAllPowers(offset, limit);
    }

    @Override
    public Power updatePower(Power power) {
        return powerDao.updatePower(power);
    }

    @Override
    public Power deletePower(Power power) {
        return powerDao.deletePower(power);
    }
    
    @Override
    public List<Power> getAllPowersBySuperPerson(SuperPerson superPerson, int offset, int limit){
        return powerDao.getAllPowersBySuperPerson(superPerson, 0, Integer.MAX_VALUE);
    }
    
}
