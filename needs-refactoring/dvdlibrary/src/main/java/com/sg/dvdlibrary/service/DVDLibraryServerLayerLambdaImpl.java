/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author matt
 */

//everything implemented -- just need to fix that one shitty method
public class DVDLibraryServerLayerLambdaImpl implements DVDLibraryServiceLayer{

    DVDLibraryDao dao;
    DVDLibraryAuditDao auditDao;
    
    public DVDLibraryServerLayerLambdaImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao){
        this.dao=dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public DVD createDVD(String title, DVD dvd) throws DVDLibraryDataValidationException, DVDLibraryPersistenceException {
        return dao.addDVD(title, dvd);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryPersistenceException {
        return dao.removeDVD(title);
    }

    @Override
    public List<DVD> listAllDvds() throws DVDLibraryPersistenceException {
        return dao.listAllDVDs();
    }
    
    @Override
    public void changeTitle(String newTitle, String oldTitle) throws DVDLibraryPersistenceException {
        dao.changeTitle(newTitle, oldTitle);
    }

    @Override
    public DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException {
        return dao.getDVDByTitle(title);
    }

    @Override
    public boolean doesTitleExist(String title) throws DVDLibraryPersistenceException {
        return dao.doesTitleExist(title);
    }

    @Override
    public Set<String> getKeys() throws DVDLibraryPersistenceException {
        return dao.getKeys();
    }

    @Override
    public long getDVDAge(LocalDate releaseDate) {
        Period p = releaseDate.until(LocalDate.now());
        return p.getYears();
    }
    
    @Override
    public long getMaxAge () throws DVDLibraryPersistenceException{
        return listAllDvds().stream()
            .mapToLong(dvd -> getDVDAge(dvd.getReleaseDate()))
            .max()
            .getAsLong();
        
    }
    @Override
    public long getMinAge () throws DVDLibraryPersistenceException{
        return listAllDvds().stream()
                .mapToLong(dvd -> getDVDAge(dvd.getReleaseDate()))
                .min()
                .getAsLong();

    }
    
    @Override
    public double getAverageDVDAge() throws DVDLibraryPersistenceException{
        return dao.listAllDVDs()
                .stream()
                .map(dvd -> dvd.getReleaseDate())
                .mapToLong(releaseDate -> {
                        return getDVDAge(releaseDate);
                })
                .average()
                .getAsDouble();
    }
    
    
    @Override
    public List<DVD> findNewestDVDInCollection() throws DVDLibraryPersistenceException{
        long youngestAge = getMinAge();
        return listAllDvds().stream()
                 .filter(dvd -> (getDVDAge(dvd.getReleaseDate())) == youngestAge)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DVD> findOldestDVDInCollection() throws DVDLibraryPersistenceException{
        long oldestAge = getMaxAge();
        return listAllDvds().stream()
                 .filter(dvd -> (getDVDAge(dvd.getReleaseDate())) == oldestAge)
                .collect(Collectors.toList());
    }
    
    

}
