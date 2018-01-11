/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

/**
 *
 * @author matt
 */
import com.sg.dvdlibrary.dto.DVD;
import static java.sql.DriverManager.println;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//for wiring app
public class DVDLibraryView {
UserIO io;

public DVDLibraryView(UserIO io){
    this.io=io;
}




public int printMenuGetSelection() {
        io.println("*********************************");
        io.println("******  >  Main Menu  <  ********");
        io.println("*********************************");
        io.println("1. Add DVD to Collection");
        io.println("2. Remove DVD from Collection");
        io.println("3. Edit existing DVD's info");
        io.println("4. List all DVDs in Collection");
        io.println("5. Show info for selected DVD");
        io.println("6. Search for DVD by title");
        io.println("7. Exit");
        io.println("");
        int menuSelection = io.readInt("Please select from the above choices: ",1,9);
        io.println("*********************************");
        io.println("*********************************");
        return menuSelection;
    }
    
    
    
    
    public void searchByTitle(String id, DVD currentDVD){
        String stringId = currentDVD.getId();
        io.println("DVD ID#...Title...MPAA Rating...Your Notes");
        io.println(stringId + " | " + currentDVD.getTitle() + " " + currentDVD.getMpaaRating() + " " + currentDVD.getNote());
        
    }
    
    
    
    
    
    public DVD getNewDVDInfo() {
        boolean validRating = false;
        String title = io.readString("Please enter the title of the film: ");
        String mpaaRating = "";
        String releaseDate = io.readString("Please enter the full release date: ");
        //rating validation
        while (!validRating){
            displayMPAARatings();
            mpaaRating = io.readString("Please enter an MPAA rating: ");
            if (isRatingValid(mpaaRating)){
                validRating=true;
            } else {
                io.println("");
                io.println("You did not enter a valid rating");                
                io.println("");
            }
        }//end rating validation
        
        String directorName = io.readString("Please enter the director's name: ");
        String studio = io.readString("Please enter the studio that produced this movie: ");
        String note = io.readString("Please enter any comments you have about the movie: ");
        
        DVD currentDVD = new DVD();
        
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setNote(note);
        
        return currentDVD;
    }
    
    
    
    public void listAllDVDs(List<DVD> dvdList){
        if (dvdList.size()>0){
            for (DVD currentDVD: dvdList){
                io.println("DVD ID# " + currentDVD.getId() + " : " +currentDVD.getTitle()+ ", directed by " + currentDVD.getDirectorName());
                io.println("Your notes: " + currentDVD.getNote());
                
            }
        } else {
            io.println("");
            io.println("Your Collection is empty");
            io.println("");
        }   
        io.println("");
        io.readString("Press ENTER to continue");
        io.println("");
    }
    
    
    
    public String getDVDTitle(){
        io.println("");
        return io.readString("Please enter a movie title: ");
    }
    
    public void displayDVD(DVD dvd) {
	if (dvd != null) {
            io.println("#####################################");
            io.println("#####################################");
            io.println(dvd.getStudio() + " presents....");
            io.println("***"+dvd.getTitle()+"***");
            io.println("Rated " + dvd.getMpaaRating());
            io.println("---------------------");
	    io.println("directed by " + dvd.getDirectorName()+ ", released on " + dvd.getReleaseDate());
	    io.println("Your notes: " + dvd.getNote());
            io.println("#####################################");
            io.println("#####################################");
	    io.println("");
	} else {
	    io.println("No such DVD");
	}
	    io.readString("Please hit enter to continue.");
            io.println("");
    }
    
    
    
    
    
    public int displayFieldMenuForDVDandChoose(){
        int infoSelection;
        
        io.println("");
        io.println("What information about the DVD would you like to edit?");
        io.println("");
        
        io.println("----Choose a Number----");
        io.println("1. Title");
        io.println("2. Release Date");
        io.println("3. Director");
        io.println("4. Studio");
        io.println("5. MPAA Rating");
        io.println("6. Notes");
        io.println("");
        
        infoSelection = io.readInt("Enter a Number: ");
        return infoSelection;
        
    }
    
    //Change info methods
    
