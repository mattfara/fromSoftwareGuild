/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.controller.commandmodel.UpdateLocationCommandModel;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.viewmodel.LocationViewModel;
import java.util.List;

/**
 *
 * @author matt
 */
public interface LocationService {
    public Location createLocation(Location location);

    public Location getLocationById(Integer locationId);

    public List<Location> getAllLocations(int offset, int limit);

    public Location updateLocation(Location location);

    public Location deleteLocation(Location location);

    public List<Location> getAllLocationsBySuperPerson(SuperPerson superperson, int offset, int limit);
    
    public LocationViewModel getLocationViewModelByLocationId(Integer locationClicked);
    public List<LocationViewModel> getLocationViewModels(int offset, int limit);
    
    
    
}
