/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.controller.commandmodel.CreateSuperPersonCommandModel;
import com.sg.superherosightingsspringmvc.controller.commandmodel.UpdateSuperPersonCommandModel;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Power;
import com.sg.superherosightingsspringmvc.model.SuperPerson;
import com.sg.superherosightingsspringmvc.service.OrganizationService;
import com.sg.superherosightingsspringmvc.service.PowerService;
import com.sg.superherosightingsspringmvc.service.SuperPersonService;
import com.sg.superherosightingsspringmvc.viewmodel.SuperPersonViewModel;
import java.util.List;
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

/*ideas for pagination
    //1. don't want to have to query the database every time we run a command
    //2. so do it once in constructor and save as static var
    //3. whenever a new superperson is added, increment
    //4. whenever an sp is deleted, decrement
    
    //5. the total is compared against a RequestParam representing
          //the highest

          offset+showing count
          if (offset+showingCount < total)
            //send flag to jsp that the next button is ok
            //otherwise send flag to disable next button
   //6. how do i get the offset?
 */
@Controller
public class SuperPersonsController {

//    private static Integer spCount;
    //pulled out @Inject annotations in favor of consttructor -- maybe this is why the method crashes?
    SuperPersonService superPersonService;
    OrganizationService organizationService;
    PowerService powerService;

    public SuperPersonsController(SuperPersonService superPersonService, OrganizationService organizationService, PowerService powerService) {
        this.superPersonService = superPersonService;
        this.organizationService = organizationService;
        this.powerService = powerService;

        //spCount = superPersonService.countSuperPersons();
    }

    @RequestMapping(value = "/superperson/superpersons", method = RequestMethod.GET)
    public String displaySuperPersonsPage(Model model, RedirectAttributes redirectAttrs) {
//        Integer currentOffset = 0;
//        
//        if (pageOffset == null){
//            currentOffset = 0;
//        }
//        else {
//            currentOffset = pageOffset;
//        }

        //model + 10 on page
        List<SuperPersonViewModel> spvmList = superPersonService.getSuperPersonViewModels(0, 10);

        if (spvmList.size() != 0) {
            Integer superPersonClicked = spvmList.get(0).getSuperPerson().getSuperPersonId();
            redirectAttrs.addAttribute("superPersonClicked", superPersonClicked);
            return "redirect:/superperson/chooseSuperPerson?superPersonClicked={superPersonClicked}";
        }

//        boolean limitReached = false;
//        if (currentOffset >= spCount){
//            limitReached = true;
//        }
        //model.addAttribute("incomingOffset", currentOffset);
        //model.addAttribute("limitReached", limitReached);
        model.addAttribute("spvmList", spvmList);
        return "/superperson/superpersons";
    }

//    @RequestMapping(value = "superperson/pageOver", method = RequestMethod.GET)
//    public void pageOver(Model model, RedirectAttributes redirectAttrs, @RequestParam Integer pageOffset){
//        displaySuperPersonsPage(model, redirectAttrs, pageOffset);
//    }
    @RequestMapping(value = "/superperson/chooseSuperPerson", method = RequestMethod.GET)
    public String displaySuperPersonsPageWithSelectedSuperPerson(Model model, HttpServletRequest request, @RequestParam Integer superPersonClicked) {
        //thinking getSuperPersonViewModelBySuperPersonId -- new method for superpersonService
        SuperPersonViewModel spvm = superPersonService.getSuperPersonViewModelBySuperPersonId(superPersonClicked);
        model.addAttribute("spvm", spvm);
        List<SuperPersonViewModel> spvmList = superPersonService.getSuperPersonViewModels(0, 10);
        model.addAttribute("spvmList", spvmList);

        return "/superperson/superpersons";
    }

    @RequestMapping(value = "superperson/delete_superperson", method = RequestMethod.GET)
    public String displayDeleteSuperPersonPage(Model model, @RequestParam Integer superPersonToDelete) {
        model.addAttribute("superPersonToDelete", superPersonToDelete);
        return "superperson/delete_superperson";
    }

    @RequestMapping(value = "superperson/deleteSuperPerson", method = RequestMethod.POST)
    public String deleteSuperPerson(@RequestParam(value = "superPersonToDelete") String superPersonToDelete, Model model) {
        superPersonService.deleteSuperPerson(superPersonService.getSuperPersonById(Integer.parseInt(superPersonToDelete)));
        //spCount--;
        return "redirect:/superperson/superpersons";
    }

    @RequestMapping(value = "superperson/redirectToSuperPersonsPage", method = RequestMethod.POST)
    public String redirectToSuperPersonsPage(Model model) {
        return "redirect:/superperson/superpersons";
    }

    @RequestMapping(value = "superperson/displayCreateSuperPersonPage", method = RequestMethod.POST)
    public String displayCreateSuperPersonPage(Model model) {
        model = createSuperPersonViewModelAndAddToModel(model);
        return "/create_superperson";
    }

