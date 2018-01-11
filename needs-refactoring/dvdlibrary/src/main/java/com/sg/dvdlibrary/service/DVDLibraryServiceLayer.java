/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 *
 * @author matt
 */
public interface DVDLibraryServiceLayer {

    DVD createDVD(String title, DVD dvd) throws DVDLibraryDataValidationException, DVDLibraryPersistenceException;  
    DVD removeDVD(String title)throws DVDLibraryPersistenceException;
    List<DVD> listAllDvds()throws DVDLibraryPersistenceException;
    void changeTitle(String newTitle, String oldTitle)throws DVDLibraryPersistenceException;
    boolean doesTitleExist(String title) throws DVDLibraryPersistenceException;
    Set<String> getKeys() throws DVDLibraryPersistenceException;
    long getDVDAge(LocalDate releaseDate) throws DVDLibraryPersistenceException;
    double getAverageDVDAge() throws DVDLibraryPersistenceException;
    List<DVD> findNewestDVDInCollection() throws DVDLibraryPersistenceException;    
    List<DVD> findOldestDVDInCollection() throws DVDLibraryPersistenceException;    
    long getMinAge () throws DVDLibraryPersistenceException;
    long getMaxAge () throws DVDLibraryPersistenceException;
    DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException;
    
}
