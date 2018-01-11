/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.viewmodel.SightingViewModel;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author matt
 */
public interface SightingService {
    //basic CRUD
    public Sighting createSighting(Sighting sighting);
    public Sighting getSightingById(Integer sightingId);
    public List<Sighting> getAllSightings(int offset, int limit);
    public Sighting updateSighting(Sighting sighting);
    //public Sighting deleteSighting(Integer sightingId);
    public Sighting deleteSighting(Sighting sighting);
    
    //demo
    public List<Sighting> getAllSightingsByDate(LocalDate date, int offset, int limit);
    public List<Sighting> getAllSightingsBySuperPerson(SuperPerson sp, int offset, int limit);
    
    public List<SightingViewModel> getSightingViewModels(int offset, int limit);
    public SightingViewModel getSightingViewModelBySightingId(Integer sightingId);
    public List<Sighting> getAllSightingsByLocation(Location location, int offset, int limit);
}
