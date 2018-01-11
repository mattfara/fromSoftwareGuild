//NOTE: made mistake in change/add note here -- wrong!


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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

//for wiring app
public class DVDLibraryView {
    UserIO io;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
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
        int menuSelection = io.readInt("Please select from the above choices: ",1,7);
        io.println("*********************************");
        io.println("*********************************");
        return menuSelection;
    }
    
    
    
    public void searchByTitle(Set<String> dvdTitles){
        String search = io.readString("Enter the (partial) title of the DVD you are looking for: ");
        io.println("The following titles match your search: ");
        for (String currentTitle : dvdTitles){
            if (currentTitle.contains(search)){
                io.println(currentTitle);
            }
        }
    }
    
    
    
    
    
    public DVD getNewDVDInfo() {
        
        boolean keepAddingNotes = true;
        
        String title = getAndCheckTitle();
        LocalDate releaseDate = io.readLocalDate("Please enter the full release date: ");
        String mpaaRating = getAndCheckRating();
        String directorName = io.readString("Please enter the director's name: ");
        String studio = io.readString("Please enter the studio that produced this movie: ");
        
        
        DVD currentDVD = new DVD();
        
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        
        while (keepAddingNotes){
            String note = io.readString("Please enter any comments you have about the movie: ");
            currentDVD.addNote(note);
            String wantToKeepUsing = io.readString("Do you want to continue entering notes? (y/n)");
            if (wantToKeepUsing.equalsIgnoreCase("n") || wantToKeepUsing.equalsIgnoreCase("no")){
                keepAddingNotes=false;
            }
        }
            return currentDVD;
    }
    
    //need to fix this
    public void listAllDVDs(List<DVD> dvdList){
        int counter = 1;
        
        if (dvdList.size()>0){
            for (DVD currentDVD: dvdList){
                io.println("DVD #" + counter+ " : " +currentDVD.getTitle()+ ", directed by " + currentDVD.getDirectorName());
                
                io.println("Your notes: " + currentDVD.getNotes());
                counter++;
            }
        } else {
            io.println("Your Collection is empty");
        }    
        io.readString("Press ENTER to continue");
    }
    
    
    
    public String askForTitle(){
        return io.readString("Please enter a movie title: ");
    }
    
    public void displayDVD(DVD dvd) {
	if (dvd != null) {
            io.println("#####################################");
            io.println("#####################################");
            io.println(dvd.getStudio() + " presents....");
            io.println("*** "+dvd.getTitle()+" ***");
            io.println("Rated " + dvd.getMpaaRating());
            io.println("---------------------");
	    io.println("directed by " + dvd.getDirectorName()+ ", released on " + dvd.getReleaseDate());
	    //need a for loop to print each note
            io.println("Your notes: ");
            for (String currentNote : dvd.getNotes()){
                io.println(currentNote);
            }
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
    
    
    //getCheck and Change methods
    
    public String getAndCheckTitle(){
        boolean validTitle = false;
        String newTitle = "";
        String extracted = "";
        
        while (!validTitle){
            newTitle = io.readString("Please correct title with (Year): ");
            if (newTitle.contains("(") && newTitle.contains(")")){
                extracted = newTitle.substring(newTitle.indexOf("(")+1, newTitle.lastIndexOf(")"));
                try {
                    Integer.parseInt(extracted);
                } catch(NumberFormatException e){
                    io.println("Your year contains something other than a number");
                } catch(NullPointerException e){
                    io.println("You didn't enter anything");
                }
                validTitle = true;
            } else {
                io.println("Your title lacks a (year)");
            }
        }
        return newTitle;
    }
    
    public String getAndCheckRating(){
        boolean validRating=false;
        
        String mpaaRating = io.readString("Please enter an MPAA rating: ").toUpperCase();
        while (!validRating){
            if (mpaaRating.equalsIgnoreCase("g") || 
                    mpaaRating.equalsIgnoreCase("pg") || 
                    mpaaRating.equalsIgnoreCase("pg-13") || 
                    mpaaRating.equalsIgnoreCase("r") || 
                    mpaaRating.equalsIgnoreCase("nc-17")){
                validRating=true;
            } else {
                io.println("");
                io.println("You did not enter a valid rating");                
                io.println("");
                mpaaRating = io.readString("Please enter an MPAA rating: ").toUpperCase();
            }
        }
        return mpaaRating;
    }
    
    
    public String changeTitle(DVD dvd){
        
        String newTitle = getAndCheckTitle();
        io.println("Title successfully changed");
        io.println("");
        return newTitle;
    }
    
    public void changeReleaseDate(DVD dvd){
        LocalDate releaseDate = io.readLocalDate("Please correct new release date: ");
        dvd.setReleaseDate(releaseDate);
        io.readString("Release date successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    
    public void changeMPAARating(DVD dvd){
        String mpaaRating = getAndCheckRating();
        dvd.setMpaaRating(mpaaRating);
        io.readString("MPAA rating successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    public void changeDirectorName(DVD dvd){
        String director = io.readString("Please correct director's name: ");
        dvd.setDirectorName(director);
        io.readString("Director name successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    public void changeStudio(DVD dvd){
        String studio = io.readString("Please correct studio: ");
        dvd.setStudio(studio);
        io.readString("Studio successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    public void showAllNotes(DVD dvd){
        List<String> allNotes = dvd.getNotes();
        for (int currentNoteIdx=0; currentNoteIdx<allNotes.size();currentNoteIdx++){
            io.println((currentNoteIdx+1) + " : " + allNotes.get(currentNoteIdx));
        }
    }
    
    public void getAndChangeNote(DVD dvd){
        
        List<String> allNotes = dvd.getNotes();
        
        int noteChoice = io.readInt("Please enter note # to edit: ", 1,allNotes.size());
                
        String noteToEdit = allNotes.get(noteChoice-1);
        
        String newNote = io.readString("Enter the new note here: ");
        
        allNotes.remove(noteChoice-1);
        allNotes.add(newNote);
        
        dvd.setNote(allNotes);
        
        io.readString("Note successfully changed. Press ENTER to continue");
        io.println("");
    }
    
    public boolean keepChanging(){
        String choice = io.readString("Do you want to continue changing info for this DVD? (y)");
        return choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes");
    }
    
    //misc methods
    
    
    
    
    //banners, error messages
    
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
    
    public void displayQuitMessage(){
        io.println("Enter 'M' to return to main menu");
    }
    
}
