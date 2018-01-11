/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public interface DVDLibraryDao {
//    io.println("1. Add DVD to Collection");
//        io.println("2. Remove DVD from Collection");
//        io.println("3. Edit existing DVD's info");
//        io.println("4. List all DVDs in Collection");
//        io.println("5. Show info for selected DVD");
//        io.println("6. Search for DVD by title");

    public DVD addDVD(DVD dvd);
    public void updateDVD(DVD dvd);
    public void removeDVD(long dvdId);
    public List<DVD> getAllDVDs();
    public DVD getDVDById(long dvdId);
    public List<DVD> searchDVDs(Map<SearchTerm,String> criteria);
    
    //
    
    
}   