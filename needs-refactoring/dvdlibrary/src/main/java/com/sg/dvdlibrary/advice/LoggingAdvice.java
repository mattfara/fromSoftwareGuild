/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.advice;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author matt
 */
public class LoggingAdvice {
    //relevant class for advice
    DVDLibraryAuditDao auditDao;
 
    //constructor
    public LoggingAdvice(DVDLibraryAuditDao auditDao) {
        this.auditDao = auditDao;
    }
 
    //method
    public void createAuditEntry(JoinPoint jp) {
        //make an array of objects called args
        //fill it with the arguments passed into createAuditEntry method
        Object[] args = jp.getArgs();
        //make a string called audit entry
        // set it to the name of the signature of the method that calls joinpoin
        String auditEntry = jp.getSignature().getName() + ": ";
        //add each argument of the method to that string
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        //try to write that string to the audit log
        try {
            auditDao.writeAuditEntry(auditEntry);
        //otherwise throw the error
        } catch (DVDLibraryPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}