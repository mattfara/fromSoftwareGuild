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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author matt
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    DVDLibraryDao dao;
    DVDLibraryAuditDao auditDao;
    
    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao){
        this.dao=dao;
        this.auditDao = auditDao;
    }

    
    @Override
    public DVD createDVD(String title, DVD dvd) throws DVDLibraryDataValidationException, DVDLibraryPersistenceException {        
        validateDVDData(dvd);        
        dao.addDVD(title, dvd);
        auditDao.writeAuditEntry("DVD " +dvd.getTitle() + " Created");
        return dvd;
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryPersistenceException{
        auditDao.writeAuditEntry("DVD " + title + " REMOVED.");
        return dao.removeDVD(title);
    }

    @Override
    public List<DVD> listAllDvds() throws DVDLibraryPersistenceException{
        return dao.listAllDVDs();
    }

    @Override
    public void changeTitle(String newTitle, String oldTitle) throws DVDLibraryPersistenceException{
        dao.changeTitle(newTitle, oldTitle);
    }

    @Override
    public DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException{
        return dao.getDVDByTitle(title);
    }

    @Override
    public Set<String> getKeys() throws DVDLibraryPersistenceException {
        return dao.getKeys();
    }

    @Override
    public boolean doesTitleExist(String title) throws DVDLibraryPersistenceException{
        return dao.doesTitleExist(title);
    }
    
    private void validateDVDData(DVD dvd) throws DVDLibraryDataValidationException {
        if (dvd.getTitle()==null || dvd.getTitle().trim().length()==0
            || dvd.getReleaseDate()==null
            || dvd.getDirectorName()==null || dvd.getDirectorName().trim().length()==0 
            || dvd.getMpaaRating()==null || dvd.getMpaaRating().trim().length()==0
            || dvd.getStudio()==null || dvd.getStudio().trim().length()==0){
            
            throw new DVDLibraryDataValidationException ("All fields required, friendo");
        }
    }
    
    @Override
    public long getMaxAge () throws DVDLibraryPersistenceException{
        long maxAge = 0;
        List<DVD> allDVDs = listAllDvds();
        for (DVD currentDVD : allDVDs){
            long currentDVDAge = getDVDAge(currentDVD.getReleaseDate());
            if (currentDVDAge > maxAge){
                maxAge = currentDVDAge;
            }
        }
        return maxAge;
    }
    
    @Override
    public long getMinAge () throws DVDLibraryPersistenceException{
        long minAge = getMaxAge();
        List<DVD> allDVDs = listAllDvds();
        for (DVD currentDVD : allDVDs){
            long currentDVDAge = getDVDAge(currentDVD.getReleaseDate());
            if (currentDVDAge < minAge){
                minAge = currentDVDAge;
            }
        }
        return minAge;
    }

    @Override
    public long getDVDAge(LocalDate releaseDate) throws DVDLibraryPersistenceException{
        Period p = releaseDate.until(LocalDate.now());
        return p.getYears();
    }

    @Override
    public double getAverageDVDAge() throws DVDLibraryPersistenceException {
        List<DVD> allDVDs = listAllDvds();
        int totalYears = 0;
        
        for (DVD currentDVD : allDVDs){
            totalYears+=getDVDAge(currentDVD.getReleaseDate());
        }
        
        return totalYears/allDVDs.size();
    }
    
    @Override
    public List<DVD> findNewestDVDInCollection() throws DVDLibraryPersistenceException {
        long minAge = getMinAge();
        List<DVD> newestDVDs = new ArrayList();
        List<DVD> allDVDs = listAllDvds();
        for (DVD currentDVD : allDVDs){
            if (getDVDAge(currentDVD.getReleaseDate()) == minAge){
                newestDVDs.add(currentDVD);
            }
        }
        return newestDVDs;
//        long youngestAge = getMinAge();
//        return listAllDvds().stream()
//                 .filter(dvd -> (getDVDAge(dvd.getReleaseDate())) == youngestAge)
//                .collect(Collectors.toList());
//    
    }

    @Override
    public List<DVD> findOldestDVDInCollection() throws DVDLibraryPersistenceException {
        long maxAge = getMaxAge();
        List<DVD> oldestDVDs = new ArrayList();
        List<DVD> allDVDs = listAllDvds();
        for (DVD currentDVD : allDVDs){
            if (getDVDAge(currentDVD.getReleaseDate()) == maxAge){
                oldestDVDs.add(currentDVD);
            }
        }
        return oldestDVDs;
    }
    
}
