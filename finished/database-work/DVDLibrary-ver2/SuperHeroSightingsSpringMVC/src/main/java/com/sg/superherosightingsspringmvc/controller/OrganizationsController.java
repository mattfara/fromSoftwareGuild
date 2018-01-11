/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.controller.commandmodel.CreateOrganizationCommandModel;
import com.sg.superherosightingsspringmvc.controller.commandmodel.UpdateOrganizationCommandModel;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.service.AddressService;
import com.sg.superherosightingsspringmvc.service.LocationService;
import com.sg.superherosightingsspringmvc.service.OrganizationService;
import com.sg.superherosightingsspringmvc.service.SuperPersonService;
import com.sg.superherosightingsspringmvc.viewmodel.OrganizationViewModel;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author matt
 */
@Controller
public class OrganizationsController {

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;

    @Inject
    AddressService addressService;

    @Inject
    SuperPersonService superPersonService;

    public OrganizationsController() {
    }

    @RequestMapping(value = "/organization/organizations", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model, RedirectAttributes redirectAttrs) {

        List<OrganizationViewModel> ovmList = organizationService.getOrganizationsViewModels(0, 10);

        if (ovmList.size() != 0) {
            Integer organizationClicked = ovmList.get(0).getOrganization().getOrganizationId();
            redirectAttrs.addAttribute("organizationClicked", organizationClicked);
            return "redirect:/organization/chooseOrganization?organizationClicked={organizationClicked}";
        }

        model.addAttribute("ovmList", ovmList);

        return "/organization/organizations";
    }

    @RequestMapping(value = "/organization/chooseOrganization", method = RequestMethod.GET)
    public String displayOrganizationsPageWithSelectedOrganization(Model model, HttpServletRequest request, @RequestParam Integer organizationClicked) {
        //thinking getOrganizationViewModelByOrganizationId -- new method for organizationService
        OrganizationViewModel ovm = organizationService.getOrganizationViewModelByOrganizationId(organizationClicked);
        model.addAttribute("ovm", ovm);

        List<OrganizationViewModel> ovmList = organizationService.getOrganizationsViewModels(0, 10);
        model.addAttribute("ovmList", ovmList);

        return "/organization/organizations";
    }

    private Model createOrganizationViewModelAndAddToModel(Model model) {
        OrganizationViewModel ovm = new OrganizationViewModel();
        ovm.setSuperPersons(superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE));
        ovm.setLocations(locationService.getAllLocations(0, Integer.MAX_VALUE));

        model.addAttribute("ovm", ovm);

