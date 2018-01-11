/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author matt
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    //dependency injection
    private ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;
    
    
    
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
        
    @Override
public void createStudent(Student student) 
        throws ClassRosterDuplicateIdException, 
        ClassRosterDataValidationException, 
        ClassRosterPersistenceException {
    // First check to see if there is alreay a student associated 
    // with the given student's id If so, we're all done here - 
    // throw a ClassRosterDuplicateIdException
    if (dao.getStudent(student.getStudentID()) != null) {
        throw new ClassRosterDuplicateIdException (
                "ERROR: Could not create student.  Student Id " + 
                student.getStudentID() + " already exists");
    }

    // Now validate all the fields on the given Student object.  
    // This method will throw an exception if any of the validation 
    // rules are violated.
    validateStudentData(student);

    // We passed all our business rules checks so go ahead and 
    // persist the Student object
    dao.addStudent(student.getStudentID(), student);

    // The student was successfully created, now write to the audit 
    // log auditDao.writeAuditEntry(
    //     "Student " + student.getStudentID() + " CREATED.");
}

@Override
public Student removeStudent(String studentID) 
        throws ClassRosterPersistenceException {
    Student removedStudent =  dao.removeStudent(studentID);
    // Write to audit log
    //auditDao.writeAuditEntry(
    //    "Student " + studentID + " REMOVED.");
    return removedStudent;
}
    
    //rule -- all fields must be filled for a student object
    //null and empty are not the same
    private void validateStudentData(Student student) throws ClassRosterDataValidationException {
        if (student.getFirstName() == null || student.getFirstName().trim().length()== 0 || student.getLastName() == null || student.getLastName().trim().length()==0 || student.getCohort() == null || student.getCohort().trim().length()==0){
                throw new ClassRosterDataValidationException("ERROR: All fields [First name, Last name, cohort] are required");
        }
    }   

    @Override
    public List<Student> getAllStudents() throws ClassRosterDuplicateIdException, ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterDuplicateIdException, ClassRosterPersistenceException {
        return dao.getStudent(studentID);
    }
    
}
