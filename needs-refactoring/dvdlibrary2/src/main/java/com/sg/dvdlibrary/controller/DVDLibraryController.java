/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author matt
 */
public class DVDLibraryController {
    
    DVDLibraryView view;
    DVDLibraryDao dao;
    
    //for wiring the app
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view){
        this.dao=dao;
        this.view=view;
    }
    
    //throws DVDLibraryDaoException had this after run before, but going to try the try-catch here
    public void run() {    
        
        boolean keepUsing = true;
        
        showOpeningScreen();
        try{
            while (keepUsing){

                int menuSelection = getMenuSelection();

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
                    default: printUknownCommand();
                        break;
                }
            }
            showExitScreen();
        } catch(DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    //use case methods
    
    private void addDVD() throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getId(), newDVD);
        view.displaySuccessFullyAddedDVD();
    }

    private void removeDVD() throws DVDLibraryDaoException{
        view.displayDisplayRemoveDVD();
        String id = view.getId();
        dao.removeDVD(id);
        view.displaySuccessfullyRemovedDVD();
    }
    //think that the exception throwing needs to be added to these composite methods,
    //since they call methods that can throw the exception, they too can throw the same
    //exception
    private void editDVDInfo() throws DVDLibraryDaoException {
        boolean keepChanging = true;
        boolean dvdExists = false;
        int choice = 0;
        String id = "";
        DVD dvd;
        
        //make method?
        displayEditDVDBanner();
        while (!dvdExists){
            id = view.getId();
            if (dao.hasId(id)){
                dvdExists = true;
            } else {
                displayDVDDoesNotExist();
            }
        }
        
        dvd = dao.showInfoForDVD(id);
        view.displayDVD(dvd);
        
        //make method?
        while (keepChanging){
            
            choice = view.displayFieldMenuForDVDandChoose();
            
            switch (choice){
                case 1: changeTitle(dvd);
                        break;
                case 2: changeReleaseDate(dvd);
                        break;
                case 3: changeDirectorName(dvd);
                        break;
                case 4: changeStudio(dvd);
                        break;
                case 5: changeMPAARating(dvd);
                        break;
                case 6: changeNote(dvd);
                        break;
                default: printUknownCommand();
                        break;
            }
                //sequestered helper method -- double check logic
            if (!keepChanging()){
                keepChanging=false;
            }
        }
        displaySuccessfullyEditedDVD(); //maybe go back and encapsulate these better, and above in switch
    }

    
    private void showInfoForDVD() throws DVDLibraryDaoException{
        view.displayDisplayOneDVD();
        String id = view.getId();
        DVD dvd = dao.showInfoForDVD(id);
        view.displayDVD(dvd);
    }

    private void searchByTitle() throws DVDLibraryDaoException{
        
        
        view.displaySearchByTitleBanner();
        String search = view.getDVDTitle();
        Set<String> ids = dao.getIds();
        for (String currentId : ids){
            DVD currentDVD = dao.showInfoForDVD(currentId);
            String currentTitle = currentDVD.getTitle();
            if (currentTitle.contains(search)){
                //need view here
                view.searchByTitle(currentId, currentDVD);                
            }
        }
        
    }

    private void listAllDVDs() throws DVDLibraryDaoException{
        view.displayDisplayAllDVDs();
        List<DVD> dvdList = dao.listAllDVDs();
        view.listAllDVDs(dvdList);
    }

    
    //edit DVD info methods
    //can these be cobbled together from pre-existing methods, or other methods made 
    //that could be reused in this process?
    private void changeTitle(DVD dvd) throws DVDLibraryDaoException{
        view.changeTitle(dvd);
    }
    
    private void changeReleaseDate(DVD dvd){
        view.changeReleaseDate(dvd);
    }

    private void changeDirectorName(DVD dvd){
        view.changeDirectorName(dvd);
    }
    
    private void changeStudio(DVD dvd){
        view.changeStudio(dvd);
    }
    private void changeMPAARating(DVD dvd){
        view.changeMPAARating(dvd);
    }
    private void changeNote(DVD dvd){
        view.changeNote(dvd);
    }
    
    //misc methods
    //mostly wrappers for methods in the view
    
    private void printUknownCommand(){
        view.printUknownCommand();
    }
    
    public void showOpeningScreen(){
        view.showOpeningPage();
    }
    
    public int getMenuSelection(){
        return view.printMenuGetSelection();
    }
    
    private void showExitScreen() {
        view.showClosingPage();
    }
    
    private boolean keepChanging(){
        return view.keepChanging();
    }
    
    private void displaySuccessfullyEditedDVD(){
        view.displaySuccessfullyEditedDVD();
    }
    
    private void displayEditDVDBanner(){
        view.displayEditDVDBanner();
    }
    
    private void displayDVDDoesNotExist(){
        view.displayDVDDoesNotExist();
    }
}
