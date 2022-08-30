
package com.shpp.p2p.cs.vshevchuk.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** In
 *   The Assignment2Part1 class extends the basic TextProgram class
 *   We must create visual illusion.
 *   1 big rect must cover 4 circles, which are on the edges of the field
 *
 */

public class Assignment2Part2 extends WindowProgram {
    //Const parameter
    final double OVAL_WIDTH = 100;
    final double OVAL_HEIGHT = 100;
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 300;

    @Override
    public void run() {
        addLeftRounds();
        addRightRounds();
        addRect();

    }

    //this method create left rounds
    public void addLeftRounds() {

        double ovalYPosition = getHeight() - OVAL_HEIGHT;
        // while the program is running the coordinate Y is changing 1 time
        double widthPosition = 0.0;

        for (int i = 0; i < 2; i++) {
            GOval leftOval = new GOval(0.0, widthPosition, OVAL_WIDTH, OVAL_HEIGHT);
            leftOval.setColor(Color.BLACK);
            leftOval.setFilled(true);
            add(leftOval);
            widthPosition = ovalYPosition;
        }
    }

    //this method create right rounds
    public void addRightRounds() {
        double ovalYPosition = getHeight() - OVAL_HEIGHT;
        double ovalXPosition = getWidth() - OVAL_WIDTH;
        // while the program is running the coordinate Y is changing 1 time
        double widthPosition = 0.0;

        for (int i = 0; i < 2; i++) {
            GOval rightOval = new GOval(ovalXPosition, widthPosition, OVAL_WIDTH, OVAL_HEIGHT);
            rightOval.setColor(Color.BLACK);
            rightOval.setFilled(true);
            widthPosition = ovalYPosition;
            add(rightOval);

        }
    }

    //Add Rect on rounds
    void addRect() {
        // the rectangle is drawn in the middle of the circle
        final double X_RECT = OVAL_WIDTH / 2;
        final double Y_RECT = OVAL_HEIGHT / 2;
        GRect r = new GRect(X_RECT, Y_RECT, getWidth() - OVAL_WIDTH, getHeight() - OVAL_HEIGHT);
        r.setColor(Color.WHITE);
        r.setFilled(true);
        add(r);
    }
}
