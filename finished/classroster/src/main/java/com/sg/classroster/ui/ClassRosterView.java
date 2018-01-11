/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author matt
 */
public class ClassRosterView {
    Scanner sc = new Scanner(System.in);
    private UserIO io;
    
    public ClassRosterView(UserIO io) {
        this.io = io;
    }
    
            
    public int printMenuAndGetSelection() {
    //note that my userIO is different for print and println
        io.println("Main Menu");
        io.println("1. List Students");
        io.println("2. Create New Student");
        io.println("3. View a Student");
        io.println("4. Remove Student");
        io.println("5. Exit");
        int menuSelection = io.readInt("Please select from the above choices ",1,5);
        return menuSelection;
    }
    
    public void printUknownCommand(){
        io.println("Uknown command - Please Try again");
    }
    
    public void displayCreateStudentBanner(){
        io.println("**** Create Student ****");
        
    }

    public Student getNewStudentInfo() {
        
        String studentID = io.readString("Please enter student ID");
        String firstName = io.readString("Please enter student's first name");
        String lastName = io.readString("Please enter student's last name");
        String cohort = io.readString("Please enter student's cohort");
        
        //use setters to remove ambiguity from this process -- a constructor is harder to use
        
        Student enteredStudent = new Student();
        
        enteredStudent.setFirstName(firstName);
        enteredStudent.setLastName(lastName);
        enteredStudent.setCohort(cohort);
        enteredStudent.setStudentId(studentID);
        
        io.println("");
        
        return enteredStudent;
        
                
    }

    
    
    public void displayCreateStudentSuccessBanner() {
        io.println("");
        io.println("Student successfully added");
        io.println("");
    }
    
    public void displayDisplayStudentBanner () {
	    io.print("=== Display Student ===");
	}

    public String getStudentIDChoice() {
        return io.readString("Please enter the Student ID.");
    }

    public void displayStudent(Student student) {
	if (student != null) {
	    io.println(student.getStudentID());
            io.println("");
	    io.println(student.getFirstName() + " " + student.getLastName());
            io.println("");
	    io.println(student.getCohort());
	    io.print("");
	} else {
	    io.print("No such student.");
	}
	    io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveStudentBanner () {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveSuccessBanner () {
        io.readString("Student successfully removed. Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }   

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displayStudentList(List<Student> studentList) {
	    for (Student currentStudent : studentList) {
	        io.println(currentStudent.getStudentID() + ": "
	            + currentStudent.getFirstName() + " "
	            + currentStudent.getLastName() + " " + currentStudent.getCohort());
            }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
	    io.print("=== Display All Students ===");
	}
}
