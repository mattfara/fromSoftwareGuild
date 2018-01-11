/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.addressbook.service;

/**
 *
 * @author matt
 */
public class PersonService {
    //this PersonService layer should handle all the daos
    //this should allow for populating the bridge table from the component tables
    
    //eg a person and address are added
    //bridge table sticks in their respective ids
    
    //eg a person is created but no address
    //address is created but no person
    
    //should be able to update automatically as well
    
    //need a new method, like associateNewPersonWithExistingAddress()?
        //steps
            //add the Person and get the id
            //get the existing address's id
            //
    
    //person_address
    //need a method to associate two objects together in the bridge table
    //so should I have the three objects in here to access their methods?
    
    
    
}
