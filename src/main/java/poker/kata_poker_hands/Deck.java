/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.kata_poker_hands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author andrb
 */
public class Deck {
    private ArrayList<Card> cards;
    
    Deck() {   
        this.cards = new ArrayList<Card>();
    }
    
    public void addCard(Card card){
        this.cards.add(card);
    }
    public Card draw(){
        Card c = this.cards.get(0);
        this.cards.remove(0);
        return c;
    }
    public void shuffle(){
        Collections.shuffle(cards, new Random(100));
    }
    public ArrayList<Card> getCards(){
        return this.cards;
    }
}
