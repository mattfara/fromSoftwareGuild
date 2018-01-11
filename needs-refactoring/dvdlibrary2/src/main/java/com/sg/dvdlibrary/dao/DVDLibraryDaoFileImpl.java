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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author matt
 */

//proposed file format: <title>::<releaseDate>::<mpaaRating>::<directorName>::<studio>::<note>

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    //data structure
    private Map<String, DVD> dvds = new HashMap();
    public static final String LIBRARY_FILE = "lib.txt";
    public static final String DELIMITER = "::";
    public static final String OTHER_DELIMETER = "}";
    /**
     *
     * @param title
     * @param dvd
     * @return
     * @throws DVDLibraryDaoException
     */
    
    
    
    @Override
    public DVD addDVD(String id, DVD dvd) throws DVDLibraryDaoException {
            DVD newDVD = dvds.put(id, dvd);
            writeLibrary();
            return newDVD;
    }

    @Override
    public DVD removeDVD(String id) throws DVDLibraryDaoException{
        DVD removedDVD = dvds.remove(id);
        writeLibrary();
        return removedDVD;
    }

    

    @Override
    public List<DVD> listAllDVDs() throws DVDLibraryDaoException{
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }
    @Override
    public DVD showInfoForDVD(String id) throws DVDLibraryDaoException{
        loadLibrary();
        return dvds.get(id);
    }

    @Override
    public void changeTitle(String id, String newTitle) throws DVDLibraryDaoException{
        DVD currentDVD = dvds.get(id);
        currentDVD.setTitle(newTitle);
    }
    
    @Override
    public Set<String> getIds() throws DVDLibraryDaoException{
        return dvds.keySet();
    }
    
    @Override
    public boolean hasId(String id)throws DVDLibraryDaoException {
        return dvds.get(id) != null;
    }
    
    //read/write files
    
    private void loadLibrary() throws DVDLibraryDaoException {
	Scanner scanner;
	    
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        // currentLine looks like this:
        // 1234::Joe::Smith::Java-September2013
        // then currentTokens will be a string array that looks like this:
        //
        // ___________________________________
        // |    |   |     |                  |
        // |1234|Joe|Smith|Java-September2013|
        // |    |   |     |                  |
        // -----------------------------------
        //  [0]  [1]  [2]         [3]
        String[] firstTokens;
        String[] currentTokens;
        
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // Student object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            //first one is a string, second one is a strings
            
            firstTokens = currentLine.split(OTHER_DELIMETER);
            currentLine = firstTokens[0];
            int staticVar = Integer.parseInt(firstTokens[1]);
            
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Student object and put it into the map of students
            // NOTE FOR APPRENTICES: We are going to use the student id
            // which is currentTokens[0] as the map key for our student object.
            // We also have to pass the student id into the Student constructor
            
            /*Said I need to add a constructor here....*/
            /*Added a constructor into the DVD class, on line 20 -- UnsupportedOperationException*/
            
            
            //this must be the problem
            //the read and write don't match --
            //static var not saving into object
            //DVD.dvdCount = Integer.parseInt(staticVar);
            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setTitle(currentTokens[1]);
            currentDVD.setReleaseDate(currentTokens[2]);
            currentDVD.setMpaaRating(currentTokens[3]);
            currentDVD.setDirectorName(currentTokens[4]);
            currentDVD.setStudio(currentTokens[5]);
            currentDVD.setNote(currentTokens[6]);

            // Put currentStudent into the map using studentID as the key
            dvds.put(currentDVD.getId(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    
    /**
	 * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
	 * for file format.
	 * 
	 * @throws ClassRosterDaoException if an error occurs writing to the file
	 */
    private void writeLibrary() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save DVD data.", e);
        }
        
        // Write out the DVD objects to the library file.
        ////////////////////////////////////////////
        List<DVD> dvdList = this.listAllDVDs();
        for (DVD currentDVD : dvdList) {
            // write the Student object to the file
            out.println(currentDVD.getId() + DELIMITER 
                    + currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER 
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getNote() + OTHER_DELIMETER
                    + DVD.getDvdCount());
            // force PrintWriter to write line to the file
            out.flush();
        }
        
        // Clean up
        out.close();
    }
    
    
}
