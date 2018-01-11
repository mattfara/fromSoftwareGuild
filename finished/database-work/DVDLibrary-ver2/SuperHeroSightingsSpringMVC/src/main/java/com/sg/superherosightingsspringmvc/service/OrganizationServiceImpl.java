/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.OrganizationDao;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.viewmodel.OrganizationViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class OrganizationServiceImpl implements OrganizationService {

    OrganizationDao organizationDao;
    SuperPersonService superPersonService;
    LocationService locationService;
    AddressService addressService;
    

    public OrganizationServiceImpl(OrganizationDao organizationDao, SuperPersonService superPersonService, LocationService locationService, AddressService addressService) {
        this.organizationDao = organizationDao;
        this.superPersonService = superPersonService;
        this.locationService = locationService;
        this.addressService = addressService;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(Integer organizationId) {
        return organizationDao.getOrganizationById(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations(int offset, int limit) {
        return organizationDao.getAllOrganizations(offset, limit);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return organizationDao.updateOrganization(organization);
    }

    @Override
    public Organization deleteOrganization(Organization organization) {
        List<SuperPerson> superPersonsAtOrganization = superPersonService.
                getAllSuperPersonsByOrganization(organization, 0, Integer.MAX_VALUE);
        for (SuperPerson currentSuperPerson : superPersonsAtOrganization) {
            superPersonService.deleteOrganizationFromSuperPerson(currentSuperPerson, organization);
        }
        return organizationDao.deleteOrganization(organization);
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperPerson(SuperPerson superperson, int offset, int limit) {
        return organizationDao.getAllOrganizationsBySuperPerson(superperson, offset, limit);
    }

    @Override
    public OrganizationViewModel getOrganizationViewModelByOrganizationId(Integer organizationClicked) {
        OrganizationViewModel ovm = new OrganizationViewModel();
        Organization organization = getOrganizationById(organizationClicked);
        
        Integer orgLocationId = organization.getLocation().getLocationId();
        
        Location orgLocation = locationService.getLocationById(orgLocationId);
        
        Integer orgAddressId = orgLocation.getAddress().getAddressId();
        Address orgAddress = addressService.getAddressById(orgAddressId);
        
        
        ovm.setAddress(orgAddress);
        ovm.setLocation(orgLocation);
        ovm.setOrganization(organization);

        List<SuperPerson> superPersons = superPersonService.getAllSuperPersonsByOrganization(organization, 0, 10);
        ovm.setSuperPersons(superPersons);
        
        return ovm;
    }

    @Override
    public List<OrganizationViewModel> getOrganizationsViewModels(int offset, int limit) {
        List<OrganizationViewModel> ovmList = new ArrayList();
        List<Organization> viewOrganizations = getAllOrganizations(offset, limit);

        for (int i = 0; i < viewOrganizations.size(); i++) {
            OrganizationViewModel currentModel = new OrganizationViewModel();

            Organization currentOrganization = viewOrganizations.get(i);

            currentModel.setOrganization(currentOrganization);

            ovmList.add(currentModel);
        }

        return ovmList;
    }

}
