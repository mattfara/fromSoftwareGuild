/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author matt
 */


public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    //data structure
    private Map<String, DVD> dvds = new HashMap();
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    /**
     *
     * @param title
     * @param dvd
     * @return
     * @throws DVDLibraryPersistenceException
     */
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
    
    //read/write files
//***This is commented out now because of the new field for the DVD -- notes. It is a List<String>, and therefore needs
    //different treatment to be saved to file
//    private void loadLibrary() throws DVDLibraryPersistenceException {
//	Scanner scanner;
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");    
//        
//        try {
//            // Create Scanner for reading the file
//            scanner = new Scanner(
//                    new BufferedReader(
//                            new FileReader(LIBRARY_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new DVDLibraryPersistenceException(
//                    "-_- Could not load library data into memory.", e);
//        }
//        
//        String currentLine;
//        String[] currentTokens;
//        while (scanner.hasNextLine()) {
//            currentLine = scanner.nextLine();
//            currentTokens = currentLine.split(DELIMITER);
//            DVD currentDVD = new DVD(currentTokens[0]);
//            currentDVD.setReleaseDate(LocalDate.parse("1989/05/02", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//            currentDVD.setMpaaRating(currentTokens[2]);
//            currentDVD.setDirectorName(currentTokens[3]);
//            currentDVD.setStudio(currentTokens[4]);
//            String[] notes = currentTokens[5].split(LIST_DELIMITER);
//            for (String currentNote : notes){
//                currentDVD.getNote().add(currentNote);
//            }
//
//            dvds.put(currentDVD.getTitle(), currentDVD);
//        }
//        scanner.close();
//    }
//    
//    /**
//	 * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
//	 * for file format.
//	 * 
//	 * @throws ClassRosterDaoException if an error occurs writing to the file
//	 */
//    private void writeLibrary() throws DVDLibraryPersistenceException {
//        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
//        } catch (IOException e) {
//            throw new DVDLibraryPersistenceException(
//                    "Could not save DVD data.", e);
//        }
//
//        List<DVD> dvdList = this.listAllDVDs();
//        for (DVD currentDVD : dvdList) {
//            out.println(currentDVD.getTitle() + DELIMITER
//                    + currentDVD.getReleaseDate().toString() + DELIMITER 
//                    + currentDVD.getMpaaRating() + DELIMITER
//                    + currentDVD.getDirectorName() + DELIMITER
//                    + currentDVD.getStudio() + DELIMITER
//                    + currentDVD.getNote());
//            out.flush();
//        }
//        out.close();
//    }

    

    @Override
    public List<DVD> findAllMoviesReleasedInLastNYears(int nYears)throws DVDLibraryPersistenceException {
        
        LocalDate now = LocalDate.now();
        
        List<DVD> allDVDs = listAllDVDs();
        List<DVD> dvdsOfCorrectAge = new ArrayList();
        for (DVD dvd : allDVDs){
            if ((Period.between(dvd.getReleaseDate(), now)).getYears() <= nYears){
                dvdsOfCorrectAge.add(dvd);
            }
        }
        
        return dvdsOfCorrectAge;
        
        //Lambda version
//        return listAllDVDs().stream()
//                .filter(dvd -> (Period.between(dvd.getReleaseDate(), now)).getYears() <= nYears)
//                .collect(Collectors.toList());
    }
    
    @Override
    public List<DVD> findAllMoviesWithGivenMPAARating(String mpaaRating) throws DVDLibraryPersistenceException {
        
        List<DVD> allDVDs = listAllDVDs();
        List<DVD> dvdsWithCorrectRating = new ArrayList();
        
        
        for (DVD dvd : allDVDs){
            if (dvd.getMpaaRating().equals(mpaaRating)){
                dvdsWithCorrectRating.add(dvd);
            }
        }
        
        return dvdsWithCorrectRating;
        
        
        
//        return listAllDVDs().stream()
//                .filter(dvd -> dvd.getMpaaRating().equals(mpaaRating))
//                .collect(Collectors.toList());
    } 
    @Override
    public Map<String, List<DVD>> findAllMoviesByGivenDirector(String director) throws DVDLibraryPersistenceException {
        
        List<DVD> allDVDs = listAllDVDs();
        Map<String, List<DVD>> directorMoviesByRating = new HashMap();
        List<DVD> dvdsByDirector = new ArrayList();
        List<String> differentRatings = new ArrayList();
        
        for (DVD currentDVD : allDVDs){
            if (currentDVD.getDirectorName().equals(director)){
                dvdsByDirector.add(currentDVD);
            }
        }
        
        for (DVD currentDVD : dvdsByDirector){
            List<DVD> possibleNewList = new ArrayList();
            if (directorMoviesByRating.get(currentDVD.getMpaaRating()) == null){
                possibleNewList.add(currentDVD);
                directorMoviesByRating.put(currentDVD.getMpaaRating(), possibleNewList);
            } else{
                List<DVD> populatedList = directorMoviesByRating.get(currentDVD.getMpaaRating());
                populatedList.add(currentDVD);
                directorMoviesByRating.replace(currentDVD.getMpaaRating(), populatedList);
            }
        }
        
        return directorMoviesByRating;
        
//        return listAllDVDs().stream()
//                .filter(dvd -> dvd.getDirectorName().equals(director))
//                .collect(Collectors.groupingBy(DVD::getMpaaRating));
    }
    @Override
    public List<DVD> findAllMoviesByGivenStudio(String studio) throws DVDLibraryPersistenceException{
        
        List<DVD> allDVDs = listAllDVDs();
        List<DVD> dvdsWithCorrectStudio = new ArrayList();
        
        
        for (DVD dvd : allDVDs){
            if (dvd.getStudio().equals(studio)){
                dvdsWithCorrectStudio.add(dvd);
            }
        }
        
        return dvdsWithCorrectStudio;
        
        
//        return listAllDVDs().stream()
//                .filter(dvd -> dvd.getStudio().equals(studio))
//                .collect(Collectors.toList());
    }
    
    @Override
    public DVD addNoteToDVD(String title, String note) throws DVDLibraryPersistenceException{
        DVD currentDVD = getDVDByTitle(title);
        currentDVD.getNotes().add(note);
        return currentDVD;
    }
    
    
}
