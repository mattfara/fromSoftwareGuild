/** To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author matt
 */
public interface DVDLibraryDao {
    DVD addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException;
    DVD removeDVD(String title) throws DVDLibraryPersistenceException;
    List<DVD> listAllDVDs() throws DVDLibraryPersistenceException;
    DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException;
    Set<String> getKeys() throws DVDLibraryPersistenceException; //not necessary
    void changeTitle(String newTitle, String oldTitle) throws DVDLibraryPersistenceException; //not necessary
    boolean doesTitleExist(String title) throws DVDLibraryPersistenceException;//should be in service
    List<DVD> findAllMoviesReleasedInLastNYears(int nYears) throws DVDLibraryPersistenceException;
    List<DVD> findAllMoviesWithGivenMPAARating(String mpaaRating) throws DVDLibraryPersistenceException;
    Map<String,List<DVD>> findAllMoviesByGivenDirector(String director) throws DVDLibraryPersistenceException;
    List<DVD> findAllMoviesByGivenStudio(String studio) throws DVDLibraryPersistenceException;    
    DVD addNoteToDVD(String title, String note) throws DVDLibraryPersistenceException;
}
