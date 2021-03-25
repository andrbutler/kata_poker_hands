/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.kata_poker_hands;

/**
 *
 * @author andrb
 */
public class ResultDeterminer {

    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8",
        "9", "Ten", "Jack", "Queen", "King", "Ace"};
    private Player winner;
    private Player p1;
    private Player p2;
    private Card winningCard;
    private boolean isTie = false;

    public ResultDeterminer(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void determineResult() {
        if (this.checkForHigherScore()) {
            this.printWinMessage();
        } else {
        this.handleSameScoringHands();
        }
        if (this.isTie) {
            System.out.println("Game is a Tie.");
        }
    }

    private boolean checkForHigherScore() {
        if (p1.getScore() > p2.getScore()) {
            this.winner = p1;
            return true;
        } else if (p2.getScore() > p1.getScore()) {
            this.winner = p2;
            return true;
        }
        return false;
    }

    private void printWinMessage() {
        switch (this.winner.getScore()) {
            case 10:
                System.out.println(winner.getName() + " wins with a royal flush!");
                break;
            case 9:
                System.out.println(winner.getName() + " wins with a straight flush: "
                        + this.ranks[this.winner.getHighCard().getValue() - 1] + " high!");
                break;
            case 8:
                System.out.println(winner.getName() + " wins with four "
                        + this.ranks[winner.getFourValue() - 1] + "'s!");
                break;
            case 7:
                System.out.println(winner.getName() + " wins with a full house: "
                        + this.ranks[winner.getThreeValue() - 1] + "'s over "
                        + this.ranks[winner.getPairValue() - 1] + "'s!");
                break;
            case 6:
                System.out.println(winner.getName() + " wins with a flush!");
                break;
            case 5:
                System.out.println(winner.getName() + " wins with a straight: "
                        + this.ranks[this.winner.getHighCard().getValue() - 1] + " high!");
                break;
            case 4:
                System.out.println(winner.getName() + " wins with three "
                        + this.ranks[winner.getThreeValue() - 1] + "'s!");
                break;
            case 3:
                System.out.println(winner.getName() + " wins with a two pair: "
                        + this.ranks[winner.getPairValue() - 1] + "'s and "
                        + this.ranks[winner.getLowPairValue() - 1] + "'s!");
                break;
            case 2:
                System.out.println(winner.getName() + " wins with a pair of "
                        + this.ranks[winner.getPairValue() - 1] + "'s!");
                break;
            default:
                System.out.println(winner.getName() + " wins!");
        }

    }

    private void handleSameScoringHands() {
        switch (this.p1.getScore()) {
            case 10:
                System.out.println("player's have tied with royal flushes!");
                break;
            case 9:
                this.handleStraight();
                break;
            case 8:
                this.handleFourOfKind();
                break;
            case 7:
                this.handleFullHouse();
                break;
            case 5:
                this.handleStraight();
                break;
            case 4:
                this.handleThreeOfKind();
                break;
            case 3:
                this.handleTwoPair();
                break;
            case 2:
                this.handlePair();
                break;
            default:
                this.handleGrouplessHands();
        }
    }

    private void handleStraight() {
        if (p1.getHighCard().getValue() > p2.getHighCard().getValue()) {
            this.winner = p1;
        } else if (p1.getHighCard().getValue() < p2.getHighCard().getValue()) {
            this.winner = p2;
        } else {
            this.isTie = true;
        }

        if (!isTie) {
            this.printWinMessage();
        }
    }

    private void handleFourOfKind() {
        boolean wonByHighCard = false;
        if (p1.getFourValue() > p2.getFourValue()) {
            this.winner = p1;
        } else if (p1.getFourValue() < p2.getFourValue()) {
            this.winner = p2;
        } else if (p1.getTieBreaker().get(0).getValue() > p2.getTieBreaker().get(0).getValue()) {
            this.winner = p1;
            wonByHighCard = true;
        } else if (p1.getTieBreaker().get(0).getValue() < p2.getTieBreaker().get(0).getValue()) {
            this.winner = p2;
            wonByHighCard = true;
        } else {
            this.isTie = true;
        }

        if (!isTie) {
            if (!wonByHighCard) {
                this.printWinMessage();
            } else {
                this.handleWonByHighCard(this.winner.getTieBreaker().get(0));
            }
        }
    }

    private void handleFullHouse() {
        if (p1.getThreeValue() > p2.getThreeValue()) {
            winner = p1;
        } else if (p1.getThreeValue() < p2.getThreeValue()) {
            winner = p2;
        } else if (p1.getPairValue() > p2.getPairValue()) {
            winner = p1;
        } else if (p1.getPairValue() < p2.getPairValue()) {
            winner = p2;
        } else {
            this.isTie = true;
        }

        if (!isTie) {
            this.printWinMessage();
        }
    }

    private void handleThreeOfKind() {
        boolean wonByHighCard = false;
        if (p1.getThreeValue() > p2.getThreeValue()) {
            winner = p1;
        } else if (p1.getThreeValue() < p2.getThreeValue()) {
            winner = p2;
        } else if (p1.getTieBreaker().get(1).getValue() > p2.getTieBreaker().get(1).getValue()) {
            this.winner = p1;
            wonByHighCard = true;
            winningCard = p1.getTieBreaker().get(1);
        } else if (p1.getTieBreaker().get(1).getValue() < p2.getTieBreaker().get(1).getValue()) {
            this.winner = p2;
            wonByHighCard = true;
            winningCard = p2.getTieBreaker().get(1);
        } else if (p1.getTieBreaker().get(0).getValue() > p2.getTieBreaker().get(0).getValue()) {
            this.winner = p1;
            wonByHighCard = true;
            winningCard = p1.getTieBreaker().get(0);
        } else if (p1.getTieBreaker().get(0).getValue() < p2.getTieBreaker().get(0).getValue()) {
            this.winner = p2;
            wonByHighCard = true;
            winningCard = p2.getTieBreaker().get(0);
        } else {
            this.isTie = true;
        }

        if (!isTie) {
            if (!wonByHighCard) {
                this.printWinMessage();
            } else {
                this.handleWonByHighCard(winningCard);
            }
        }
    }

    private void handleTwoPair() {
        boolean wonByHighCard = false;
        if (p1.getPairValue() > p2.getPairValue()) {
            this.winner = p1;
        } else if (p1.getPairValue() < p2.getPairValue()) {
            this.winner = p2;
        } else if (p1.getLowPairValue() > p2.getLowPairValue()) {
            this.winner = p1;
        } else if (p1.getLowPairValue() < p2.getLowPairValue()) {
            this.winner = p2;
        } else if (p1.getTieBreaker().get(0).getValue() > p2.getTieBreaker().get(0).getValue()) {
            this.winner = p1;
            wonByHighCard = true;
        } else if (p1.getTieBreaker().get(0).getValue() < p2.getTieBreaker().get(0).getValue()) {
            this.winner = p2;
            wonByHighCard = true;
        } else {
            this.isTie = true;
        }

        if (!isTie) {
            if (!wonByHighCard) {
                this.printWinMessage();
            } else {
                this.handleWonByHighCard(this.winner.getTieBreaker().get(0));
            }
        }
    }
    
    private void handlePair(){
         boolean wonByHighCard = false;
        if (p1.getPairValue() > p2.getPairValue()) {
            this.winner = p1;
        } else if (p1.getPairValue() < p2.getPairValue()) {
            this.winner = p2;
        } else if (p1.getTieBreaker().get(2).getValue() > p2.getTieBreaker().get(2).getValue()) {
            this.winner = p1;
            wonByHighCard = true;
            this.winningCard = this.winner.getTieBreaker().get(2);
        } else if (p1.getTieBreaker().get(2).getValue() < p2.getTieBreaker().get(2).getValue()) {
            this.winner = p2;
            wonByHighCard = true;
            this.winningCard = this.winner.getTieBreaker().get(2);
        } else if (p1.getTieBreaker().get(1).getValue() > p2.getTieBreaker().get(1).getValue()) {
            this.winner = p1;
            wonByHighCard = true;
            this.winningCard = this.winner.getTieBreaker().get(1);
        } else if (p1.getTieBreaker().get(1).getValue() < p2.getTieBreaker().get(1).getValue()) {
            this.winner = p2;
            wonByHighCard = true;
            this.winningCard = this.winner.getTieBreaker().get(1);
        } else if (p1.getTieBreaker().get(0).getValue() > p2.getTieBreaker().get(0).getValue()) {
            this.winner = p1;
            wonByHighCard = true;
            this.winningCard = this.winner.getTieBreaker().get(2);
        } else if (p1.getTieBreaker().get(0).getValue() < p2.getTieBreaker().get(0).getValue()) {
            this.winner = p2;
            wonByHighCard = true;
            this.winningCard = this.winner.getTieBreaker().get(0);
        } else {
            this.isTie = true;
        }

        if (!isTie) {
            if (!wonByHighCard) {
                this.printWinMessage();
            } else {
                this.handleWonByHighCard(this.winningCard);
            }
        }
    }

    private void handleGrouplessHands() {
        for (int i = 4; i >= 0; i--) {
            if (p1.getHand().get(i).getValue() > p2.getHand().get(i).getValue()) {
                this.winner = p1;
                winningCard = p1.getHand().get(i);
                i = -1;
            } else if (p1.getHand().get(i).getValue() < p2.getHand().get(i).getValue()) {
                this.winner = p2;
                winningCard = p2.getHand().get(i);
                i = -1;
            }
            if (i == 0) {
                this.isTie = true;
            }
        }
        if (!isTie) {
            this.handleWonByHighCard(winningCard);
        }
    }

    private void handleWonByHighCard(Card c) {
        System.out.println(this.winner.getName() + " wins with high card: "
                + c.getRank()
                + c.getSuit() + " !");
    }
}
