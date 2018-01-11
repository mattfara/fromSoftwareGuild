/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDataValidationException;
import com.sg.classroster.service.ClassRosterDuplicateIdException;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.ui.ClassRosterView;
import java.util.List;

/**
 *
 * @author matt
 */
public class ClassRosterController {
    ClassRosterServiceLayer service;
    ClassRosterView view;
    
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }
    
public void run() {
	    boolean keepGoing = true;
	    int menuSelection = 0;
	    try {
	        while (keepGoing) {

	            menuSelection = getMenuSelection();

	            switch (menuSelection) {
	                case 1:
	                    listStudents();
	                    break;
	                case 2:
	                    createStudent();
	                    break;
	                case 3:
	                    viewStudent();
	                    break;
	                case 4:
	                    removeStudent();
	                    break;
	                case 5:
	                    keepGoing = false;
	                    break;
	                default:
	                    unknownCommand();
                }

        }
            exitMessage();
        } catch (ClassRosterPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        } catch (ClassRosterDuplicateIdException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    
    //these should all be private to prevent other classes from calling them -- the controller has control
    private void listStudents() throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    //the view method reddened because the method isn't in the view?
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        
        do {
            Student newStudent = view.getNewStudentInfo();
            try {
                service.createStudent(newStudent);
                view.displayCreateStudentSuccessBanner();
                hasErrors=false;
            } catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException e){
                hasErrors=true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors); 
    }
    
    private void viewStudent() throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
        view.displayDisplayStudentBanner();
        String studentID = view.getStudentIDChoice();
        Student student = service.getStudent(studentID);
        view.displayStudent(student);
    }
    
    private void removeStudent() throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
        view.displayRemoveStudentBanner();
        String studentID = view.getStudentIDChoice();
        service.removeStudent(studentID);
        view.displayRemoveSuccessBanner();
    }

    private void quit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
}
