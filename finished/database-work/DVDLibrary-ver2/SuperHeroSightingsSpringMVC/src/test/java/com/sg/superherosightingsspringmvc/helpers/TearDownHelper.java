/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.helpers;

import com.sg.superherosightingsspringmvc.dao.AddressDao;
import com.sg.superherosightingsspringmvc.dao.LocationDao;
import com.sg.superherosightingsspringmvc.dao.OrganizationDao;
import com.sg.superherosightingsspringmvc.dao.PowerDao;
import com.sg.superherosightingsspringmvc.dao.SightingDao;
import com.sg.superherosightingsspringmvc.dao.SuperPersonDao;
import com.sg.superherosightingsspringmvc.dao.SuperPersonOrganizationDao;
import com.sg.superherosightingsspringmvc.dao.SuperPersonPowerDao;
import com.sg.superherosightingsspringmvc.dao.SuperPersonSightingDao;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.model.SuperPersonOrganization;
import com.sg.superherosightingsspringmvc.model.SuperPersonPower;
import com.sg.superherosightingsspringmvc.model.SuperPersonSighting;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author matt
 */
public class TearDownHelper {
    //declarations
    private AddressDao addressDao;
    private LocationDao locationDao;
    private SuperPersonDao superPersonDao;
    private OrganizationDao organizationDao;
    private PowerDao powerDao;
    private SightingDao sightingDao;
    private SuperPersonOrganizationDao superPersonOrganizationDao;
    private SuperPersonPowerDao superPersonPowerDao;
    private SuperPersonSightingDao superPersonSightingDao;
    //constructor /bean setting
    public TearDownHelper() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        addressDao = ctx.getBean("addressDao", AddressDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        superPersonDao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        powerDao = ctx.getBean("powerDao", PowerDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        superPersonOrganizationDao = ctx.getBean("superPersonOrganizationDao", SuperPersonOrganizationDao.class);
        superPersonPowerDao = ctx.getBean("superPersonPowerDao", SuperPersonPowerDao.class);
        superPersonSightingDao = ctx.getBean("superPersonSightingDao", SuperPersonSightingDao.class);
    }
    public void clearTables() {
        List<SuperPersonPower> superPersonPowers = superPersonPowerDao.getAllSuperPersonPowers(0, Integer.MAX_VALUE);
        for (SuperPersonPower currentSuperPersonPower : superPersonPowers) {
            superPersonPowerDao.deleteSuperPersonPower(currentSuperPersonPower);
        }
                List<SuperPersonOrganization> superPersonOrganizations = superPersonOrganizationDao.getAllSuperPersonOrganizations(0, Integer.MAX_VALUE);
        for (SuperPersonOrganization currentSuperPersonOrganization : superPersonOrganizations) {
            superPersonOrganizationDao.deleteSuperPersonOrganization(currentSuperPersonOrganization);
        }
        
        List<SuperPersonSighting> superPersonSightings = superPersonSightingDao.getAllSuperPersonSightings(0, Integer.MAX_VALUE);
        for (SuperPersonSighting currentSuperPersonSighting : superPersonSightings) {
            superPersonSightingDao.deleteSuperPersonSighting(currentSuperPersonSighting);
        }
        List<Power> powers = powerDao.getAllPowers(0, Integer.MAX_VALUE);
        for (Power currentPower : powers) {
            powerDao.deletePower(currentPower);
        }
        List<SuperPerson> superPersons = superPersonDao.getAllSuperPersons(0, Integer.MAX_VALUE);
        for (SuperPerson currentSuperPerson : superPersons) {
            superPersonDao.deleteSuperPerson(currentSuperPerson);
        }
        List<Sighting> sightings = sightingDao.getAllSightings(0, Integer.MAX_VALUE);
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting);
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations(0, Integer.MAX_VALUE);
        for (Organization currentOrganization : organizations) {
            organizationDao.deleteOrganization(currentOrganization);
        }        
        List<Location> locations = locationDao.getAllLocations(0, Integer.MAX_VALUE);
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation);
        }         
        List<Address> addresses = addressDao.getAllAddresses(0, Integer.MAX_VALUE);
        for (Address currentAddress : addresses) {
            addressDao.deleteAddress(currentAddress);
        }
             
    }
}