    private Model createSuperPersonViewModelAndAddToModel(Model model) {
        SuperPersonViewModel spvm = new SuperPersonViewModel();
        spvm.setPowers(powerService.getAllPowers(0, Integer.MAX_VALUE));
        spvm.setOrganizations(organizationService.getAllOrganizations(0, Integer.MAX_VALUE));
        model.addAttribute("spvm", spvm);
        return model;
    }

    private String returnToPageWithErrorMessages(Model model, CreateSuperPersonCommandModel cspcm) {
        model = createSuperPersonViewModelAndAddToModel(model);
        model.addAttribute("cspcm", cspcm);
        return "superperson/create_superperson";
    }

    private String returnToPageWithErrorMessages(Model model, UpdateSuperPersonCommandModel uspcm) {
        model = createSuperPersonViewModelAndAddToModel(model);
        model.addAttribute("uspcm", uspcm);
        return "superperson/update_superperson";
    }

    private SuperPerson setAndSubmitSuperPerson(CreateSuperPersonCommandModel cspcm) {

        SuperPerson newSuperPerson = new SuperPerson();
//        newSuperPerson.setName(cspcm.getName());
//        newSuperPerson.setDescription(cspcm.getDescription());
//        String reputation = cspcm.getReputation();
//
//        if (reputation == null) {
//        } else if (reputation.equals("good")) {
//            newSuperPerson.setIsGood(true);
//        } else if (reputation.equals("evil")) {
//            newSuperPerson.setIsGood(false);
//        }
//
//        
//        int[] powers = cspcm.getPowers();
//        int[] orgs = cspcm.getOrganizations();
//        newSuperPerson = superPersonService.createSuperPerson(newSuperPerson);
//
//        for (Integer currentPowerId : powers) {
//            superPersonService.addSuperPersonToPower(newSuperPerson,
//                    powerService.getPowerById(currentPowerId));
//        }
//        
//        for (Integer currentOrgId : orgs) {
//            superPersonService.addSuperPersonToOrganization(newSuperPerson,
//                    organizationService.getOrganizationById(currentOrgId));
//        }

        return newSuperPerson;
    }

    @RequestMapping(value = "/superperson/createSuperPerson", method = RequestMethod.POST)
    public String createSuperPerson(@Valid @ModelAttribute("cspcm") CreateSuperPersonCommandModel cspcm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return returnToPageWithErrorMessages(model, cspcm);
        }

        //these are called separately in the controller to decouple them in the service
        SuperPerson newSuperPerson = superPersonService.buildSuperPersonFromCommandModel(cspcm); //for future reference this should be in the service to facilitate testing
        superPersonService.createSuperPerson(newSuperPerson);
        superPersonService.addSuperPersonToOrganizations(newSuperPerson.getSuperPersonId(), cspcm.getOrganizations());
        superPersonService.addSuperPersonToPowers(newSuperPerson.getSuperPersonId(), cspcm.getPowers());

