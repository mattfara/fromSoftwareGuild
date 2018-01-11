/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author matt
 */
public class DVDLibraryDaoStubImpl implements DVDLibraryDao{

    private DVD oneDVD;
    private DVD anotherDVD;
    private Map<String, DVD> dvdList = new HashMap();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
 
    
    public DVDLibraryDaoStubImpl(){
        oneDVD = new DVD("Rocky (1977)");
        oneDVD.setStudio("MGM");
        oneDVD.setReleaseDate(LocalDate.parse("1977/05/20",formatter));
        oneDVD.setMpaaRating("R");
        oneDVD.addNote("it was funny");
        oneDVD.setDirectorName("Stallone");
        
        dvdList.put(oneDVD.getTitle(), oneDVD);
        
        anotherDVD = new DVD("Slappy (1977)");
        anotherDVD.setStudio("Bob's movies");
        anotherDVD.setReleaseDate(LocalDate.parse("1977/05/20",formatter));
        anotherDVD.setMpaaRating("PG-13");
        anotherDVD.addNote("I didn't want to see it");
        anotherDVD.setDirectorName("Fred Flinko");
        
        dvdList.put(anotherDVD.getTitle(), anotherDVD);
        
        
        
        
    }
    
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        if (title.equals(oneDVD.getTitle()) || (title.equals(anotherDVD.getTitle()))) {
            return dvd;
        } else {
            return null;
        }
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryPersistenceException {
        if (title.equals(oneDVD.getTitle()) || (title.equals(anotherDVD.getTitle()))) {
            return oneDVD;
        } else {
            return null;
        }
    }

    @Override
    public List<DVD> listAllDVDs() throws DVDLibraryPersistenceException {
        List<DVD> list = new ArrayList(dvdList.values());
        return list;
    }

    @Override
    public DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException {
        if (title.equals(oneDVD.getTitle()) || (title.equals(anotherDVD.getTitle()))) {
            return oneDVD;
        } else {
            return null;
        } 
    }
    
    @Override
    public void changeTitle(String newTitle, String oldTitle) throws DVDLibraryPersistenceException {
        //do nothing for now
    }

    @Override
    public boolean doesTitleExist(String title) throws DVDLibraryPersistenceException {
        return title.equals(oneDVD.getTitle()) || (title.equals(anotherDVD.getTitle()));
    }
    
    @Override
    public Set<String> getKeys() throws DVDLibraryPersistenceException{
        return dvdList.keySet();
    }

    

    @Override
    public List<DVD> findAllMoviesReleasedInLastNYears(int nYears) throws DVDLibraryPersistenceException {
        LocalDate now = LocalDate.now();
        
        return listAllDVDs().stream()
                .filter(dvd -> (Period.between(dvd.getReleaseDate(), now)).getYears() <= nYears)
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> findAllMoviesWithGivenMPAARating(String mpaaRating) throws DVDLibraryPersistenceException {
        return listAllDVDs().stream()
                .filter(dvd -> dvd.getMpaaRating().equals(mpaaRating))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<DVD>> findAllMoviesByGivenDirector(String director) throws DVDLibraryPersistenceException {
        return listAllDVDs().stream()
                .filter(dvd -> dvd.getDirectorName().equals(director))
                .collect(Collectors.groupingBy(DVD::getMpaaRating));
    }

    @Override
    public List<DVD> findAllMoviesByGivenStudio(String studio) throws DVDLibraryPersistenceException {
        return listAllDVDs().stream()
                .filter(dvd -> dvd.getStudio().equals(studio))
                .collect(Collectors.toList());
    }

    @Override
    public DVD addNoteToDVD(String title, String note) throws DVDLibraryPersistenceException {
        DVD currentDVD = getDVDByTitle(title);
        currentDVD.getNotes().add(note);
        return currentDVD;
    }
}







