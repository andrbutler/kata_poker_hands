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
public class InvalidRankException  extends Exception{
    private String rank;
    private String ranks;
    private String rankErrorMessage = " is not a valid rank, valid ranks are: \n";
      public InvalidRankException(String rank, String ranks) {
        super();
        this.rank = rank;
        this.ranks = ranks;
    }
    @Override
    public String getMessage() {
        return (this.rank + this.rankErrorMessage + this.ranks);
    }
}
