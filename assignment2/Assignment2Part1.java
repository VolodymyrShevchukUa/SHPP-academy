package com.shpp.p2p.cs.vshevchuk.assignment2;


import com.shpp.cs.a.console.TextProgram;


/**
 * The Assignment2Part1 class extends the basic TextProgram class
 * Write a console program that will input 3 numbers of type double
 * (a,b,c), and give the roots of the quadratic equation.
 */

public class Assignment2Part1 extends TextProgram {

    @Override
    public void run() {
        // you must input 3 double, a - cant be a null.
        double a = readDouble("please input a:");
        double b = readDouble("please input b:");
        double c = readDouble("please input c:");
        // a cannot be 0, because if a = 0, then it is not a quadratic equation
        if (a != 0) {
            findSolution(a, b, c);
        } else {
            print("Incorrect \"a\" ");
        }
    }

    private void findSolution(double a, double b, double c) {
        // d = Discriminate
        double d;
        //this is our "X"
        double root;
        double root2;
        d = b * b - (4 * a * c);

        if (d < 0) {
            print("There are not real roots ");
            println();
            return;
        }
        if (d == 0) {
            print("There is one root ");
            root = -(b / 2 * a);
            print(root);
            println();
        } else {
            print("There are two roots ");
            // Math.sqrt - returns the root "d"
            root = (-b + Math.sqrt(d)) / (2 * a);
            print(root);
            print(" and ");
            root2 = (-b - Math.sqrt(d)) / (2 * a);
            print(root2);
            println();
        }
    }
}
