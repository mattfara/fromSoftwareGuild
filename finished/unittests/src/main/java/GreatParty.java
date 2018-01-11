/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matt
 */

// When squirrels get together for a party, they like to have cigars.
// A squirrel party is successful when the number of cigars is between
// 40 and 60, inclusive. Unless it is the weekend, in which case there 
// is no upper bound on the number of cigars. Return true if the party
// with the given values is successful, or false otherwise.
//
// greatParty(30, false) → false
// greatParty(50, false) → true
// greatParty(70, true) → true

public class GreatParty {
    public boolean greatParty (int cigars, boolean isWeekend){
        if (cigars<40){
            return false;
        } else {
            if (!isWeekend){
                if (cigars>60){return false;}
            }
            return true;
        }
    }
}
