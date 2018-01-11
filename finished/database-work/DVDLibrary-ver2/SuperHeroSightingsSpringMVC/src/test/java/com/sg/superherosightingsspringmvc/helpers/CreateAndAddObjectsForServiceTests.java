/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.helpers;

import com.sg.superherosightingsspringmvc.controller.commandmodel.CreateSuperPersonCommandModel;
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
import com.sg.superherosightingsspringmvc.service.AddressService;
import com.sg.superherosightingsspringmvc.service.LocationService;
import com.sg.superherosightingsspringmvc.service.OrganizationService;
import com.sg.superherosightingsspringmvc.service.PowerService;
import com.sg.superherosightingsspringmvc.service.SightingService;
import com.sg.superherosightingsspringmvc.service.SuperPersonService;
import java.time.LocalDate;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author matt
 */
public class CreateAndAddObjectsForServiceTests {

    ApplicationContext ctx = ApplicationContextHelper.getContext();
    SuperPersonService superPersonService;
    LocationService locationService;
    OrganizationService organizationService;
    AddressService addressService;
    SightingService sightingService;
    PowerService powerService;

    SuperPersonOrganizationDao superPersonOrganizationDao;
    SuperPersonPowerDao superPersonPowerDao;
    SuperPersonSightingDao superPersonSightingDao;

    //constructor
    public CreateAndAddObjectsForServiceTests() {
        ApplicationContext ctx = ApplicationContextHelper.getContext();
        superPersonService = ctx.getBean("superPersonService", SuperPersonService.class);
        addressService = ctx.getBean("addressService", AddressService.class);
        locationService = ctx.getBean("locationService", LocationService.class);
        organizationService = ctx.getBean("organizationService", OrganizationService.class);
        sightingService = ctx.getBean("sightingService", SightingService.class);
        powerService = ctx.getBean("powerService", PowerService.class);

        superPersonOrganizationDao = ctx.getBean("superPersonOrganizationDao", SuperPersonOrganizationDao.class);
        superPersonPowerDao = ctx.getBean("superPersonPowerDao", SuperPersonPowerDao.class);
        superPersonSightingDao = ctx.getBean("superPersonSightingDao", SuperPersonSightingDao.class);
    }

    public SuperPerson createAndAddSuperPerson() {
        SuperPerson sp = new SuperPerson();
        sp.setName("Batman");
        sp.setDescription("some rich dude with gadgets");
        sp.setIsGood(true);
        sp = superPersonService.createSuperPerson(sp);
        return sp;
    }

    public Organization createAndAddOrganization() {
        Organization o = new Organization();
        o.setName("NAMBLA");
        o.setDescription("you don't want to know....");
        o.setIsGood(false);
        o.setPhone("5555555");
        Location l = createAndAddLocation();
        o.setLocation(l);
        o = organizationService.createOrganization(o);
        return o;
    }

    public Address createAndAddAddress() {
        Address a = new Address();
        a.setCity("Nutville");
        a.setState("UT");
        a.setStreet("123 Young Street");
        a.setZipcode("12345");
        return addressService.createAddress(a);
    }

    public Location createAndAddLocation() {
        Location l = new Location();
        Address a = createAndAddAddress();
        l.setAddress(a);
        l.setDescription("The wrong part of town");
        l.setLatitude("24 24");
        l.setLongitude("25 25");
        l.setName("Slime Factory");
        return locationService.createLocation(l);
    }

    public Sighting createAndAddSighting() {
        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("1992-01-01"));
        Location l = createAndAddLocation();
        s.setLocation(l);
        s.setDescription("Penguins");
        return sightingService.createSighting(s);
    }

    public Power createAndAddPower() {
        Power p = new Power();
        p.setName("Super Sneezing");
        return powerService.createPower(p);
    }

    public SuperPersonOrganization createAndAddSpo() {
        SuperPerson sp = createAndAddSuperPerson();
        Organization o = createAndAddOrganization();
        SuperPersonOrganization spo = new SuperPersonOrganization();
        spo.setSuperPerson(sp);
        spo.setOrganization(o);
        return superPersonOrganizationDao.createSuperPersonOrganization(spo);
    }

    public SuperPersonPower createAndAddSpp() {
        SuperPerson sp = createAndAddSuperPerson();
        Power p = createAndAddPower();
        SuperPersonPower spp = new SuperPersonPower();
        spp.setSuperPerson(sp);
        spp.setPower(p);
        return superPersonPowerDao.createSuperPersonPower(spp);
    }

    public SuperPersonSighting createAndAddSps() {
        SuperPerson sp = createAndAddSuperPerson();
        Sighting s = createAndAddSighting();
        SuperPersonSighting sps = new SuperPersonSighting();
        sps.setSuperPerson(sp);
        sps.setSighting(s);
        return superPersonSightingDao.createSuperPersonSighting(sps);
    }

    public CreateSuperPersonCommandModel createAndReturnCommandModel() {
        CreateSuperPersonCommandModel cspcm = new CreateSuperPersonCommandModel();
        cspcm.setName("Fred Flinstone");
        cspcm.setDescription("A nice guy");
        cspcm.setReputation("good");
        
        Integer[] orgs = new Integer[3];
        orgs[0] = 1;
        orgs[1] = 2;
        orgs[2] = 3;

        Integer[] powers = new Integer[3];
        powers[0] = 1;
        powers[1] = 2;
        powers[2] = 3;

        cspcm.setOrganizations(orgs);
        cspcm.setPowers(powers);
        
        return cspcm;
    }

}
