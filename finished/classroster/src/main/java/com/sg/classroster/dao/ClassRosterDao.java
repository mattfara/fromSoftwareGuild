/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDuplicateIdException;
import java.util.List;

/**
 *
 * @author matt
 */
public interface ClassRosterDao {
    //method defs only -- just
    //public is implied by the contract
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException,ClassRosterDuplicateIdException;
    public List<Student> getAllStudents() throws ClassRosterPersistenceException,ClassRosterDuplicateIdException;
    public Student getStudent(String studentId) throws ClassRosterPersistenceException,ClassRosterDuplicateIdException;
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException;
    
    
    
}
