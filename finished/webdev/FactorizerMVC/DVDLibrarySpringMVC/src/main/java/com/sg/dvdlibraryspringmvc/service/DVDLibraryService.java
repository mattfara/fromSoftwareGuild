/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.service;

import com.sg.dvdlibraryspringmvc.dao.SearchTerm;
import com.sg.dvdlibraryspringmvc.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public interface DVDLibraryService {
    public DVD addDVD(DVD dvd);
    public void updateDVD(DVD dvd);
    public void removeDVD(long dvdId);
    public List<DVD> getAllDVDs();
    public DVD getDVDById(long dvdId);
    public List<DVD> searchDVDs(Map<SearchTerm,String> criteria);
}