    public void changeTitle(DVD dvd){
        
        String newTitle = io.readString("Enter a new title with (Year): ");
        dvd.setTitle(newTitle);
        io.readString("Title successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    public void changeReleaseDate(DVD dvd){
        String releaseDate = io.readString("Please write a new release date: ");
        dvd.setReleaseDate(releaseDate);
        io.readString("Release date successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    
    public void changeMPAARating(DVD dvd){
        boolean validRating=false;
        String mpaaRating = "";
        
        while (!validRating){
            displayMPAARatings();
            mpaaRating = io.readString("Please enter an MPAA rating: ");
            if (isRatingValid(mpaaRating)){
                validRating=true;
            } else {
                io.println("");
                io.println("You did not enter a valid rating");                
                io.println("");
            }
        }
        
        dvd.setMpaaRating(mpaaRating);
        io.readString("MPAA rating successfully changed. Press ENTER to continue");
        io.println("");
    
    }
    
    public void changeDirectorName(DVD dvd){
        String director = io.readString("Please enter director's name: ");
        dvd.setDirectorName(director);
        io.readString("Director name successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    public void changeStudio(DVD dvd){
        String studio = io.readString("Please enter studio: ");
        dvd.setStudio(studio);
        io.readString("Studio successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    public void changeNote(DVD dvd){
        String note = io.readString("Please enter a new note: ");
        dvd.setNote(note);
        io.readString("Note successfully changed. Press ENTER to continue");
        io.println("");
    }

    public String getId(){
        return io.readString("Enter the ID # of the DVD you want to remove: ");
    }

    
    //misc methods
    
    //validation
    public boolean keepChanging(){
        String choice = io.readString("Do you want to continue changing info for this DVD? (y)");
        return choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes");
    }
    
    public boolean isRatingValid(String mpaaRating){
        return (mpaaRating.equalsIgnoreCase("g") || mpaaRating.equalsIgnoreCase("pg") || 
                    mpaaRating.equalsIgnoreCase("pg-13") || mpaaRating.equalsIgnoreCase("r") 
                    || mpaaRating.equalsIgnoreCase("nc-17"));
                    
    }
    
    //banners
    
    public void displayDisplayAllDVDs(){
        io.println("######################");
        io.println("   Display All DVDs   ");
        io.println("######################");
    }
    
    public void displayDisplayOneDVD(){
        io.println("");
        io.println("===DVD Info===");
        io.println("");
    }
    
    public void displayEditDVDBanner(){
        io.println("");
        io.println("###   Edit DVD Info  ###");
        io.println("");
    }
    
    public void displaySuccessfullyEditedDVD(){
        io.println("");
        io.println("DVD Successfully edited");
        io.println("");
    }  
    
    public void displayDVDDoesNotExist(){
        io.println("That DVD does not exist");
    }
    
    public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
    }
    
    public void displayDisplayRemoveDVD(){
        io.println("");
        io.println("===Remove DVD===");
        io.println("");
    }
    
    public void displaySuccessfullyRemovedDVD(){
        io.println("");
        io.readString("DVD successfully removed. Press ENTER to continue");
        io.println("");
    }
    
    public void printArt(){
        io.println("  ______     ______        _        \n" +
" |  _ \\ \\   / /  _ \\  __ _| |_ __ _ \n" +
" | | | \\ \\ / /| | | |/ _` | __/ _` |\n" +
" | |_| |\\ V / | |_| | (_| | || (_| |\n" +
" |____/  \\_/  |____/ \\__,_|\\__\\__,_|\n" +
"                                    ");
    
    }

    public void showOpeningPage(){
        printArt();
        io.println("");
        io.println("Welcome to DVData");
        io.println("Probably the last DVD library you'll ever use....");
        io.readString("Press ENTER to continue");
        io.println("");
    }

    public void showClosingPage(){
        printArt();
        io.println("Closing DVData -- if you don't come back, I'll understand....");
    }
    
    public void printUknownCommand(){
        io.println("Uknown command - Please Try again");
    }


    public void displayAddDVDBanner() {
        io.println("");
        io.println("***Add a DVD to the Collection***");
        io.println("");
    }
    
    public void displaySearchByTitleBanner(){
        io.println("");
        io.println("===Title Search===");
        io.println("");
    }
    
    public void displayMPAARatings(){
        io.println("");
        io.println("===MPAA Ratings===");
        io.println(" G");
        io.println(" PG");
        io.println(" PG-13");
        io.println(" R");
        io.println(" NC-17");
        io.println("");
    }
    
    public void displayCreateDVDBanner(){
        io.println("");
        io.println("+++Add a DVD+++");
        io.println("===============");        
        io.println("");
    }
    
    public void displaySuccessFullyAddedDVD(){
        io.println("");
        io.readString("You successfully added this DVD. Press ENTER to continue");
        io.println("");
    }
}
