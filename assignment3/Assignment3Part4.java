package com.shpp.p2p.cs.vshevchuk.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * So, it will be necessary to make a pyramid of clay. Each row contains one less clay.
 * The frame should be centered horizontally and lie on the "bottom" of the window.
 */

public class Assignment3Part4 extends WindowProgram {
    public static int APPLICATION_WIDTH = 1000;
    public static int APPLICATION_HEIGHT = 500;
    final double BRICK_HEIGHT = 4;
    final double BRICK_WIDTH = 8;
    final double BRICKS_IN_BASE = 80;


    @Override
    public void run() {
        buildPyramid();
    }
    //In this method we are building pyramid
    //This is 2d massive which after each iteration decreases bricks
    private void buildPyramid() {
        double BricksInRow = BRICKS_IN_BASE;
        double midPointY = getHeight() - BRICK_HEIGHT;

        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            double xOffset = (BricksInRow * BRICK_WIDTH) / 2d;
            buildRow(BricksInRow, midPointY, xOffset);
            midPointY -= BRICK_HEIGHT;
            --BricksInRow;
        }
    }

    private void buildRow(double BricksInRow, double midPointY, double xOffset) {
        for (int j = 0; j < BricksInRow; j++) {
            double startPointX = getWidth() / 2d - xOffset;
            add(drawBrick(), startPointX, midPointY);
            xOffset -= BRICK_WIDTH;
        }
    }

    // this method draw brick
    public GRect drawBrick() {
        GRect gRect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        gRect.setColor(Color.BLACK);
        gRect.setFilled(true);
        gRect.setFillColor(Color.orange);
        return gRect;
    }
}