        return model;
    }

    private String returnToPageWithErrorMessages(Model model, CreateOrganizationCommandModel cocm) {

        model = createOrganizationViewModelAndAddToModel(model);
        model.addAttribute(cocm);

        return "organization/create_organization";
    }

    @RequestMapping(value = "organization/displayCreateOrganizationPage", method = RequestMethod.POST)
    public String displayCreateOrganizationPage(Model model) {

        model = createOrganizationViewModelAndAddToModel(model);

        return "organization/create_organization";
    }

    @RequestMapping(value = "organization/createOrganization", method = RequestMethod.POST)
    public String createOrganization(@Valid @ModelAttribute("cocm") CreateOrganizationCommandModel cocm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return returnToPageWithErrorMessages(model, cocm);
        }

        Organization organizationToCreate = new Organization();

        Location organizationLocation = locationService.getLocationById(cocm.getLocationId());
        organizationToCreate.setLocation(organizationLocation);

        organizationToCreate.setPhone(cocm.getPhone());
        organizationToCreate.setName(cocm.getName());
        organizationToCreate.setDescription(cocm.getDescription());

        String reputation = cocm.getReputation();

        if (reputation == null) {
        } else if (reputation.equals("good")) {
            organizationToCreate.setIsGood(Boolean.TRUE);
        } else {
            organizationToCreate.setIsGood(Boolean.FALSE);
        }

        Organization createdOrganization = organizationService.createOrganization(organizationToCreate);
        for (Integer superPersonId : cocm.getSuperPersons()) {
            superPersonService.addSuperPersonToOrganization(superPersonId, createdOrganization.getOrganizationId());
        }
        return "redirect:/organization/organizations";
    }

    private Boolean getBooleanFromReputation(String reputation) {
        if (reputation != null) {
            return (reputation.equals("good"));
        }
        return null;
    }

    private String getReputationFromBoolean(Boolean isGood) {
        if (isGood != null) {
            return (isGood) ? "good" : "evil";
        }
        return null;
    }

    private Integer[] getListOfIntegersFromListOfSuperPersons(List<SuperPerson> spList) {
        Integer[] ids = new Integer[spList.size()];

        for (int i = 0; i < spList.size(); i++) {
            ids[i] = spList.get(i).getSuperPersonId();
        }

        return ids;
    }

    @RequestMapping(value = "organization/displayUpdateOrganizationPage", method = RequestMethod.GET)
    public String displayUpdateOrganizationPage(Model model, HttpServletRequest request, @RequestParam Integer organizationToUpdate) {

        OrganizationViewModel ovm = organizationService.getOrganizationViewModelByOrganizationId(organizationToUpdate);
        
        if (!model.containsAttribute("uocm")) {
            UpdateOrganizationCommandModel uocm = new UpdateOrganizationCommandModel();

            uocm.setDescription(ovm.getOrganization().getDescription());

            uocm.setReputation(getReputationFromBoolean(ovm.getOrganization().getIsGood()));
            uocm.setLocationId(ovm.getLocation().getLocationId());
            uocm.setName(ovm.getOrganization().getName());
            uocm.setOrganizationId(organizationToUpdate);
            uocm.setPhone(ovm.getOrganization().getPhone());

            Integer[] listOfSuperPersonId = getListOfIntegersFromListOfSuperPersons(ovm.getSuperPersons());
            uocm.setSuperPersons(listOfSuperPersonId);

            ovm.setSuperPersons(superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE));
            ovm.setLocations(locationService.getAllLocations(0, Integer.MAX_VALUE));
            
            model.addAttribute("uocm", uocm);
        }

        model.addAttribute("ovm", ovm);
        
        return "organization/update_organization";
    }

    private String returnToPageWithErrorMessages(Model model, UpdateOrganizationCommandModel uocm) {
        model = createOrganizationViewModelAndAddToModel(model);
        model.addAttribute("uocm", uocm);
        return "organization/update_organization";
    }
    
    @RequestMapping(value = "organization/updateOrganization", method = RequestMethod.POST)
    public String updateOrganization(@Valid @ModelAttribute("uocm") UpdateOrganizationCommandModel uocm,
            BindingResult result, Model model, @RequestParam Integer organizationId) {

        if (result.hasErrors()) {
            return returnToPageWithErrorMessages(model, uocm);
        }

        
        Organization orgToUpdate = organizationService.getOrganizationById(organizationId);

        OrganizationViewModel ovm = organizationService.getOrganizationViewModelByOrganizationId(organizationId);
        ovm.setLocation(locationService.getLocationById(uocm.getLocationId()));
        List<SuperPerson> originalSuperPersons = ovm.getSuperPersons();

        for (SuperPerson currentSp : originalSuperPersons) {
            superPersonService.deleteOrganizationFromSuperPerson(currentSp.getSuperPersonId(), organizationId);
        }

        Integer[] newSuperPersons = uocm.getSuperPersons();
        for (Integer currentId : newSuperPersons) {
            superPersonService.addSuperPersonToOrganization(currentId, organizationId);
        }

        Location organizationLocation = locationService.getLocationById(uocm.getLocationId());
        orgToUpdate.setLocation(organizationLocation);

        orgToUpdate.setPhone(uocm.getPhone());
        orgToUpdate.setName(uocm.getName());
        orgToUpdate.setDescription(uocm.getDescription());
        
        orgToUpdate.setIsGood(getBooleanFromReputation(uocm.getReputation()));

        Organization updatedOrganization = organizationService.updateOrganization(orgToUpdate);

        return "redirect:/organization/organizations";
    }

    @RequestMapping(value = "organization/redirectToOrganizationsPage", method = RequestMethod.GET)
    public String redirectToOrganizationsPage() {
        return "redirect:/organization/organizations";
    }

    //display the delete page
    @RequestMapping(value = "organization/delete_organization", method = RequestMethod.GET)
    public String displayDeleteOrganizationPage(Model model, HttpServletRequest request, @RequestParam Integer organizationToDelete) {
        model.addAttribute("organizationToDelete", organizationToDelete);
        return "organization/delete_organization";
    }

    //deleteOrganization
    @RequestMapping(value = "organization/deleteOrganization", method = RequestMethod.POST)
    public String deleteOrganization(@RequestParam(value = "organizationToDelete") String organizationToDelete, Model model) {
        organizationService.deleteOrganization(organizationService.getOrganizationById(Integer.parseInt(organizationToDelete)));
        return "redirect:/organization/organizations";
    }

}
