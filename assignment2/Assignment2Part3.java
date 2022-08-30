package com.shpp.p2p.cs.vshevchuk.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The Assignment2Part3 class extends the basic WindowProgram class
 * Write a program which will draws a pawprint according conditions
 * to use all constants.
 */

public class Assignment2Part3 extends WindowProgram {


    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     *
     * (Yes, I know that actual pawprints have four toes.
     * Just pretend it's a cartoon animal. ^_^)
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    // Heel size parameter
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    private void drawPawprint(double x, double y) {

        double[] OffsetX = new double[3];
        double[] OffsetY  = new double[3];

        //add to Array X
        OffsetX[0] = FIRST_TOE_OFFSET_X;
        OffsetX[1] = SECOND_TOE_OFFSET_X;
        OffsetX[2] = THIRD_TOE_OFFSET_X;
        // add to Array Y
        OffsetY[0] = FIRST_TOE_OFFSET_Y;
        OffsetY[1] = SECOND_TOE_OFFSET_Y;
        OffsetY[2] = THIRD_TOE_OFFSET_Y;

        // Creating a 3 Toe
        for (int i = 0; i < 3; i++) {
            add(drawOval(x + OffsetX[i], y + OffsetY[i], TOE_WIDTH, TOE_HEIGHT));
        }
        // Creating a Heel
        add(drawOval(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT));

    }
    // This method creating a Oval
    private GOval drawOval(double x, double y, double width, double height) {
        GOval gOval = new GOval(x, y, width, height);
        gOval.setColor(Color.black);
        gOval.setFilled(true);
        return gOval;
    }
}


