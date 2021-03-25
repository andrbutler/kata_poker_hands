/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.kata_poker_hands;

import Exceptions.InvalidCardLengthException;
import Exceptions.InvalidInputException;
import Exceptions.InvalidRankException;
import Exceptions.InvalidSuitException;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author andrb
 */
public class Kata {
    
    /* string arrys used if generating deck and hands without input
    private static final String[] suits = {"C", "D", "H", "S"};
    private static final String[] ranks = {"2", "3", "4", "5", "6", "7", "8",
        "9", "T", "J", "Q", "K", "A"};
     */

    public static void main(String[] args) throws IOException {

        /* logic for generating player hands and deck with no external input
        Deck deck = new Deck();
        int handSize = 5;
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        //build standard poker deck
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 13; j++) {
                deck.addCard(new Card(ranks[j], suits[i], j + 1));
            }
        }
        deck.shuffle();
        
        //draw player starting hands and sort cards by rank
        for(int i = 0; i < handSize; i++) {
            player1.addCard(deck.draw());
            player2.addCard(deck.draw());
        }
         */
        try {
            if (args.length == 12) {
                Player player1 = new Player(args[0].substring(0, args[0].length() - 1));
                Player player2 = new Player(args[6].substring(0, args[6].length() - 1));
                Parser parser = new Parser();
              
                parser.parsePlayerHand(String.join(" ", Arrays.copyOfRange(args, 1, 6)), player1);
                parser.parsePlayerHand(String.join(" ", Arrays.copyOfRange(args, 7, 12)), player2);
                
                player1.calculateScore();
                player2.calculateScore();

                //print player hands;
                System.out.println(player1.getName() + "'s hand: ");
                player1.printHand();

                System.out.println(player2.getName() + "'s hand: ");
                player2.printHand();
                
                new ResultDeterminer(player1, player2).determineResult();
            } else {
                throw new InvalidInputException();
            }
        } catch (InvalidInputException | InvalidRankException | InvalidCardLengthException | InvalidSuitException e) {
            System.out.println(e.getMessage());
        }
    }
}
