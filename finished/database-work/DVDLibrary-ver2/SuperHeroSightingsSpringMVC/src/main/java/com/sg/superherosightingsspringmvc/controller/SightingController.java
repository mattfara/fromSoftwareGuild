/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.controller.commandmodel.CreateSightingCommandModel;
import com.sg.superherosightingsspringmvc.controller.commandmodel.UpdateSightingCommandModel;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Sighting;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.service.LocationService;
import com.sg.superherosightingsspringmvc.service.SightingService;
import com.sg.superherosightingsspringmvc.service.SuperPersonService;
import com.sg.superherosightingsspringmvc.viewmodel.SightingViewModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingController {

    @Inject
    SightingService sightingService;
    //many more beans required....
    @Inject
    SuperPersonService superPersonService;
    @Inject
    LocationService locationService;

    public SightingController() {
    }

    // this is an arbitrary redirect based on the jsp ish
    @RequestMapping(value = "/sighting/sightings", method = RequestMethod.GET)
    public String displaySightingsPage(Model model, RedirectAttributes redirectAttrs) {
        List<SightingViewModel> svmList = sightingService.getSightingViewModels(0, 10);
        if (svmList.size() != 0) {
            Integer sightingClicked = svmList.get(0).getSighting().getSightingId();
            redirectAttrs.addAttribute("sightingClicked", sightingClicked);
            return "redirect:/sighting/chooseSighting?sightingClicked={sightingClicked}";
        }
        model.addAttribute("svmList", svmList);
        // this is directory/file
        return "/sighting/sightings";
    }

    @RequestMapping(value = "sighting/chooseSighting", method = RequestMethod.GET)
    public String displaySightingsPageWithSelectedSighting(Model model, HttpServletRequest request, @RequestParam Integer sightingClicked) {
        //thinking getSightingViewModelBySightingId -- new method for sightingService
        SightingViewModel svm = sightingService.getSightingViewModelBySightingId(sightingClicked);
        model.addAttribute("svm", svm);
        //when we redirect, how do we send new information to the model?
        List<SightingViewModel> svmList = sightingService.getSightingViewModels(0, 10);
        model.addAttribute("svmList", svmList);
        return "/sighting/sightings";
    }

    @RequestMapping(value = "sighting/delete_sighting", method = RequestMethod.GET)
    public String displayDeleteSightingPage(Model model, HttpServletRequest request, @RequestParam Integer sightingToDelete) {
        model.addAttribute("sightingToDelete", sightingToDelete);
        return "sighting/delete_sighting";
    }

    @RequestMapping(value = "sighting/deleteSighting", method = RequestMethod.POST)
    public String deleteSighting(@RequestParam(value = "sightingToDelete") String sightingToDelete, Model model) {
        sightingService.deleteSighting(sightingService.getSightingById(Integer.parseInt(sightingToDelete)));
        return "redirect:/sighting/sightings";
    }

    @RequestMapping(value = "sighting/redirectToSightingsPage", method = RequestMethod.POST)
    public String redirectToSightingsPage(Model model) {
        return "redirect:/sighting/sightings";
    }

    @RequestMapping(value = "sighting/displayCreateSightingPage", method = RequestMethod.POST)
    public String displayCreateSightingPage(Model model) {
        //sighting view model?
        model = createSightingViewModelAndAddToModel(model);

        return "sighting/create_sighting";
    }

    private Model createSightingViewModelAndAddToModel(Model model) {
        SightingViewModel svm = new SightingViewModel();
        svm.setLocations(locationService.getAllLocations(0, Integer.MAX_VALUE));
        svm.setSuperPersons(superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE));

        model.addAttribute("svm", svm);

        return model;
    }

    private String returnToPageWithErrorMessages(Model model, CreateSightingCommandModel cscm) {

        model = createSightingViewModelAndAddToModel(model);
        model.addAttribute(cscm);

        return "sighting/create_sighting";
    }

    @RequestMapping(value = "sighting/createSighting", method = RequestMethod.POST)
    public String createSighting(@Valid @ModelAttribute("cscm") CreateSightingCommandModel cscm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return returnToPageWithErrorMessages(model, cscm);
        }

        Sighting sightingToCreate = new Sighting();

        Location sightingLocation = locationService.getLocationById(cscm.getLocationId());
        sightingToCreate.setLocation(sightingLocation);

        sightingToCreate.setDate(LocalDate.parse(cscm.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sightingToCreate.setDescription(cscm.getDescription());
        Sighting createdSighting = sightingService.createSighting(sightingToCreate);
        for (Integer superPersonId : cscm.getSuperPersons()) {
            superPersonService.addSuperPersonToSighting(superPersonId, createdSighting.getSightingId());
        }
        return "redirect:/sighting/sightings";
    }

    private Integer[] getListOfIntegersFromListOfSuperPersons(List<SuperPerson> spList) {
        Integer[] ids = new Integer[spList.size()];

        for (int i = 0; i < spList.size(); i++) {
            ids[i] = spList.get(i).getSuperPersonId();
        }

        return ids;
    }

    @RequestMapping(value = "sighting/displayUpdateSightingPage", method = RequestMethod.GET)
    public String displayUpdateSightingPage(Model model, HttpServletRequest request, @RequestParam Integer sightingToUpdate) {
        
        SightingViewModel svm = sightingService.getSightingViewModelBySightingId(sightingToUpdate);

        if (!model.containsAttribute("uscm")) {
            UpdateSightingCommandModel uscm = new UpdateSightingCommandModel();

            String date = svm.getSighting().getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            uscm.setDate(date);
            uscm.setDescription(svm.getDescription());
            uscm.setLocationId(svm.getLocation().getLocationId());
            uscm.setSightingId(svm.getSighting().getSightingId());
            Integer[] spList = getListOfIntegersFromListOfSuperPersons(svm.getSuperPersons());

            uscm.setSuperPersons(spList);

            svm.setSuperPersons(superPersonService.getAllSuperPersons(0, Integer.MAX_VALUE));
            svm.setLocations(locationService.getAllLocations(0, Integer.MAX_VALUE));

            model.addAttribute("uscm", uscm);
        }

        model.addAttribute("svm", svm);

        return "sighting/update_sighting";
    }

    private String returnToPageWithErrorMessages(Model model, UpdateSightingCommandModel uscm) {
        model = createSightingViewModelAndAddToModel(model);
        model.addAttribute("uscm", uscm);
        return "sighting/update_sighting";
    }

    @RequestMapping(value = "sighting/updateSighting", method = RequestMethod.POST)
    public String updateSighting(@Valid @ModelAttribute("uscm") UpdateSightingCommandModel uscm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return returnToPageWithErrorMessages(model, uscm);
        }

        Integer idForUpdate = uscm.getSightingId();
        Sighting sightingToBeUpdated = sightingService.getSightingById(idForUpdate);
        SightingViewModel svm = sightingService.getSightingViewModelBySightingId(idForUpdate);
        List<SuperPerson> originalSuperPersons = svm.getSuperPersons();
        for (SuperPerson currentSp : originalSuperPersons) {
            superPersonService.deleteSightingFromSuperPerson(currentSp.getSuperPersonId(), idForUpdate);
        }
        Integer[] newSuperPersons = uscm.getSuperPersons();
        for (Integer currentId : newSuperPersons) {
            superPersonService.addSuperPersonToSighting(currentId, idForUpdate);
        }
        Location sightingLocation = locationService.getLocationById(uscm.getLocationId());
        sightingToBeUpdated.setLocation(sightingLocation);
        sightingToBeUpdated.setDescription(uscm.getDescription());
        sightingToBeUpdated.setDate(LocalDate.parse(uscm.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Sighting updatedSighting = sightingService.updateSighting(sightingToBeUpdated);
        return "redirect:/sighting/sightings";
    }

}
