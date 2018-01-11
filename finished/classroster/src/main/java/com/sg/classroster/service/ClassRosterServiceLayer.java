/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author matt
 */
public interface ClassRosterServiceLayer {
//this will repeat the same user case methods we had earlier
    
    
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException;
    //pass through methods
    public List<Student> getAllStudents() throws ClassRosterDuplicateIdException,ClassRosterPersistenceException;
    public Student getStudent(String studentId) throws ClassRosterDuplicateIdException,ClassRosterPersistenceException;
    
    //this one is not a pass through, since it involves more functionality than the dao version
    public Student removeStudent(String studentId) throws ClassRosterDuplicateIdException, ClassRosterPersistenceException;
    
    
    
}
