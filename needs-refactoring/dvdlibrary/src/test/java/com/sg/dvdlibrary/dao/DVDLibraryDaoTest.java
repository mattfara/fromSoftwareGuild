/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matt
 */
public class DVDLibraryDaoTest {
    
    DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
    
    
    @BeforeClass
    public static void setUpClass() {
    }
     
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws DVDLibraryPersistenceException {
        List<DVD> dvdList = dao.listAllDVDs();
        for (DVD currentDVD : dvdList ){
            dao.removeDVD(currentDVD.getTitle());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testAddAndGetDVD() throws Exception {
        DVD dvd = new DVD("Rodney (2001)");
        dvd.setDirectorName("Sam Roberts");
        dvd.setMpaaRating("Joe Dirt");
        dvd.addNote("Good for a goofy kid");
        dvd.addNote("Saw it when I lived in NY");
        dvd.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd.setStudio("21 Centuries");
        
        dao.addDVD(dvd.getTitle(), dvd);
        DVD fromDao = dao.getDVDByTitle(dvd.getTitle());
        assertEquals(dvd, fromDao);
    }

    /**
     * Test of removeDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testRemoveDVD() throws Exception {
        //pre-populating dao
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (1989)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Honey, I Fucked the Kids (1989)");
        dvd2.setDirectorName("Joe Johnston");
        dvd2.setMpaaRating("PG-13");
        dvd2.addNote("Good for a goofy kid");
        dvd2.addNote("Saw it when I lived in NY");
        dvd2.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd2.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd2.getTitle(), dvd2);
        //removing some things from dao
        
        dao.removeDVD(dvd1.getTitle());
        assertEquals(1, dao.listAllDVDs().size());
        dao.removeDVD(dvd2.getTitle());
        assertEquals(0, dao.listAllDVDs().size());
        
    }

    /**
     * Test of listAllDVDs method, of class DVDLibraryDao.
     */
    @Test
    public void testListAllDVDs() throws Exception {
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (1989)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        dvd1.addNote("Good for goofy kids");
        dvd1.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Honey, I Shot the Kids (1989)");
        dvd2.setDirectorName("Joe Johnston");
        dvd2.setMpaaRating("PG-13");
        dvd2.addNote("Good for goofy kids");
        dvd2.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd2.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        assertEquals(2, dao.listAllDVDs().size());
    }

    
        /**
     * Test of getKeys method, of class DVDLibraryDao.
     * 
     * 
     */
    @Test
    public void testGetKeys() throws Exception {
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (1989)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        dvd1.addNote("Good for goofy kids");
        dvd1.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        Set<String> oneKey = new HashSet<String>(Arrays.asList("Honey, I Shrunk the Kids (1989)"));
        assertEquals(oneKey ,dao.getKeys());
    }
    
    
    /**
     * Test of changeTitle method, of class DVDLibraryDao.
     */
    @Test
    public void testChangeTitle() throws Exception {
        //make sure oldTitle != newTitle, and all other fields same
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (1989)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        dvd1.addNote("Good for goofy kids");
        dvd1.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        dao.changeTitle("Honey, I Sold the Kids (1989)", "Honey, I Shrunk the Kids (1989)");
        DVD newDVD = dao.getDVDByTitle("Honey, I Sold the Kids (1989)");
        
        assertEquals("Honey, I Sold the Kids (1989)", newDVD.getTitle());
        assertTrue(dvd1.getDirectorName().equals(newDVD.getDirectorName()));
        assertTrue(dvd1.getNotes().equals(newDVD.getNotes()));
        assertTrue(dvd1.getMpaaRating().equals(newDVD.getMpaaRating()));
        
    }
    
    @Test
    public void testAddNoteToDVD() throws Exception{
        //make sure oldTitle != newTitle, and all other fields same
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (1989)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD fromDao = dao.getDVDByTitle(dvd1.getTitle());
        
        assertEquals(2, fromDao.getNotes().size());
        assertEquals("Good for a goofy kid", fromDao.getNotes().get(0));
        assertEquals("Saw it when I lived in NY", fromDao.getNotes().get(1));
    
    }
    
    @Test
    public void testDoesTitleExist()throws Exception{
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (1989)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        dao.doesTitleExist(dvd1.getTitle());
    }
        
    @Test
    public void testGetDVDByTitle() throws Exception{
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (1989)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD fromDao = dao.getDVDByTitle(dvd1.getTitle());
        
        assertEquals(dvd1.getDirectorName(), fromDao.getDirectorName());
        assertEquals(dvd1.getStudio(), fromDao.getStudio());
        
    }
    
    @Test
    public void testFindAllMoviesReleasedInLastNYears() throws Exception{
        DVD dvd1 = new DVD("Honey, I Sold the Kids (2010)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("PG-13");
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("2010/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Schmocko (2011)");
        dvd2.setDirectorName("Joe Johnston");
        dvd2.setMpaaRating("PG-13");
        dvd2.addNote("Good for a goofy kid");
        dvd2.addNote("Saw it when I lived in NY");
        dvd2.setReleaseDate(LocalDate.parse("2011/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd2.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        DVD dvd3 = new DVD("Fudge Eater (1934)");
        dvd3.setDirectorName("Joe Johnston");
        dvd3.setMpaaRating("PG-13");
        dvd3.addNote("Good for a goofy kid");
        dvd3.addNote("Saw it when I lived in NY");
        dvd3.setReleaseDate(LocalDate.parse("1934/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd3.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd3.getTitle(), dvd3);
        
        List<DVD> dvdsPastNYears = dao.findAllMoviesReleasedInLastNYears(10);
        List<String> dvdsPastNYearsTitles = dvdsPastNYears.stream().map(dvd -> dvd.getTitle()).collect(Collectors.toList());
        
        
        assertEquals(2, dvdsPastNYears.size());
        assertTrue(dvdsPastNYearsTitles.contains("Schmocko (2011)"));
        assertTrue(dvdsPastNYearsTitles.contains("Honey, I Sold the Kids (2010)"));
        assertFalse(dvdsPastNYearsTitles.contains("Fudge Eater (1934)"));
    }
    
    @Test
    public void testFindAllMoviesWithGivenMPAARating() throws Exception{
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (2010)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("G");
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("2010/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Schmocko (2011)");
        dvd2.setDirectorName("Joe Johnston");
        dvd2.setMpaaRating("G");
        dvd2.addNote("Good for a goofy kid");
        dvd2.addNote("Saw it when I lived in NY");
        dvd2.setReleaseDate(LocalDate.parse("2011/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd2.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        DVD dvd3 = new DVD("Fudge Eater (1934)");
        dvd3.setDirectorName("Joe Johnston");
        dvd3.setMpaaRating("PG-13");
        dvd3.addNote("Good for a goofy kid");
        dvd3.addNote("Saw it when I lived in NY");
        dvd3.setReleaseDate(LocalDate.parse("1934/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd3.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd3.getTitle(), dvd3);
        
        List<DVD> gRatedDVDs = dao.findAllMoviesWithGivenMPAARating("G"); //should use an equalsIgnoreCase
        List<String> gRatedDVDTitles = gRatedDVDs.stream().map(dvd -> dvd.getTitle()).collect(Collectors.toList());
                
        assertEquals(2, gRatedDVDTitles.size());
        assertTrue(gRatedDVDTitles.contains("Schmocko (2011)"));
        assertTrue(gRatedDVDTitles.contains("Honey, I Shrunk the Kids (2010)"));
        assertFalse(gRatedDVDTitles.contains("Fudge Eater (1934)"));
        
    }
    
    @Test
    public void testFindAllMoviesByGivenDirector() throws Exception{
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (2010)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("G");
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("2010/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Schmocko (2011)");
        dvd2.setDirectorName("Joe Johnston");
        dvd2.setMpaaRating("G");
        dvd2.addNote("Good for a goofy kid");
        dvd2.addNote("Saw it when I lived in NY");
        dvd2.setReleaseDate(LocalDate.parse("2011/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd2.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        DVD dvd3 = new DVD("Fudge Eater (1934)");
        dvd3.setDirectorName("Mack the Knife");
        dvd3.setMpaaRating("PG-13");
        dvd3.addNote("Good for a goofy kid");
        dvd3.addNote("Saw it when I lived in NY");
        dvd3.setReleaseDate(LocalDate.parse("1934/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd3.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd3.getTitle(), dvd3);
        
        DVD dvd4 = new DVD("Pin Stripe (1949)");
        dvd4.setDirectorName("Joe Johnston");
        dvd4.setMpaaRating("PG-13");
        dvd4.addNote("Good for a goofy kid");
        dvd4.addNote("Saw it when I lived in NY");
        dvd4.setReleaseDate(LocalDate.parse("1934/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd4.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd4.getTitle(), dvd4);
        
        Map<String, List<DVD>> joeDVDs = dao.findAllMoviesByGivenDirector("Joe Johnston");
        
        
        //how do I either dig into this data structure to test for titles, or make a better data structure?
        
        assertTrue(joeDVDs.containsKey("G"));
        assertTrue(joeDVDs.containsKey("PG-13"));
        //assertEquals(2, dvdsInJoeDVDs.size());
        

        assertFalse(joeDVDs.containsKey("R"));
        
        
    }
    
    @Test
    public void testFindAllMoviesByGivenStudio() throws Exception{
        DVD dvd1 = new DVD("Honey, I Shrunk the Kids (2010)");
        dvd1.setDirectorName("Joe Johnston");
        dvd1.setMpaaRating("G");
        
        dvd1.addNote("Good for a goofy kid");
        dvd1.addNote("Saw it when I lived in NY");
        dvd1.setReleaseDate(LocalDate.parse("2010/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd1.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Schmocko (2011)");
        dvd2.setDirectorName("Joe Johnston");
        dvd2.setMpaaRating("G");
        dvd2.addNote("Good for a goofy kid");
        dvd2.addNote("Saw it when I lived in NY");
        dvd2.setReleaseDate(LocalDate.parse("2011/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd2.setStudio("Buena Vista Pictures");
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        DVD dvd3 = new DVD("Fudge Eater (1934)");
        dvd3.setDirectorName("Mack the Knife");
        dvd3.setMpaaRating("PG-13");
        
        
        dvd3.addNote("Good for a goofy kid");
        dvd3.addNote("Saw it when I lived in NY");
        
        dvd3.setReleaseDate(LocalDate.parse("1934/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        dvd3.setStudio("Lift Studios");
        dao.addDVD(dvd3.getTitle(), dvd3);
        
        List<DVD> buenaVistaDVDS = dao.findAllMoviesByGivenStudio("Buena Vista Pictures");
        List<String> buenaVistaDVDSTitles = buenaVistaDVDS.stream().map(dvd -> dvd.getTitle()).collect(Collectors.toList());
        
        assertTrue(buenaVistaDVDSTitles.contains("Honey, I Shrunk the Kids (2010)"));
        assertTrue(buenaVistaDVDSTitles.contains("Schmocko (2011)"));
        assertFalse(buenaVistaDVDSTitles.contains("Fudge Eater (1934)"));
    }
    
    
}
