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
public class InvalidCardLengthException  extends Exception{
    String cardLengthErrorMessage = " is not a valid card, valid cards consist of a rank and a suit in the format of:"
            + "\n[rank][suit], with each containing exactly 1 character"
            + "\nfor example: AS, KC, 2D, TH, ect";
    String card;
      public InvalidCardLengthException(String card) {
        super();
        this.card = card;
    }
    @Override
    public String getMessage() {
        return (this.card + this.cardLengthErrorMessage);
    }
}
