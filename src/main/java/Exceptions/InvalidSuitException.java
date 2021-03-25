/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author andrb
 */
public class InvalidSuitException  extends Exception{
    private String suit;
    private String suits;
    private String suitErrorMessage = " is not a valid suit, valid suits are: \n";
      public InvalidSuitException(String suit, String suits) {
        super();
        this.suit = suit;
        this.suits = suits;
    }
    @Override
    public String getMessage() {
        return (this.suit + this.suitErrorMessage + this.suits);
    }
}
