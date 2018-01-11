/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

/**
 *
 * @author matt
 */
import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author matt
 */
public class App {
    public static void main(String[] args) {
        // wiring the app
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        
        controller.run();
    }
}
