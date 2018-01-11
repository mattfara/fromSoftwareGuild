/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDaoStubImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoLambdaImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDaoStubImpl;
import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author matt
 */
public class DVDLibraryServiceLayerTest {
    private DVDLibraryServiceLayer service;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    //dvd2.setReleaseDate(LocalDate.parse("10/10/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    
    public DVDLibraryServiceLayerTest() {
        DVDLibraryDao dao = new DVDLibraryDaoStubImpl();
        DVDLibraryAuditDao auditDao = new DVDLibraryAuditDaoStubImpl();

        service = new DVDLibraryServerLayerLambdaImpl(dao, auditDao);
    }
    
    @Test
    public void testCreateDVD() throws Exception{
        DVD dvd = new DVD("Fargo (1997)");
        dvd.setDirectorName("Coen bros.");
        dvd.setMpaaRating("R");
        dvd.addNote("Great acting and story");
        dvd.setReleaseDate(LocalDate.parse("1997/05/20", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd.setStudio("Warner bros");
        service.createDVD(dvd.getTitle(), dvd);
    }
    
    @Test
    public void testCreateDVDInvalidData() throws Exception {
        DVD dvd = new DVD("Fargo (1997)");
        dvd.setDirectorName("");
        dvd.setMpaaRating("R");
        dvd.addNote("Great acting and story");
        dvd.setReleaseDate(LocalDate.parse("1997/05/20", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd.setStudio("Warner bros");
        
        try{
            service.createDVD(dvd.getTitle(), dvd);
            fail("Expected DVDLibraryDataValidationException to be thrown, but wasn't");
        } catch(DVDLibraryDataValidationException e){
            return;
        }
    }
    
    @Test
    public void testListAllDVDs() throws Exception {
        assertEquals(2,service.listAllDvds().size());
    }
    
    @Test
    public void testGetDVDByTitle() throws Exception {
        DVD dvd = service.getDVDByTitle("Rocky (1977)");
        assertNotNull(dvd);
        dvd = service.getDVDByTitle("Pinto bean (1994)");
        assertNull(dvd);
    }
    
    @Test
    public void testRemoveDVD() throws Exception{
        DVD dvd = service.removeDVD("Rocky (1977)");
        assertNotNull(dvd);
        dvd = service.getDVDByTitle("Pinto bean (1994)");
        assertNull(dvd);
    }
    
    @Test
    public void testFindNewestDVDInCollection() throws Exception{    
        List<DVD> newestDVDs = service.findNewestDVDInCollection();
        
        assertEquals(2, newestDVDs.size());
    }
    
    @Test
    public void testFindOldestDVDInCollection() throws Exception{    
        List<DVD> oldestDVDs = service.findOldestDVDInCollection();    
        assertEquals(2, oldestDVDs.size());
    }
    
    @Test
    public void testGetMinAge()throws Exception{
        long minAge = service.getMinAge();
        
        assertEquals(40, minAge);
    }
    
    @Test
    public void testGetMaxAge()throws Exception{
        long maxAge = service.getMaxAge();
        
        assertEquals(40, maxAge);
    }
    
    @Test
    public void isDataValid(){
        
    }
    
    
}
