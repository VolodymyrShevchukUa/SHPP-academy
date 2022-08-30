package com.shpp.p2p.cs.vshevchuk.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Write a program use GOval and draw caterpillar,the number and
 * diameter of the circle should be regulated by constants.
 */

public class Assignment2Part6 extends WindowProgram {
    // in this const you can set caterpillar size.
    private static final double CATERPILLAR_SIZE = 100;
    // Window Size
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;


    public void run() {
        // if you input too long caterpillar , you get a warning
        //in this method we can choose count of Link our caterpillar
        buildCaterpillar(8);
    }


    void buildCaterpillar(int countOfLink) {
        double ovalOffset = CATERPILLAR_SIZE / 3d;
        double fullCaterpillarSize = (CATERPILLAR_SIZE * countOfLink - ovalOffset * countOfLink);
        // a check to see if
        if (getWidth() < fullCaterpillarSize) {
            WarningAnswer();
            return;
        }
        // in this place we initializate start position coordinates
        // xPosition can change during the execution of the method !!!
        double xPosition = 0d;
        double yPosition = 0 + (CATERPILLAR_SIZE / 2);
        createCaterpillar(countOfLink, ovalOffset, xPosition, yPosition);
    }

    // In this method we create circles that change their position with each non-pair iteration
    private void createCaterpillar(int countOfLink, double lavraOffset, double xPosition, double yPosition) {
        for (int i = 2; i < (countOfLink + 2); i++) {
            GOval g = new GOval(xPosition, yPosition, CATERPILLAR_SIZE, CATERPILLAR_SIZE);
            g.setColor(Color.RED);
            g.setFilled(true);
            g.setFillColor(Color.YELLOW);
            add(g);
            xPosition += CATERPILLAR_SIZE;
            if (i % 2 == 0) {
                yPosition = 0;
                xPosition -= lavraOffset;
            } else {
                yPosition = 0 + (CATERPILLAR_SIZE / 2);
                xPosition -= lavraOffset;
            }
        }
    }

    private void WarningAnswer() {
        GLabel gLabel = new GLabel("Довжина гусениці перевищує розмір екрану!", 100, getHeight() / 2d);
        gLabel.setColor(Color.BLACK);
        gLabel.setFont("verdana-20");
        add(gLabel);
    }


}
