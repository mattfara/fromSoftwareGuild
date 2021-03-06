/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

*/
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDaoFileImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDaoLambdaImpl;
import com.sg.dvdlibrary.service.DVDLibraryServerLayerLambdaImpl;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayerImpl;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author matt
 */

public class App {
    public static void main(String[] args) {
        //stating what has what here, allowing for composition later and for selecting
        //specific implementation for project
        
        
        UserIO myIo = new UserIOConsoleImpl(); 
        DVDLibraryDao myDao = new DVDLibraryDaoLambdaImpl();
        DVDLibraryAuditDao myAuditDao = new DVDLibraryAuditDaoFileImpl();
        DVDLibraryServiceLayer myService = new DVDLibraryServerLayerLambdaImpl(myDao, myAuditDao);
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryController controller = new DVDLibraryController(myService, myView);
        //starting the program
        controller.run();
    }
}













