package com.shpp.p2p.cs.vshevchuk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {
    /**
     * Take some integer and call it n
     * If n is a pair, then divide it by 2
     * If n is non-par, multiply by 3 and add 1
     * Continue this process until n equals 1
     */
    public void run() {
        CalculateNumber();

    }

    public void CalculateNumber() {
        double n = readInt("Enter a number \"n\" ");
        if (n > 0 ) {
            while (n != 1.0) {
                if (n % 2 == 1) {
                    print(n + " " + " is odd so I make 3n + 1 ");
                    n *= 3;
                    println(n += 1);
                }
                if (n % 2 == 0) {
                    print(n + " " + " Is even, so i take half ");
                    println(n /= 2);
                }
            }
            print("The end folks");
        } else {
            print("number must be positive and integer!!!!");
            CalculateNumber();
        }
    }
}
