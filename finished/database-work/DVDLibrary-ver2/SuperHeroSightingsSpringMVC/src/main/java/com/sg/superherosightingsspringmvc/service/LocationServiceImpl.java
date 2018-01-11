/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.controller.commandmodel.UpdateLocationCommandModel;
import com.sg.superherosightingsspringmvc.dao.LocationDao;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.viewmodel.LocationViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class LocationServiceImpl implements LocationService {

    LocationDao locationDao;
    AddressService addressService;
    SuperPersonService superPersonService;
    
    public LocationServiceImpl(LocationDao locationDao, AddressService addressService, 
            SuperPersonService superPersonService) {
        this.locationDao = locationDao;
        this.addressService = addressService;
        this.superPersonService = superPersonService;
    }

    @Override
    public Location createLocation(Location location) {
        return locationDao.createLocation(location);
    }

    @Override
    public Location getLocationById(Integer locationId) {
        return locationDao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations(int offset, int limit) {
        return locationDao.getAllLocations(offset, limit);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationDao.updateLocation(location);
    }

    @Override
    public Location deleteLocation(Location location) {
        return locationDao.deleteLocation(location);
    }

    @Override
    public List<Location> getAllLocationsBySuperPerson(SuperPerson superperson, int offset, int limit) {
        return locationDao.getAllLocationsBySuperPerson(superperson, offset, limit);
    }

    @Override
    public LocationViewModel getLocationViewModelByLocationId(Integer locationClicked) {
        LocationViewModel lvm = new LocationViewModel();
        Location location = getLocationById(locationClicked);
        
        lvm.setLocation(location);

        Integer addressId = location.getAddress().getAddressId();
        
        Address address = addressService.getAddressById(addressId);
        lvm.setAddress(address);
        
        List<SuperPerson> superPersons = superPersonService.getAllSuperPersonsBySightingLocation(location, 0, 10);
        
        lvm.setSuperPersons(superPersons);
        
        return lvm;
    }

    @Override
    public List<LocationViewModel> getLocationViewModels(int offset, int limit) {
        List<LocationViewModel> lvmList = new ArrayList();
        List<Location> viewLocations = getAllLocations(offset, limit);

        for (int i = 0; i < viewLocations.size(); i++) {
            LocationViewModel currentModel = new LocationViewModel();
            Location currentLocation = viewLocations.get(i);
            currentModel.setLocation(currentLocation);            
            lvmList.add(currentModel);
        }
        
        
        return lvmList;
    }

}
