/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.kata_poker_hands;

import java.util.Comparator;

/**
 *
 * @author andrb
 */
public class Card {
    private String rank;
    private String suit;
    private Integer value;
    
    Card(String rank, String suit, Integer value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
