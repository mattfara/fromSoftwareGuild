/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.service.SightingService;
import com.sg.superherosightingsspringmvc.viewmodel.SightingViewModel;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author matt
 */
@Controller
public class HomeController {

    @Inject
    SightingService sightingService;
    //many more beans required....

    public HomeController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {

        List<SightingViewModel> svmList = sightingService.getSightingViewModels(0, 10);
        model.addAttribute("svmList", svmList);
//                model.addAttribute("superPersonsAtSightings", superPersonsAtSightings);

        return "/home/home";
    }
}
