    package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DVDLibraryDao;
import com.sg.dvdlibraryspringmvc.dto.DVD;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@CrossOrigin
@Controller
public class DVDController {

    private DVDLibraryDao dvdDao;
    
    @Inject
    public DVDController(DVDLibraryDao dvdDao){
        this.dvdDao = dvdDao;
    }
    
    
    public DVDController() {
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DVD getDVD(@PathVariable("id") long id) {
        return dvdDao.getDVDById(id);
    }
    
    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DVD createDVD(@Valid @RequestBody DVD dvd) {//@RequestBody converts JSON to DVD object for us
        return dvdDao.addDVD(dvd);                                //@Valid makes sure no violations of DVD rules were made (see Model/DVD dto)
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDVD(@PathVariable("id") long id) {
        dvdDao.removeDVD(id);
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDVD(@PathVariable("id") long id, @Valid @RequestBody DVD dvd) throws UpdateIntegrityException {

        if (id != dvd.getDvdId()){
            throw new UpdateIntegrityException("DVD Id on RUL must match DVD Id in submitted data");
        }
        
        dvdDao.updateDVD(dvd);
    }

    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public List<DVD> getAllDVDs() {
        return dvdDao.getAllDVDs();
    }
    
}
