/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GradeBook;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import talkingclasses.UserIO;
import talkingclasses.UserIOImpl;

/**
 *
 * @author matt
 */

//I think this is where the additions, calculations, etc should go
public class GradeBook {
    //properties
    UserIO io = new UserIOImpl();
    static Map<Integer, Student> book = new HashMap<>();
    //store all new students in a hashmap with ID# as key, Student obj as value -- guarantees unique identifier for every student
    
    
    
    //methods

    
    public static void addStudent(String studentName){
        Student newStudent = new Student(studentName);
        book.put(Student.getIdNumber(), newStudent);
        
        
        //String name = io.readString("What is the student's name?");
        //List<Integer> scores = addQuizGradesForStudent();
        //students.add(new Student())
    }
    
    public boolean isStudentNameUsed(String studentName){
        Collection<Student> students = book.values();
        for (Student student : students){
            return student.getName().equals(studentName);
        }
        return false;
    }
    
    public boolean isListEmpty(){
        return book.size()>0;
    }
    
    public void addQuizGradeForStudent(int studentID, int score){
        //access student's scores, add one
        book.get(studentID).getScores().add(score);
    }
    
    public boolean isIDUsed (int studentID){
        return book.keySet().contains(studentID);
    }
    
    public Set<Integer> getAllIDs (){
        Set<Integer> allIDs = book.keySet();
        return allIDs;
    }
    
    public Student getStudent(int studentID){
        book.get(studentID);
    }
    
//    public void removeStudent(String student){
//        book.remove(student);
//    }
    
//    public List<Integer> getQuizGrades(String student){
//        //List<Integer> gradesList;
//        //gradesList = book.get(student);
//        //return gradesList;
//    }
    //starting from student name
//    public double calculateAvgQuizGrade(String student){
//        int sum=0;
//        List<Integer> quizGrades;
//        quizGrades = getQuizGrades(student);
//        
//        for (int currentGrade : quizGrades){
//            sum+=currentGrade;
//        }
//        
//        return sum/quizGrades.size();
//    }
    //trying to overload this method so I can use it in two situations
    //starting from values list -- grades
//    public double calculateAvgQuizGrade(List<Integer> quizGrades){
//        int sum=0;
//        for (int currentGrade : quizGrades){
//            sum+=currentGrade;
//        }
//        
//        return sum/quizGrades.size();
//    }
    
//    public double calculateClassAvg(){
//        //get collection of arraylists
//        //iterate over, getting avg, adding to sum var
//        //divide sum var by size of collection
//        int sum=0;
//        //Collection<List<Integer>> quizRows = book.values();
//        for (List<Integer> currentRow : quizRows){
//            sum+=calculateAvgQuizGrade(currentRow);
//        }
//        return sum/quizRows.size();
//    }
    
//    public Set<String> findHighestScores(){
//        
//        //get keySet
//        //iterate over each to get values
//        //iterate through values and if a value higher than highestScore is found, clear the list and add this name
//        //if score found equal to highestScore, add name to list
//        
//        
//        int highestScore = 0;
//        Set<String> highestScorers = new HashSet(); //want to return a set
//        //Set<String> students = getClassRoster();
//        
//        //for (String currentStudent : students){
//            List<Integer> scores = book.get(currentStudent);
//            for (int currentScore : scores){
//                if (currentScore > highestScore){
//                    highestScorers.clear();
//                    highestScorers.add(currentStudent);
//                    highestScore = currentScore;
//                } else if (currentScore == highestScore){
//                    highestScorers.add(currentStudent);
//                }
//            }
//        }
//        return highestScorers;
//    }
//    
//    public Set<String> findLowestScores(){   
//        
//        int lowestScore = 100;
//        Set<String> lowestScorers = new HashSet(); //want to return a set
//        Set<String> students = book.keySet();
//        
//        for (String currentStudent : students){
//            List<Integer> scores = getQuizGrades(currentStudent);
//            for (int currentScore : scores){
//                if (currentScore < lowestScore){
//                    lowestScorers.clear();
//                    lowestScorers.add(currentStudent);
//                    lowestScore = currentScore;
//                } else if (currentScore == lowestScore){
//                    lowestScorers.add(currentStudent);
//                }
//            }
//        }
//        return lowestScorers;
//    }
//    
//    public int getListSize(){
//        return book.size();
//    }
//    
//    public Set<String> getStudents(){
//        return book.keySet();
//    }
}
