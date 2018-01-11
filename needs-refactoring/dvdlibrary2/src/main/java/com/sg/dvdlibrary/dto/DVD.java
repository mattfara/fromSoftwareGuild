/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 *
 * @author matt
 */
public class DVD {
    
    public static int dvdCount = 0;
    
    private String id;
    private String title; //use as key in hashmap?
    private String releaseDate; //formatting of this? MM-DD-YYYY
    private String mpaaRating; //offer menu for this? yes, since there are predefined choices
    private String directorName; //nothing special for this
    private String studio; //nothing special for this
    private String note; //nothing special for this

    //added this explicity default constructor to allow me to instantiate new DVDs in the View, line 168
    public DVD(){
        //dvdCount++;
        this.id = Integer.toString(dvdCount);
    }
    
    //this is what the readLibrary uses
    //shouildn't need the dvdCount++ here
    //since it only repeats what the no-arg one
    //does
    public DVD(String currentToken) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //not sure how to use this constructor
        dvdCount++;
        this.id=currentToken;//currentTokens[0] refers to the id in my case....
    }
    
    //don't think i need this
    public static int getDvdCount(){
        return dvdCount;
    }
    
    public String getId(){
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
