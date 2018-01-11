/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import java.util.List;
import java.util.Set;

/**
 *
 * @author matt
 */
public class DVDLibraryController {
    
    private DVDLibraryView view;
    private DVDLibraryServiceLayer service;
    
    //for wiring the app
    public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view){
        this.service=service;
        this.view=view;
    }
    
    public void run() {    
        
        boolean keepUsing = true;
        
        view.showOpeningPage();
        
        try{
            while (keepUsing){

                int menuSelection = view.printMenuGetSelection();

                switch (menuSelection){

                    case 1: addDVD();
                        break;
                    case 2: removeDVD();
                        break;
                    case 3: editDVDInfo();   
                        break;
                    case 4: listAllDVDs();
                        break;
                    case 5: showInfoForDVD();
                        break;
                    case 6: searchByTitle();
                        break;
                    case 7: keepUsing = false;
                        break;
                    default: view.printUknownCommand();
                        break;
                }
            }
            view.showClosingPage();
        } catch(DVDLibraryPersistenceException | DVDLibraryDataValidationException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    
    private void addDVD() throws DVDLibraryPersistenceException, DVDLibraryDataValidationException {
        view.displayAddDVDBanner();
        boolean hasErrors = false;
        do {
            DVD newDVD = view.getNewDVDInfo();
            try {
                service.createDVD(newDVD.getTitle(), newDVD);
                view.displaySuccessFullyAddedDVD();
                hasErrors=false;
            } catch(DVDLibraryPersistenceException | DVDLibraryDataValidationException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }
    
    
    
    private void removeDVD() throws DVDLibraryPersistenceException{
        boolean dvdExists = false;
        
        view.displayDisplayRemoveDVD();
        view.displayQuitMessage();
        while(!dvdExists){
            String title = view.askForTitle();
            if (title.equalsIgnoreCase("m")){
                break;
            }
            if (service.getKeys().contains(title)){
                dvdExists = true;
                service.removeDVD(title);
                view.displaySuccessfullyRemovedDVD();
            } else {
                view.displayDVDDoesNotExist();
            }       
        }   
    }
    
    private void editDVDInfo() throws DVDLibraryPersistenceException {
        boolean keepChanging = true;
        int choice = 0;
        DVD dvd;
        
        
        view.displayEditDVDBanner();
        String title = view.askForTitle();
        while (keepChanging){
            if (service.doesTitleExist(title)){
                dvd = service.getDVDByTitle(title);
                view.displayDVD(dvd);
            
                choice = view.displayFieldMenuForDVDandChoose();
            
                switch (choice){
                    case 1: changeTitle(dvd, title);
                            break;
                    case 2: view.changeReleaseDate(dvd);
                            break;
                    case 3: view.changeDirectorName(dvd);
                            break;
                    case 4: view.changeStudio(dvd);
                            break;
                    case 5: view.changeMPAARating(dvd);
                            break;
                    case 6: view.getAndChangeNote(dvd);
                            break;
                    default: keepChanging=false;
                            break;
                }
                
                if (!view.keepChanging()){
                    keepChanging=false;
                    view.displaySuccessfullyEditedDVD();
                }
            } else {
                view.displayDVDDoesNotExist();
                keepChanging=false;
            }
        }
         //maybe go back and encapsulate these better, and above in switch
    }

    
    private void showInfoForDVD() throws DVDLibraryPersistenceException{
        view.displayDisplayOneDVD();
        String title = view.askForTitle();
        DVD dvd = service.getDVDByTitle(title);
        view.displayDVD(dvd);
    }

    private void searchByTitle() throws DVDLibraryPersistenceException{
        view.displaySearchByTitleBanner();
        Set<String> titles = service.getKeys();
        view.searchByTitle(titles);
    }

    private void listAllDVDs() throws DVDLibraryPersistenceException{
        view.displayDisplayAllDVDs();
        List<DVD> dvdList = service.listAllDvds();
        view.listAllDVDs(dvdList);
    }
    
    private void changeTitle(DVD dvd, String oldTitle) throws DVDLibraryPersistenceException{
        service.changeTitle(view.changeTitle(dvd), oldTitle);
    }
}
