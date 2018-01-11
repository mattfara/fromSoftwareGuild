/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDuplicateIdException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao{

    Student onlyStudent;
    List<Student> studentList = new ArrayList();
    
    public ClassRosterDaoStubImpl(){
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("John");
        onlyStudent.setLastName("Doe");
        onlyStudent.setCohort("Java-Jan-2015");
        
        studentList.add(onlyStudent);
    }
    
    
    @Override
    public Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
        if (studentID.equals(onlyStudent.getStudentID())){
            return onlyStudent;
        } else {return null;}
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
        return studentList;
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterPersistenceException, ClassRosterDuplicateIdException {
        if (studentID.equals(onlyStudent.getStudentID())){
            return onlyStudent;
        } else {return null;}
    }

    @Override
    public Student removeStudent(String studentID) throws ClassRosterPersistenceException{
        if (studentID.equals(onlyStudent.getStudentID())){
            return onlyStudent;
        } else {return null;}
    }
    
}
