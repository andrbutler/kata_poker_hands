/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.kata_poker_hands;

import Exceptions.InvalidCardLengthException;
import Exceptions.InvalidRankException;
import Exceptions.InvalidSuitException;
import java.util.Arrays;

/**
 *
 * @author andrb
 */
public class Parser {
    private String[] inputs;
    private String[] suits = {"C", "D", "H", "S"};
    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8",
        "9", "T", "J", "Q", "K", "A"};
    Parser(){};
    public void parsePlayerHand(String s, Player p) throws InvalidCardLengthException, InvalidRankException, InvalidSuitException{ 
        inputs = s.split(" ");
        
        for(String input: inputs){
            String rank = Character.toString(input.charAt(0));
            String suit = Character.toString(input.charAt(1));
            if(!this.validateLength(input)){
                throw new InvalidCardLengthException(input);
            }
            if(!this.validateRank(rank)){
                throw new InvalidRankException(rank, String.join(", ", ranks));
            }
            if(!this.validateSuit(suit)){
                throw new InvalidSuitException(suit, String.join(", ", suits));
            }
            p.addCard(new Card(rank, suit, this.getValue(rank)));
        }
    }
    public boolean validateLength(String s) {
        if(s.length() == 2){
          return true;
        }
        return false;
    }
    public boolean validateRank(String rank) {
        if((Arrays.asList(ranks).contains(rank))){
            return true;
        }
        return false;
    }
    public boolean validateSuit(String suit) {
        if((Arrays.asList(suits).contains(suit))){
         return true;
        }
        return false;
    }
    public Integer getValue(String rank) {
        if(rank.equals("A")){
            return 13;
        } 
        else if(rank.equals("K")){
            return 12;
        }
        else if(rank.equals("Q")){
            return 11;
        }
        else if(rank.equals("J")){
            return 10;
        }
        else if(rank.equals("T")){
            return 9;
        }
        return Integer.parseInt(rank) - 1;
    }

    public void setSuits(String[] suits) {
        this.suits = suits;
    }

    public void setRanks(String[] ranks) {
        this.ranks = ranks;
    }
}
