/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    DVD addDVD(String id, DVD dvd) throws DVDLibraryDaoException;
    DVD removeDVD(String id) throws DVDLibraryDaoException;
    //repeat method -- 
    //DVD editDVDInfo(String title) throws DVDLibraryDaoException;
    List<DVD> listAllDVDs() throws DVDLibraryDaoException;
    DVD showInfoForDVD(String id) throws DVDLibraryDaoException;
    //Collection<DVD> searchByTitle() throws DVDLibraryDaoException;
    void changeTitle(String id, String newTitle) throws DVDLibraryDaoException;
    Set<String> getIds() throws DVDLibraryDaoException;
    boolean hasId(String id) throws DVDLibraryDaoException;
}   
