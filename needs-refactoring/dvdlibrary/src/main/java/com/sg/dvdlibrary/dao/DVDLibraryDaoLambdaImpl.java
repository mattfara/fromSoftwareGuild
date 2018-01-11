/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author matt
 */
public class DVDLibraryDaoLambdaImpl implements DVDLibraryDao {
    
    private Map<String, DVD> dvds = new HashMap();
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    public static final String LIST_DELIMITER = ";;";
        
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
            DVD newDVD = dvds.put(title, dvd);
            //writeLibrary();
            return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryPersistenceException{
        DVD removedDVD = dvds.remove(title);
        //writeLibrary();
        return removedDVD;
    }

    

    @Override
    public List<DVD> listAllDVDs() throws DVDLibraryPersistenceException{
        //loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    
    @Override
    public void changeTitle(String newTitle, String oldTitle) throws DVDLibraryPersistenceException{
        DVD oldDVD = getDVDByTitle(oldTitle); 
        oldDVD.setTitle(newTitle); 
        removeDVD(oldTitle); 
        dvds.put(newTitle, oldDVD); 
        //writeLibrary(); 
    }
    
        
    @Override
    public DVD getDVDByTitle(String title) throws DVDLibraryPersistenceException{
        //loadLibrary();
        return dvds.get(title);
    }

    @Override
    public Set<String> getKeys() throws DVDLibraryPersistenceException{
        //loadLibrary();//added this under assumption
        return dvds.keySet(); //should I load before this?
    }

    public boolean doesTitleExist(String title) throws DVDLibraryPersistenceException{
        return dvds.get(title) != null;
    }

    //New Lambda methods for 9/12/17
    
    
    @Override
    public List<DVD> findAllMoviesReleasedInLastNYears(int nYears)throws DVDLibraryPersistenceException {
        
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
    public List<DVD> findAllMoviesByGivenStudio(String studio) throws DVDLibraryPersistenceException{
        return listAllDVDs().stream()
                .filter(dvd -> dvd.getStudio().equals(studio))
                .collect(Collectors.toList());
    }
    
    @Override
    public DVD addNoteToDVD(String title, String note) throws DVDLibraryPersistenceException{
        DVD currentDVD = getDVDByTitle(title);
        currentDVD.getNotes().add(note);
        return currentDVD;
    }
    //read/write files

    private void loadLibrary() throws DVDLibraryPersistenceException {
	Scanner scanner;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");    
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException(
                    "-_- Could not load library data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setReleaseDate(LocalDate.parse(currentTokens[1],formatter));
            currentDVD.setMpaaRating(currentTokens[2]);
            currentDVD.setDirectorName(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            String[] notes = currentTokens[5].split(LIST_DELIMITER);
            for (String currentNote : notes){
                currentDVD.addNote(currentNote);
            }
            
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }
    
    /**
	 * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
	 * for file format
	 * 
	 * @throws ClassRosterDaoException if an error occurs writing to the file
	 */
    private void writeLibrary() throws DVDLibraryPersistenceException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException(
                    "Could not save DVD data.", e);
        }

        List<DVD> dvdList = this.listAllDVDs();
        for (DVD currentDVD : dvdList) {
            List<String> notes = currentDVD.getNotes();
            out.println(currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate().format(formatter) + DELIMITER 
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER);
                    for (int currentNote = 0; currentNote<notes.size(); currentNote++){
                        if (currentNote==currentDVD.getNotes().size()-1){
                            out.println(notes.get(currentNote));
                        } else {
                            out.println(notes.get(currentNote)+LIST_DELIMITER);
                        }
                    }
            out.flush();
        }
        out.close();
    }    

    
}
