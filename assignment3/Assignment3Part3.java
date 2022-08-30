package com.shpp.p2p.cs.vshevchuk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    /**
     * We need to write a method that will take two parameters
     * and calculate the value of the first parameter taken to the degree of parameter 2.
     */
    @Override
    public void run() {
        print(raiseToPower(readDouble("Please, input Base"), readInt("Please, input Exponent")));
    }

    private double raiseToPower(double base, int exponent) {
        double result = 1;
        double square = base;
        if (exponent == 0) {
            return result;
            // handler for negative exponent
        } else if (exponent < 0) {
            for (int i = -1; i > exponent; i--) {
                square *= base;
            }
            result = 1d / (square);
            // handler for positive exponent
        } else {
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }

        }
        return result;
    }
}
