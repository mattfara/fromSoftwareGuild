/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dto;

/**
 *
 * @author matt
 */
public class Change {
  private int quarters;
  private int dimes;
  private int nickels;
  private int pennies;
  
  public Change(int penniesIn){
    this.quarters = (penniesIn / 25);
    penniesIn %= 25;
    this.dimes = (penniesIn / 10);
    penniesIn %= 10;
    this.nickels = (penniesIn / 5);
    penniesIn %= 5;
    this.pennies = penniesIn;
  }
  
  public int getQuarters(){
    return this.quarters;
  }
  
  public int getDimes(){
    return this.dimes;
  }
  
  public int getNickels(){
    return this.nickels;
  }
  
  public int getPennies(){
    return this.pennies;
  }
}
