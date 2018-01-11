/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.controller.commandmodel.CreateLocationCommandModel;
import com.sg.superherosightingsspringmvc.controller.commandmodel.UpdateLocationCommandModel;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.service.AddressService;
import com.sg.superherosightingsspringmvc.service.LocationService;
import com.sg.superherosightingsspringmvc.viewmodel.LocationViewModel;
import java.util.List;
import java.util.Map;
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
public class LocationsController {

    @Inject
    LocationService locationService;
    @Inject
    AddressService addressService;

    public LocationsController() {
    }

    @RequestMapping(value = "/location/locations", method = RequestMethod.GET)
    public String displayLocationsPage(Model model, RedirectAttributes redirectAttrs) {

        List<LocationViewModel> lvmList = locationService.getLocationViewModels(0, 10);

        if (lvmList.size() != 0) {
            Integer locationClicked = lvmList.get(0).getLocation().getLocationId();
            redirectAttrs.addAttribute("locationClicked", locationClicked);
            return "redirect:/location/chooseLocation?locationClicked={locationClicked}";
        }

        model.addAttribute("lvmList", lvmList);
        return "/location/locations";
    }

    @RequestMapping(value = "/location/chooseLocation", method = RequestMethod.GET)
    public String displayLocationsPageWithSelectedLocation(Model model, HttpServletRequest request, @RequestParam Integer locationClicked) {
        LocationViewModel lvm = locationService.getLocationViewModelByLocationId(locationClicked);
        model.addAttribute("lvm", lvm);

        List<LocationViewModel> lvmList = locationService.getLocationViewModels(0, 10);
        model.addAttribute("lvmList", lvmList);

        return "/location/locations";
    }

    @RequestMapping(value = "location/displayCreateLocationPage", method = RequestMethod.GET)
    public String displayCreateLocationPage() {
        return "location/create_location";
    }

    @RequestMapping(value = "location/displayDeleteLocationPage", method = RequestMethod.GET)
    public String displayDeleteLocationPage() {
        return "location/delete_location";
    }

    private String returnToPageWithErrorMessages(Model model, CreateLocationCommandModel clcm) {

        model.addAttribute(clcm);

        return "sighting/create_sighting";
    }

    @RequestMapping(value = "location/createLocation", method = RequestMethod.GET)
    public String createLocation(@Valid @ModelAttribute("clcm") CreateLocationCommandModel clcm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return returnToPageWithErrorMessages(model, clcm);
        }

        Address newAddress = createSetAndSubmitAddress(clcm);

        Location newLocation = createSetAndSubmitLocation(clcm, newAddress);

