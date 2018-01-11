/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook;

import com.tsguild.basics.addressbook.controller.AddressBookController;
import com.tsguild.basics.addressbook.dao.AddressBookDao;
import com.tsguild.basics.addressbook.dao.AddressBookDaoFileImpl;
import com.tsguild.basics.addressbook.ui.AddressBookView;
import com.tsguild.basics.addressbook.ui.UserIO;
import com.tsguild.basics.addressbook.ui.UserIOConsoleImpl;

/**
 *
 * @author matt
 */
public class App {
    public static void main(String[] args) {
        //wiring the app/dependency injection
        UserIO myIo = new UserIOConsoleImpl();
        AddressBookDao myDao = new AddressBookDaoFileImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookController controller = new AddressBookController(myDao, myView);
        controller.run();
    }
}