        return "redirect:/superperson/superpersons";
    }

    private String getReputationFromBoolean(Boolean isGood) {
        if (isGood != null) {
            return (isGood) ? "good" : "evil";
        }
        return null;
    }
    
    private Boolean getBooleanFromReputation(String reputation){
        if (reputation != null){
            return (reputation.equals("good"));
        }
        return null;
    }

    private Integer[] getListOfIntegersFromListOfOrganizations(List<Organization> orgs) {
        Integer[] ids = new Integer[orgs.size()];

        for (int i = 0; i < orgs.size(); i++) {
            ids[i] = orgs.get(i).getOrganizationId();
        }

        return ids;
    }

    //name clash: X and Y have same erasure....
    private Integer[] getListOfIntegersFromListOfPowers(List<Power> powers) {
        Integer[] ids = new Integer[powers.size()];

        for (int i = 0; i < powers.size(); i++) {
            ids[i] = powers.get(i).getPowerId();
        }

        return ids;
    }

    @RequestMapping(value = "superperson/displayUpdateSuperPersonPage", method = RequestMethod.GET)
    public String displayUpdateSuperPersonPage(Model model, @RequestParam Integer superPersonToUpdate) {
        
        SuperPersonViewModel spvm = superPersonService.getSuperPersonViewModelBySuperPersonId(superPersonToUpdate);
        
        
        if (!model.containsAttribute("uspcm")) {
            UpdateSuperPersonCommandModel uspcm = new UpdateSuperPersonCommandModel();

            uspcm.setSuperPersonId(spvm.getSuperPerson().getSuperPersonId());
            uspcm.setName(spvm.getSuperPerson().getName());
            uspcm.setDescription(spvm.getSuperPerson().getDescription());
            uspcm.setReputation(getReputationFromBoolean(spvm.getSuperPerson().getIsGood()));
            uspcm.setOrganizations(getListOfIntegersFromListOfOrganizations(spvm.getOrganizations()));
            uspcm.setPowers(getListOfIntegersFromListOfPowers(spvm.getPowers()));

            spvm.setPowers(powerService.getAllPowers(0, Integer.MAX_VALUE));
            spvm.setOrganizations(organizationService.getAllOrganizations(0, Integer.MAX_VALUE));
            
            model.addAttribute("uspcm", uspcm);
        }

        model.addAttribute("spvm", spvm);

        return "superperson/update_superperson";
    }

    @RequestMapping(value = "superperson/updateSuperPerson", method = RequestMethod.POST)
    public String UpdateSuperPerson(@Valid @ModelAttribute("uspcm") UpdateSuperPersonCommandModel uspcm, 
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return returnToPageWithErrorMessages(model, uspcm);
        }

        SuperPerson oldSp = superPersonService.getSuperPersonById(uspcm.getSuperPersonId());
        List<Power> oldPowers = powerService.getAllPowersBySuperPerson(oldSp, 0, Integer.MAX_VALUE);
        for (Power currentPower : oldPowers) {
            superPersonService.deletePowerFromSuperPerson(oldSp, currentPower);
        }

        List<Organization> oldOrganizations = organizationService.getAllOrganizationsBySuperPerson(oldSp, 0, Integer.MAX_VALUE);
        for (Organization currentOrganization : oldOrganizations) {
            superPersonService.deleteOrganizationFromSuperPerson(oldSp, currentOrganization);
        }

        SuperPerson updatedSuperPerson = new SuperPerson();
        updatedSuperPerson.setName(uspcm.getName());
        updatedSuperPerson.setDescription(uspcm.getDescription());
        updatedSuperPerson.setSuperPersonId(uspcm.getSuperPersonId());
        
        String reputation = uspcm.getReputation();
        updatedSuperPerson.setIsGood(getBooleanFromReputation(reputation));

        Integer[] powers = uspcm.getPowers();
        Integer[] orgs = uspcm.getOrganizations();
        updatedSuperPerson = superPersonService.updateSuperPerson(updatedSuperPerson);

        for (Integer currentPower : powers) {
            superPersonService.addSuperPersonToPower(updatedSuperPerson,
                    powerService.getPowerById(currentPower));
        }
        
        for (Integer currentOrg : orgs) {
            superPersonService.addSuperPersonToOrganization(updatedSuperPerson,
                    organizationService.getOrganizationById(currentOrg));
        }

//        newSuperPerson.setIsGood(request.getParameter("description"));
        return "redirect:/superperson/superpersons";
    }

    
}

//Lucas's way of doing updateSuperPerson

/*
SuperPersonViewModel spvm2 = superPersonService.getSuperPersonViewModelBySuperPersonId(superPersonToUpdate);
        List<Power> allPowers = powerService.getAllPowers(0, Integer.MAX_VALUE);
        List<Power> hasPowers = powerService.getAllPowersBySuperPerson(spvm2.getSuperPerson(), 0, Integer.MAX_VALUE);
        List<Power> doesntHavePowers = allPowers;
        for (Power currentPower : hasPowers) {
            doesntHavePowers.remove(currentPower);
        }

        List<Organization> allOrganizations = organizationService.getAllOrganizations(0, Integer.MAX_VALUE);
        List<Organization> hasOrganizations = organizationService.getAllOrganizationsBySuperPerson(spvm2.getSuperPerson(), 0, Integer.MAX_VALUE);
        List<Organization> doesntHaveOrganizations = allOrganizations;
        for (Organization currentOrganization : hasOrganizations) {
            doesntHaveOrganizations.remove(currentOrganization);
        }

        SuperPersonViewModel spvm = new SuperPersonViewModel();
        spvm.setPowers(powerService.getAllPowers(0, Integer.MAX_VALUE));
        spvm.setOrganizations(organizationService.getAllOrganizations(0, Integer.MAX_VALUE));
        model.addAttribute("spvm", spvm);
        model.addAttribute("spvm2", spvm2);
        model.addAttribute("doesntHavePowers", doesntHavePowers);
        model.addAttribute("doesntHaveOrganizations", doesntHaveOrganizations);
 */

 /*

    first way of trying to populate the Update page
<c:set var="superPersonId" value="${empty uspcm.superPersonId ? spvm.superPerson.superPersonId : uspcm.superPersonId}"/>
                    <c:set var="name" value="${empty uspcm.name ? spvm.superPerson.name : uspcm.name}"/>
                    <c:set var="description" value="${empty uspcm.description ? spvm.superPerson.description : uspcm.description}"/>
                    <c:set var="reputation" value="${empty uspcm.reputation ? spvm.superPerson.reputation : uspcm.reputation}"/>
                    <c:set var="powers" value="${empty uspcm.powers ? spvm.powers : uspcm.powers}"/>
                    <c:set var="orgs" value="${empty uspcm.organizations ? spvm.organizations : uspcm.organizations}"/>
                    

 */
