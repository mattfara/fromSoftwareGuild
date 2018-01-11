/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.review.one.testingcode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public class TestingWriter {
    
    private Map<String, DVD> dvds = new HashMap();
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    public static final String LIST_DELIMITER = ";;";
    
    public static void main(String[] args) {
        
    }
    
    private void writeLibrary() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new Exception(
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
