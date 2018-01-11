/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.service;

import com.sg.dvdlibraryspringmvc.dao.DVDLibraryDao;
import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.dto.DVD;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author matt
 */
public class DVDLibraryServiceImpl implements DVDLibraryService{

    DVDLibraryDao dao;
    
    @Inject
    public DVDLibraryServiceImpl(DVDLibraryDao dao){
        this.dao = dao;
    }
    
    @Override
    public DVD addDVD(DVD dvd) {
        return dao.addDVD(dvd);
    }

    @Override
    public void updateDVD(DVD dvd) {
        dao.updateDVD(dvd);
    }

    @Override
    public void removeDVD(long dvdId) {
        dao.removeDVD(dvdId);
    }

    @Override
    public List<DVD> getAllDVDs() {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVDById(long dvdId) {
        return dao.getDVDById(dvdId);
    }

    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
        return dao.searchDVDs(criteria);
    }
    
}
