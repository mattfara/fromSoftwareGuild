/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.SightingDao;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.viewmodel.SightingViewModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class SightingServiceImpl implements SightingService {

    SightingDao sightingDao;
    SuperPersonService superPersonService;
    LocationService locationService;

    public SightingServiceImpl(SightingDao sightingDao, SuperPersonService superPersonService, LocationService locationService) {
        this.sightingDao = sightingDao;
        this.superPersonService = superPersonService;
        this.locationService = locationService;

    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        return sightingDao.createSighting(sighting);
    }

    @Override
    public Sighting getSightingById(Integer sightingId) {
        return sightingDao.getSightingById(sightingId);
    }

    @Override
    public List<Sighting> getAllSightings(int offset, int limit) {
        return sightingDao.getAllSightings(offset, limit);
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        return sightingDao.updateSighting(sighting);
    }

    @Override
    public Sighting deleteSighting(Sighting sighting) {
        List<SuperPerson> superPersonsAtSighting = superPersonService.
                getAllSuperPersonsBySighting(sighting, 0, Integer.MAX_VALUE);
        for (SuperPerson currentSuperPerson : superPersonsAtSighting) {
            superPersonService.deleteSightingFromSuperPerson(currentSuperPerson, sighting);
        }
        return sightingDao.deleteSighting(sighting);
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date, int offset, int limit) {
        return sightingDao.getAllSightingsByDate(date, offset, limit);
    }

    @Override
    public List<Sighting> getAllSightingsBySuperPerson(SuperPerson sp, int offset, int limit) {
        return sightingDao.getAllSightingsBySuperPerson(sp, offset, limit);
    }

    @Override
    public List<SightingViewModel> getSightingViewModels(int offset, int limit) {
        List<SightingViewModel> svmList = new ArrayList();
        List<Sighting> viewSightings = getAllSightings(offset, limit);

        for (int i = 0; i < viewSightings.size(); i++) {
            // Make new Model object for each iteration
            SightingViewModel currentModel = new SightingViewModel();
            // Get the current sighting and set it on the model
            Sighting currentSighting = viewSightings.get(i);
            currentModel.setSighting(currentSighting);
            // Get the current LocationID
            Integer currentLocationId = currentSighting.getLocation().getLocationId();
            // Get the current Location and set it on the model
            Location currentLocation = locationService.getLocationById(currentLocationId);
            currentModel.setLocation(currentLocation);
            // Get a list of super persons at the sighting and set it on the model
            List<SuperPerson> superPersonsAtSighting = superPersonService.getAllSuperPersonsBySighting(
                    currentModel.getSighting(), 0, 10);
            currentModel.setSuperPersons(superPersonsAtSighting);
            // add the current model to the list of models
            svmList.add(currentModel);
        }
        // return the list of models
        return svmList;
    }

    @Override
    public SightingViewModel getSightingViewModelBySightingId(Integer sightingId) {
        SightingViewModel svm = new SightingViewModel();
        Sighting sighting = getSightingById(sightingId);

        svm.setSighting(sighting);
        Integer locationId = sighting.getLocation().getLocationId();
        Location location = locationService.getLocationById(locationId);
        svm.setLocation(location);
        List<SuperPerson> superPersonsAtSighting = superPersonService.getAllSuperPersonsBySighting(
                svm.getSighting(), 0, 10);
        svm.setSuperPersons(superPersonsAtSighting);
        svm.setDescription(sighting.getDescription());

        return svm;
    }

    @Override
    public List<Sighting> getAllSightingsByLocation(Location location, int offset, int limit) {
        return sightingDao.getAllSightingsByLocation(location, offset, limit);
    }

}
