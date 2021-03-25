/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.kata_poker_hands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author andrb
 */
public class Player {

    private ArrayList<Card> hand = new ArrayList<Card>();
    private ArrayList<Card> tieBreaker = new ArrayList<Card>();
    private String playerName;
    private int score = 1;
    private boolean isFlush;
    private boolean isStraight;
    private boolean hasPair;
    private int[] handValues = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private Card highCard;
    private int threeValue = 0;
    private int fourValue = 0;
    private int pairValue = 0;
    private int lowPairValue = 0;

    Player(String name) {
        this.playerName = name;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void setName(String playerName) {
        this.playerName = playerName;
    }



    private void sortHand() {
        Collections.sort(hand, (c1, c2) -> c1.getRank().compareTo(c2.getRank()));
    }

    public void printHand() {
        StringBuffer s = new StringBuffer();
        for (Card c : this.hand) {
            s.append(c.getRank() + c.getSuit() + " ");
        }
        System.out.println(s.toString());
    }

    ;
    public void calculateScore() {
        this.sortHand();
        this.calculateHighCard();
        this.checkForflush();
        if (this.isFlush) {
            this.score = 6;
        }
        this.checkForStraight();
        if (this.isStraight) {
            this.score = 5;
        }
        if (this.isStraight && this.isFlush) {
            this.score = 9;
        }
        if (this.isStraight && this.isFlush && (this.highCard.getValue() == 13)) {
            this.score = 10;
        }

        this.checkForGroups();
        if (this.fourValue > 0) {
            this.score = 8;
        }
        if (this.pairValue > 0) {
            this.score = 2;
        }
        if (this.lowPairValue > 0) {
            this.score = 3;
        }
        if (this.threeValue > 0) {
            this.score = 4;
        }
        if ((this.threeValue > 0) && (this.pairValue > 0)) {
            this.score = 7;
        }
        this.calculateTieBreaker();
    }

    private void calculateHighCard() {
        this.highCard = this.hand.get(this.hand.size() - 1);
    }

    private void checkForflush() {
        String suit = this.hand.get(0).getSuit();
        this.isFlush = true;
        for (Card c : hand) {
            if (!(c.getSuit().equals(suit))) {
                this.isFlush = false;
            }
        }
    }

    private void checkForStraight() {
        this.isStraight = false;
        if ((this.hand.get(0).getValue() + 4) == this.hand.get(4).getValue()) {
            this.isStraight = true;
        }
    }

    private void calculateHandValues() {
        for (Card c : this.hand) {
            this.handValues[c.getValue() - 1]++;
        }
    }

    private void checkForGroups() {
        this.calculateHandValues();
        for (int i = 12; i >= 0; i--) {
            if (this.handValues[i] == 4) {
                this.fourValue = i + 1;
            }
            if (this.handValues[i] == 3) {
                this.threeValue = i + 1;
            }
            if (this.handValues[i] == 2) {
                if (this.hasPair) {
                    this.lowPairValue = i + 1;
                } else {
                    this.pairValue = i + 1;
                    this.hasPair = true;
                }
            }
        }
    }

    private void calculateTieBreaker() {
        if (this.score == 8) {
            for (Card c : hand) {
                if (c.getValue() != this.fourValue) {
                    this.tieBreaker.add(c);
                }
            }
        } else if (this.score == 4) {
            for (Card c : hand) {
                if ((c.getValue() != this.threeValue)) {
                    this.tieBreaker.add(c);
                }
            }
        } else if (this.score == 3) {
            for (Card c : hand) {
                if ((c.getValue() != this.pairValue) && (c.getValue() != this.lowPairValue)) {
                    this.tieBreaker.add(c);
                }
            }
        } else if (this.score == 2) {
            for (Card c : hand) {
                if (c.getValue() != this.pairValue) {
                    this.tieBreaker.add(c);
                }
            }
        }

    }

    public int getScore() {
        return score;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getTieBreaker() {
        return tieBreaker;
    }

    public String getName() {
        return playerName;
    }

    public Card getHighCard() {
        return highCard;
    }

    public int getThreeValue() {
        return threeValue;
    }

    public int getFourValue() {
        return fourValue;
    }

    public int getPairValue() {
        return pairValue;
    }

    public int getLowPairValue() {
        return lowPairValue;
    }
}
