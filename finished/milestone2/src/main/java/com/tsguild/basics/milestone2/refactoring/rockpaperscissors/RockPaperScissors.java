/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.basics.milestone2.refactoring.rockpaperscissors;

/**
 *
 * @author matt
 */
import java.util.Scanner;
import java.util.Random;
public class RockPaperScissors {
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    
    int rounds, currentRound=1;//2
    int userMove, compMove; 
    int userWins, compWins, ties;
    String playAgain = "yes";
    boolean roundsInBounds;
    boolean userMoveLegal;

    public void playRPS(){
        printOpeningScreen();
        showPermittedMoves();
        while (playAgain.equals("yes")){
            setScoresToZero();
            
            
            while (roundsInBounds==false){
                setRounds();
                if (roundsInBounds(rounds)){
                    roundsInBounds=true;
                }
            }
            //rounds playing out
            for (; currentRound <= rounds; currentRound++){
                userMoveLegal=false;
                while (userMoveLegal==false){
                    setUserMove();
                    if(checkUserMove(userMove)){
                        userMoveLegal=true;
                    }
                }
                setCompMove();
                printMoves();
                evaluateRound();
            }
            
            //once all rounds played
            showScoreBoard();
            evaluateGame();
            askPlayAgain();            
        }
        printExit();
    }
    
    public void printArt(){
        System.out.println("*********************************"); //3 https://tinyurl.com/y8edjlt7
        System.out.println("*____________________  _________*\n" +
"*\\______   \\______   \\/   _____/*\n" +
"* |       _/|     ___/\\_____  \\ *\n" +
"* |    |   \\|    |    /        \\*\n" +
"* |____|_  /|____|   /_______  /*\n" +
"*        \\/                  \\/ *");
        System.out.println("*********************************");
        System.out.println("     *ROCK, PAPER, SCISSORS*     ");
        System.out.println("     ***********************     "); 
        System.out.println(); //4
        
    }
    
    public void printOpeningScreen(){
        
        printArt();
        
        System.out.println("Prepare to lose, human!");
        System.out.println();
        System.out.println("I won't waste time with the rules other than this:");
        System.out.println();
        System.out.print("Each move has its own number you push to play. ");
        System.out.println("Just have a look at this: ");
        
        
    }
    
    public void showPermittedMoves() {
        System.out.println();
        System.out.println("    MOVES   ");
        System.out.println("------------");
        System.out.println("|Rock      1|");
        System.out.println("|Paper     2|");
        System.out.println("|Scissors  3|");
        System.out.println("------------");
        System.out.println();
    }
    
    public void setScoresToZero(){
        this.ties=0; this.userWins=0; this.compWins=0;
    }
    
    public void setRounds(){
        System.out.println("**********************************************************************");
        System.out.println("So, how many rounds you wanna play? We can do up to 10.... ");
        System.out.print("Rounds: ");
        this.rounds = sc.nextInt();
    }
    
    public boolean roundsInBounds(int rounds){
        return rounds <= 10 || rounds >= 1; //7
    }
    
    public void setUserMove(){
        System.out.println("Round " + currentRound);
        System.out.println("Rock, paper, scissors says SHOOT!");
        System.out.print("Your move: ");
                
        this.userMove = sc.nextInt(); 
    }
    
    public boolean checkUserMove(int move){
        return userMove >= 1 || userMove <= 3; //11
    }
    
    public void callOutUserError(boolean userInputBad){
        if (userInputBad) {
            System.out.println("Hey! Don't be a weirdo!!!! Go again!");
            
            showPermittedMoves();
        }
    }
    
    public void setCompMove(){
        this.compMove = rand.nextInt(3)+1;
    }
    
    
    
    public void printMoves(){
        switch(userMove){ //14
            case 1: System.out.println("You played rock");
                    break;
            case 2: System.out.println("You played paper");
                    break;
            case 3: System.out.println("You played scissors");
                    break;
            default: System.err.println("Illegal input - userMove variable -- check method called on Scanner object"); //16 Thanks, Eric
                    break;
        }
                
                //printing computer's move
                switch(compMove){
                    case 1: System.out.println("I played rock");
                            break;
                    case 2: System.out.println("I played paper");
                            break;
                    case 3: System.out.println("I played scissors");
                            break;
                    default: System.err.println("Illegal - The compMove variable has an incompatible value -- check method called on Random object");
                            break;
                }
    
    }
    
    public void evaluateRound(){
        if (userMove == compMove){ //17 breakpoint for round evaluation logic
            ties++; //18
            System.out.println("We tied!"); 
                
                    //cases where user wins (U = user, C = computer)
                    //            U:paper and C:rock         or     U:scissor and C:paper         or        U:rock and C:scissor
        } else if (((userMove == 2 && compMove == 1) || (userMove == 3 && compMove == 2)) || (userMove == 1 && compMove == 3)){ //19
            userWins++;
            System.out.println("You win....");
        } else { //20
            compWins++;
            System.out.println("You LOSE!");
        }
                
        System.out.println(); //breakpoint for round evaluation logic
    }
    
    public void showScoreBoard(){
        System.out.println("##########################################################"); //breakpoint for score change
        System.out.println("##########################################################");
                                                        //trying ternary for output plural/singular problem -- reduces clutter of if/else
        System.out.println("After "+rounds+" round"+(rounds==1 ? "":"s")+", here are the results!"); //21
        System.out.println("-------------------------------------                          ");
        System.out.println("     Ties: " + ties+ "                                         ");
        System.out.println("  My wins: " + compWins+"                                      ");
        System.out.println("Your wins: " + userWins+"                                      ");
            
        System.out.println();
    }
    
    public void evaluateGame(){
        if (userWins == compWins){ //22
            System.out.println("We tied! Let's play again and see who the real winner is!");
        } else if (userWins > compWins) { //23
            System.out.println("You won, you rat bastard");
        } else { //24
            System.out.println("You lost, of course");
        }
    }
    
    
    public void askPlayAgain(){
        System.out.println("Do you want to play again?");
        playAgain = sc.next();
    }
    
    public void printExit(){
        System.out.println("Thanks for playing");
        printArt();
    }
    
    
}

