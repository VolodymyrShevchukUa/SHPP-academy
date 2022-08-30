package com.shpp.p2p.cs.vshevchuk.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The Assignment2Part5 class extends the basic WindowProgram class
 * Write a program which will draw a matrix of black boxes.
 * Drawing should be in the center of window.
 */

public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 7;
    private static final int NUM_COLS = 8;
    // Window Size
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    @Override
    public void run() {

        drawIllusion();
    }

    private void drawIllusion() {
        double verticalSize = NUM_ROWS * BOX_SIZE + BOX_SPACING/2d * (NUM_ROWS-1);
        double horizontalSize = NUM_COLS * BOX_SIZE + BOX_SPACING/2d * (NUM_COLS-1);
        double yPosition = getHeight()/2d - verticalSize/2d;
        //  create 2d massive
        buildDesk(horizontalSize, yPosition);
    }

    private void buildDesk(double horizontalSize, double yPosition) {
        // cycle for ROWS
        // in each iteration we make offset
        for (int j = 0; j < NUM_ROWS; j++) {
            double xPosition = ((double) getWidth()) / 2d - horizontalSize /2d;
            // cycle for COLS
            // in each iteration we make offset
            for (int i = 0; i < NUM_COLS; i++) {
                GRect gRect = new GRect(xPosition, yPosition, BOX_SIZE, BOX_SIZE);
                gRect.setColor(Color.BLACK);
                gRect.setFilled(true);
                gRect.setFillColor(Color.BLACK);
                add(gRect);
                xPosition += (BOX_SIZE + BOX_SPACING/2);
            }
            yPosition += (BOX_SIZE + BOX_SPACING/2);
        }
    }

}


