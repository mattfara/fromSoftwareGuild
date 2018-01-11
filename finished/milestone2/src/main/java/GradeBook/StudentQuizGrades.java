/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GradeBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



/**
 *
 * @author matt
 */
public class StudentQuizGrades {
    UserIO io = new UserIOImpl();
    GradeBook myGradeBook = new GradeBook();
    boolean keepUsing = true;
    
    
    public void useBook(){
        openingScreen();
        
        while (keepUsing){
            promptUser();
            followOption(getUserOption());
        }
        
        exitScreen();
    }
    
    
    
    public void printArt(){
        io.print("                                                                  \n" +
"                                                                  \n" +
"  ,----..    ,--,                                                 \n" +
" /   /   \\ ,--.'|                                                 \n" +
"|   :     :|  | :                                                 \n" +
".   |  ;. /:  : '                 .--.--.    .--.--.              \n" +
".   ; /--` |  ' |     ,--.--.    /  /    '  /  /    '       .--,  \n" +
";   | ;    '  | |    /       \\  |  :  /`./ |  :  /`./     /_ ./|  \n" +
"|   : |    |  | :   .--.  .-. | |  :  ;_   |  :  ;_    , ' , ' :  \n" +
".   | '___ '  : |__  \\__\\/: . .  \\  \\    `. \\  \\    `./___/ \\: |  \n" +
"'   ; : .'||  | '.'| ,\" .--.; |   `----.   \\ `----.   \\.  \\  ' |  \n" +
"'   | '/  :;  :    ;/  /  ,.  |  /  /`--'  //  /`--'  / \\  ;   :  \n" +
"|   :    / |  ,   /;  :   .'   \\'--'.     /'--'.     /   \\  \\  ;  \n" +
" \\   \\ .'   ---`-' |  ,     .-./  `--'---'   `--'---'     :  \\  \\ \n" +
"  `---`             `--`---'                               \\  ' ; \n" +
"                                                            `--`  ");
    }
    
    public void openingScreen(){
        printArt();
        io.print("");
        io.print("Welcome to Classy. Here you can view your class's gradebook, edit it, and run calculations.");
        io.print("");
    }
    
    public void exitScreen(){
        printArt();
        io.print("Thank you for using Classy");
    }
    
    public void promptUser(){
        io.print("");
        io.print("What would you like to do?");
        io.print("Menu of options: ");
        io.print("===============================================");
        io.print("1 - View list of students");
        io.print("2 - Add student to gradebook");
        io.print("3 - Add grade to student's list of grades");
        io.print("4 - Remove student from gradebook");
        io.print("5 - View quiz grades for a student");
        io.print("6 - View average quiz score for a student");
        io.print("7 - View average quiz score for entire class");
        io.print("8 - View student(s) with highest quiz score");
        io.print("9 - View student(s) with lowest quiz score");
        io.print("10 - Quit program");
        io.print("===============================================");
        io.print("");
    } 
    
    public int getUserOption(){
        io.print("");
        return io.readInt("Please enter the number (#) of the option you'd like", 1,10);
        
    }
    
    public void showClassRoster(){
        if (myGradeBook.isListEmpty()){
            io.print("Your roster is empty");
        } else {
            io.print("ID# - Name");
            Set<Integer> allIds = myGradeBook.getAllIDs();
            for (int currentID : allIds){
                System.out.println(currentID + " - " + (myGradeBook.getStudent(currentID).getName()));
            }
        }
    }
    
