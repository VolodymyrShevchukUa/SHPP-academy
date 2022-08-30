package com.shpp.p2p.cs.vshevchuk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram {

    public static final int CASH_LIMIT = 20;

    /**
     * There are two people playing: a happy-go-lucky and a sleepy-go-lucky. The game ends when the first one earns $20 or more.
     * The spirited person puts $1 on the table, and the hapless person begins to flip a coin.
     * If heads - then the winner reports to the sum on the table exactly the same amount.
     * If tails, everything on the table goes to the winner.
     * If the result of the winner is less than $20, the game is repeated.
     */


    @Override
    public void run() {
        saintPeterBurgGame();

    }
    // this method return true with chance 50%
    public boolean headsOrTails() {
        return Math.random() > 0.5;
    }

    public void saintPeterBurgGame() {
        // Start game
        // we always start with 1 dollar
        // gameCounter calculates the number of games
        int totalCash = 0;
        int cash = 1;
        int gameCounter = 0;
        handlerGame(totalCash,cash,gameCounter);
    }


// While cash will be lesser than 20$, game will continue
    public void handlerGame(int totalCash, int cash, int gameCounter) {
        while (totalCash < CASH_LIMIT) {
            if (headsOrTails()) {
                cash += cash;
            } else {
                println("This game, you earned " + " " + cash + "$");
                totalCash += cash;
                println("Your total is " + totalCash);
                gameCounter++;
                cash = 1;
            }
        }
        finalAswer(gameCounter);
    }
    public void finalAswer(int gameCounter) {
        println("It took" + " " + gameCounter + " games to earn $20");
    }
}


