/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GradeBook;

import java.util.List;
import java.util.Map;

/**
 *
 * @author matt
 */
public class Student {

    private static int IdNumber = 0;
    private String name;
    private List<Integer> scores;

    
    //constructor
    public Student (String name){
        this.name = name;
        IdNumber++;
    }
    
    
    
    public static int getIdNumber(){
        return IdNumber;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }
    
    
}
