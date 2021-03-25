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
public class InvalidInputException  extends Exception{
    String InputErrorMessage = "Error in processing poker hands! Proper Format is: \n[first_player_name]: [card1] [card2] [card3] [card4] [card5] [second_player_name]: [card1] [card2] [card3] [card4] [card5]\n Player names may not contain spaces.";
    public InvalidInputException() {
        super();
    }
    @Override
    public String getMessage() {
        return this.InputErrorMessage;
    }
}