    public void addStudentToBook(){
        //could search through existing students for the same name and bring up something to alert teacher
        
        String studentName = io.readString("What is the name of the student you'd like to add?");
        if ((myGradeBook.isStudentNameUsed(studentName))){
            String confirm = io.readString("You have a student by this name already. Are you sure you are adding a unique student? Enter 'y' to add");
            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")){
                GradeBook.addStudent(studentName);
                io.print("");
                io.print("Student added");
                io.print("");
            }
        }
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
    }
    // only got here if we didn't return false
    return true;
}
    
    public void addQuizGradesForStudent(){
        int studentID;
        int grade;
        String userInput;
        boolean finishedAddingGrades = false;
        int newGradeCounter = 1;
        
        while(!finishedAddingGrades){
            if(!myGradeBook.isListEmpty()){
                studentID = io.readInt("Enter student's ID # ");
                if (myGradeBook.isIDUsed(studentID)){
                    io.print("Enter 'S' when you want to stop adding grades");
                    userInput = io.readString((newGradeCounter == 1 ? "Please enter the first score for this student" : "Please enter score " + newGradeCounter + " for this student"));
                    if (userInput.equalsIgnoreCase("s") || userInput.equalsIgnoreCase("stop")){
                        break;
                    } else if (isInteger(userInput)){
                        myGradeBook.addQuizGradeForStudent(studentID, Integer.parseInt(userInput));
                    } else {
                        io.print("Please enter a number or 'S'");
                }
                    
                    
                    
                } else {
                    io.print("That student is not in your roster");
                }
            } else {
                io.print("Your roster is empty. You can't add grades yet.");
            }
        }
    }
    
  
    
    
    
//    public void removeStudentFromBook(){
//        String student = io.readString("What student do you want to remove? ");
//        if (!isListEmpty()) {
//            if (doesStudentExist(student)){
//                myGradeBook.removeStudent(student);
//                io.print("Student removed");
//                io.print("");
//            } else {
//                io.print("That student is not in your roster");
//            }
//        } else {
//            io.print("Your roster is empty. You can't remove students yet.");
//        }
//    }
//    
//    public void showQuizGradesForStudent(){
//        String student;
//        List<Integer> grades;
//        if (myGradeBook.getListSize()>0){
//            student = io.readString("Which student?");
//            grades = myGradeBook.getQuizGrades(student);
//            for (int currentGrade : grades){
//                io.print(Integer.toString(currentGrade)); //why can't use io.print(currentGrade); here w/o toString?
//            }
//        } else {
//            io.print("Your roster is empty. You can't do this yet");
//        } 
//    }
//    
//    public void showAverageQuizGradeForStudent(){
//        double avg;
//        if (!isListEmpty()){
//            avg = myGradeBook.calculateAvgQuizGrade(io.readString("For which student would you like to calculate an average quiz score? "));
//            io.print(Double.toString(avg));
//        } else {
//            io.print("Your roster is empty. You can't do this yet.");
//        }    
//    }
//    
//    public void showClassAvg(){
//        double avg;
//        if(!isListEmpty()){
//            avg = myGradeBook.calculateClassAvg();
//            io.print("The class average is: ");
//            io.print(Double.toString(avg));
//        } else {
//            io.print("Your roster is empty. The class doesn't have an average yet.");
//        }
//    }
//    
//    public void showHighestScorers(){
//        Set<String> highScorers;
//        if (!isListEmpty()){
//            highScorers = myGradeBook.findHighestScores();
//            io.print(highScorers.size() > 1 ? "The highest scoring student is " : "The highest scoring students are ");
//            for (String currentScorer : highScorers){
//                io.print(currentScorer);
//            }
//        } else {
//            io.print("Your roster is empty. You can't show high scores yet");
//        }
//    }
//    
//    public void showLowestScorers(){
//        Set<String> lowScorers;
//        if (!isListEmpty()){
//            lowScorers = myGradeBook.findLowestScores();
//            io.print(lowScorers.size() > 1 ? "The lowest scoring student is " : "The lowest scoring students are ");
//            for (String currentScorer : lowScorers){
//                io.print(currentScorer);
//            }
//        } else {
//            io.print("Your roster is empty. You can't show high scores yet");
//        }   
//    }
    
    public void followOption(int option){
        switch(option){
            case 1: showClassRoster();
                    break;
            case 2: addStudentToBook();
                    break;
            case 3: addQuizGradesForStudent();
                    break;
            case 4: removeStudentFromBook();
                    break;
            case 5: showQuizGradesForStudent();
                    break;
            case 6: showAverageQuizGradeForStudent();
                    break;
            case 7: showClassAvg();
                    break;
            case 8: showHighestScorers();
                    break;
            case 9: showLowestScorers();
                    break;
            case 10: keepUsing=false;
                     break;
            
            default: io.print("Something is wrong - check UserIOImpl implementation for readInt()");
                     break;
        }    
    }
}
    
    