        return "redirect:/location/locations";
    }

    //redirectToLocationsPage
    @RequestMapping(value = "location/redirectToLocationsPage", method = RequestMethod.GET)
    public String redirectToLocationsPage() {
        return "redirect:/location/locations";
    }

    private String returnToPageWithErrorMessages(Model model, UpdateLocationCommandModel ulcm) {
        model.addAttribute(ulcm);
        return "location/update_location";
    }

    @RequestMapping(value = "location/diplayUpdateLocationPage", method = RequestMethod.GET)
    public String displayUpdateLocationPage(Model model, @RequestParam Integer locationToUpdate) {
        
        LocationViewModel lvm = locationService.getLocationViewModelByLocationId(locationToUpdate);

        if (!model.containsAttribute("ulcm")) {
            UpdateLocationCommandModel ulcm = new UpdateLocationCommandModel();

            ulcm.setAddressId(lvm.getAddress().getAddressId());
            ulcm.setStreet(lvm.getAddress().getStreet());
            ulcm.setCity(lvm.getAddress().getCity());
            ulcm.setState(lvm.getAddress().getState());
            ulcm.setZipcode(lvm.getAddress().getZipcode());

            ulcm.setLocationId(lvm.getLocation().getLocationId());
            ulcm.setDescription(lvm.getLocation().getDescription());
            ulcm.setLatitude(lvm.getLocation().getLatitude());
            ulcm.setLongitude(lvm.getLocation().getLongitude());
            ulcm.setName(lvm.getLocation().getName());

            model.addAttribute("ulcm", ulcm);
        }
        
        model.addAttribute("lvm", lvm); //sending the VM will be useful in other Controllers

        return "location/update_location";
    }

    @RequestMapping(value = "/location/updateLocation", method = RequestMethod.POST)
    public String updateLocation(@Valid @ModelAttribute("ulcm") UpdateLocationCommandModel ulcm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {

            return returnToPageWithErrorMessages(model, ulcm);
        }

        Address updatedAddress = createSetAndSubmitAddress(ulcm);
        createSetAndSubmitLocation(ulcm, updatedAddress);

        return "redirect:/location/locations";
    }

    private Address createSetAndSubmitAddress(CreateLocationCommandModel clcm) {
        Address newAddress = new Address();

        newAddress.setCity(clcm.getCity());
        newAddress.setState(clcm.getState());
        newAddress.setStreet(clcm.getStreet());
        newAddress.setZipcode(clcm.getZipcode());

        return addressService.createAddress(newAddress);
    }

    private Address createSetAndSubmitAddress(UpdateLocationCommandModel ulcm) {
        Address updatedAddress = new Address();

        updatedAddress.setCity(ulcm.getCity());
        updatedAddress.setState(ulcm.getState());
        updatedAddress.setStreet(ulcm.getStreet());
        updatedAddress.setZipcode(ulcm.getZipcode());
        updatedAddress.setAddressId(ulcm.getAddressId());

        return addressService.updateAddress(updatedAddress);
    }

    private Location createSetAndSubmitLocation(CreateLocationCommandModel clcm, Address newAddress) {
        Location newLocation = new Location();

        newLocation.setAddress(newAddress);
        newLocation.setDescription(clcm.getDescription());
        newLocation.setLatitude(clcm.getLatitude());
        newLocation.setLongitude(clcm.getLongitude());
        newLocation.setName(clcm.getName());

        Location createdLocation = locationService.createLocation(newLocation);
        return createdLocation;
    }

    private Location createSetAndSubmitLocation(UpdateLocationCommandModel ulcm, Address updatedAddress) {
        Location updatedLocation = new Location();

        updatedLocation.setAddress(updatedAddress);
        updatedLocation.setDescription(ulcm.getDescription());
        updatedLocation.setLatitude(ulcm.getLatitude());
        updatedLocation.setLongitude(ulcm.getLongitude());
        updatedLocation.setName(ulcm.getName());

        return locationService.updateLocation(updatedLocation);

    }
}

/*

This is what I had in the jsp before

                    <c:set var="locationId" value="${empty ulcm.locationId ? lvm.location.locationId : ulcm.locationId}"/>
                    <c:set var="addressId" value="${empty ulcm.addressId ? lvm.address.addressId : ulcm.addressId}"/>
                    <c:set var="name" value="${empty ulcm.name ? lvm.location.name : ulcm.name}"/>
                    <c:set var="description" value="${empty ulcm.description ? lvm.location.description : ulcm.description}"/>
                    <c:set var="street" value="${empty ulcm.street ? lvm.address.street : ulcm.street}"/>
                    <c:set var="city" value="${empty ulcm.city ? lvm.address.city : ulcm.city}"/>
                    <c:set var="state" value="${empty ulcm.state ? lvm.address.state : ulcm.state}"/>
                    <c:set var="zipcode" value="${empty ulcm.zipcode ? lvm.address.zipcode : ulcm.zipcode}"/>
                    <c:set var="latitude" value="${empty ulcm.latitude ? lvm.location.latitude : ulcm.latitude}"/>
                    <c:set var="longitude" value="${empty ulcm.longitude ? lvm.location.longitude : ulcm.longitude}"/>


*/