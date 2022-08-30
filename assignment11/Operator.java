package com.shpp.p2p.cs.vshevchuk.assignment11;

import java.util.function.Function;

/**
 * Enum with all Operator
 */
enum Operator {
    Plus(1, '+', (b) -> b.firstNumber + b.secondNumber),
    Minus(1, '-', (b) -> b.firstNumber - b.secondNumber),
    Division(2, '/', (b) -> b.firstNumber / b.secondNumber),
    Multiply(2, '*', (b) -> b.firstNumber * b.secondNumber),
    RaiseToPower(3, '^', (b) -> Math.pow(b.firstNumber, b.secondNumber)),
    OpenBrackets(4, '('),
    CloseBrackets(4, ')');

    public final int PRIORITY;
    public final char SYMBOL;
    public Function<Pair, Double> function;

    //Check if symbol is Operator
    public static Operator valueOf(char symbol) {
        for (Operator o : Operator.values()) {
            if (o.SYMBOL == symbol) {
                return o;
            }
        }
        return null;
    }

    // Constructor for Operator
    Operator(int PRIORITY, char SYMBOL, Function<Pair, Double> function) {
        this.function = function;
        this.PRIORITY = PRIORITY;
        this.SYMBOL = SYMBOL;
    }

    // Constructor for Brackets
    Operator(int PRIORITY, char SYMBOL) {
        this.PRIORITY = PRIORITY;
        this.SYMBOL = SYMBOL;
    }
    /**
     * Inner class with two parameters
     */
    static class Pair {
        Double firstNumber;
        Double secondNumber;

        Pair(double firstNumber, double secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }
    }
}

