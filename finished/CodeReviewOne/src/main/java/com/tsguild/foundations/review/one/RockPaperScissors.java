/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.review.one;
    
/**
 *
 * @author matt
 */
import java.util.Scanner;
import java.util.Random;
public class RockPaperScissors {
    public static void main(String[] args) { //1
        
        //declaring/initializing variables
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        int rounds;//2
        int userMove, compMove; 
        int userWins, compWins, ties;
        
        //opening screen
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
        System.out.println("Prepare to lose, human!");
        System.out.println();
        System.out.println("I won't waste time with the rules other than this:");
        System.out.println();
        System.out.print("Each move has its own number you push to play. ");
        System.out.println("Just have a look at this: ");
        System.out.println();
        System.out.println("    MOVES   ");
        System.out.println("------------");
        System.out.println("|Rock      1|");
        System.out.println("|Paper     2|");
        System.out.println("|Scissors  3|");
        System.out.println("------------");
        System.out.println();
        
        
        //begin new game
        while (true) { //5
            
            ties=0; userWins=0; compWins=0; //6 
            System.out.println("**********************************************************************");
            System.out.println("So, how many rounds you wanna play? We can do up to 10.... ");
            System.out.print("Rounds: ");
            rounds = sc.nextInt();
            
            //check to see that rounds are in bounds
            if (rounds > 10) { //7
                System.out.println("What, can't your read? I said 10 max. You can take your business elsewhere, bub");
                break; //8
            } else if (rounds < 1) {
                System.out.println("What are you, some kind of comedian. Buzz off!");
                break;
            }
            
            
            //start of game play
            System.out.println();
            for (int i = 1; i<=rounds; i++){ //9
                //call to player to choose move, and generate computers move
                System.out.println("Round " + i);
                System.out.println("Rock, paper, scissors says SHOOT!");
                System.out.print("Your move: ");
                
                userMove = sc.nextInt(); 
                compMove = rand.nextInt(3)+1; //10
                System.out.println();
                
                //catching bad input and repeating the round
                       //nextInt on line 80 rules out all input except integers
                       //this if statement rules out integers that don't map to valid moves
                if (userMove < 1 || userMove > 3){ //11
                    System.out.println("Hey! Don't be a weirdo!!!! Go again!");
                    System.out.println();
                    System.out.println("   MOVES   ");
                    System.out.println("-------------");
                    System.out.println("Rock      1");
                    System.out.println("Paper     2");
                    System.out.println("Scissor   3");
                    System.out.println();
                    i--; //12 
                    continue; //13
                }
                
                //printing user's move
                switch(userMove){ //14
                    case 1: System.out.println("You played rock");
                            break; //15
                    case 2: System.out.println("You played paper");
                            break;
                    case 3: System.out.println("You played scissors");
                            break;
                    default: System.err.println("Illegal input - userMove variable -- check method called on Scanner object"); //16 Thanks, Eric
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
                }
                
                //evaluating the round
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
            
            //score board
            System.out.println("##########################################################"); //breakpoint for score change
            System.out.println("##########################################################");
                                                        //trying ternary for output plural/singular problem -- reduces clutter of if/else
            System.out.println("After "+rounds+" round"+(rounds==1 ? "":"s")+", here are the results!"); //21
            System.out.println("-------------------------------------                          ");
            System.out.println("     Ties: " + ties+ "                                         ");
            System.out.println("  My wins: " + compWins+"                                      ");
            System.out.println("Your wins: " + userWins+"                                      ");
            
            System.out.println();
            
            //evaluating full game
            if (userWins == compWins){ //22
                System.out.println("We tied! Let's play again and see who the real winner is!");
            } else if (userWins > compWins) { //23
                System.out.println("You won, you rat bastard");
            } else { //24
                System.out.println("You lost, of course");
            }
            
            System.out.println("##########################################################"); //breakpoint for score changes
            System.out.println("##########################################################");
            System.out.println();
            
            
            /*end of game play*/
            
            System.out.print("Wanna play again? (y/n) ");
            
            if (sc.next().equals("n")){ //25
                System.out.println();
                System.out.print("Thanks for playing!");
                System.out.println();
                System.out.println("*********************************");
        System.out.println("*____________________  _________*\n" +
"*\\______   \\______   \\/   _____/*\n" +
"* |       _/|     ___/\\_____  \\ *\n" +
"* |    |   \\|    |    /        \\*\n" +
"* |____|_  /|____|   /_______  /*\n" +
"*        \\/                  \\/ *");
        System.out.println("*********************************");
        System.out.println("     *ROCK, PAPER, SCISSORS*     ");
        System.out.println("     ***********************     ");        
                break; //26
            } 
            
            System.out.println();
        }
    }
    
    
    
    
}
