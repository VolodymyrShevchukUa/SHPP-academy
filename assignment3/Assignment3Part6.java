package com.shpp.p2p.cs.vshevchuk.assignment3;

import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * in this task we need to write a program that displays for 5 seconds of any kind of information on the screen
 * For no more and no less than 5 seconds.
 * The animation must contain 50 frames at least.
 */
public class Assignment3Part6 extends WindowProgram {

    public static int APPLICATION_WIDTH = 2000;
    public static int APPLICATION_HEIGHT = 2000;
    final static double ANGLES = 45.0;
    final static int NUM_POINTS = 1000;
    final double BALLS_SIZE = 150;
    final double RADIUS = BALLS_SIZE * 2d;


    @Override
    public void run() {
        drawAtom();
    }

    //The main method,which draw Atom and electrons that moving around
    void drawAtom() {
        double ballXPosition = getWidth() / 2d - BALLS_SIZE / 2;
        double ballYPosition = getHeight() / 2d - BALLS_SIZE / 2d;
        add(drawOval(ballXPosition, ballYPosition, BALLS_SIZE, BALLS_SIZE, Color.RED));
        int i = 1;

        double midPointForX = getWidth() / 2d;
        double midPointForY = getHeight() / 2d;

        // Mathematical formulas for calculating the elipse, and the inclination of the elipse
        double angle = Math.toRadians(((double) i / Assignment3Part6.NUM_POINTS) * 360);
        double sinus = Math.sin(angle) * RADIUS;
        double cosinus = Math.cos(angle) * RADIUS;

        // start X and Y coordinate for our electrons
        double X = midPointForX + sinus / 2.5;
        double X2 = midPointForX - cosinus;
        double Y = midPointForY + cosinus;

        // formula keplera
        final double xOffsetAngle = Math.abs(X - Math.cos(ANGLES) - Y - Math.sin(ANGLES) - X2);
        final double yOffsetAngle = Y - (X - Math.sin(ANGLES) + Y - Math.cos(ANGLES));

        //creating electrons
        Electrons electrons = new Electrons(xOffsetAngle, yOffsetAngle);
        Electrons electrons2 = new Electrons(xOffsetAngle, yOffsetAngle);
        add(electrons.electron1);
        add(electrons.electron2);
        add(electrons.electron3);
        add(electrons2.electron1);
        add(electrons2.electron2);
        add(electrons2.electron3);
        //schedule thread to close window in 5 second
        Executors.newSingleThreadScheduledExecutor().schedule(this::exit, 20, TimeUnit.SECONDS);
        while (true) {
            electrons.repaintElectron(i++);
            electrons2.repaintElectron(i + NUM_POINTS / 2);
            pause(1);
        }

    }

    GOval drawOval(double x, double y, double xSize, double ySize, Color color) {
        GOval gOval = new GOval(x, y, xSize, ySize);
        gOval.setColor(color);
        gOval.setFilled(true);
        return gOval;

    }

    GOval drawPath(double x, double y) {
        GOval gOval = new GOval(x, y, 1, 1);
        gOval.setColor(Color.BLACK);
        return gOval;
    }
    public class Electrons {
        final double XOffsetAngle;
        final double YOffsetAngle;
        GObject electron1 = drawOval(0, 0, 10, 10, Color.orange);
        GObject electron2 = drawOval(0, 0, 10, 10, Color.orange);
        GObject electron3 = drawOval(0, 0, 10, 10, Color.orange);

        public Electrons(double xOffsetAngle, double yOffsetAngle) {
            XOffsetAngle = xOffsetAngle;
            YOffsetAngle = yOffsetAngle;
        }
        //Calculation position and trajectory for our electrons, and draw it
        private void repaintElectron(int i) {
            double midPointForX = getWidth() / 2d;
            double midPointForY = getHeight() / 2d;

            // Mathematical formulas for calculating the elipse, and the inclination of the elipse
            double angle = Math.toRadians(((double) i / Assignment3Part6.NUM_POINTS) * 360);
            double sinus = Math.sin(angle) * RADIUS;
            double cosinus = Math.cos(angle) * RADIUS;

            // start X and Y coordinate for our electrons
            double X = midPointForX + sinus / 2.5;
            double X2 = midPointForX - cosinus;
            double Y = midPointForY + cosinus;
            double Y2 = midPointForY - sinus / 2.5;

            //X and Y coordinate for our electrons
            double cosAng = Math.cos(ANGLES);
            double sinAng = Math.sin(ANGLES);
            double electron1X = midPointForX + sinus * 1.5 / 2.5;
            double electron1Y = midPointForY + cosinus * 1.5;
            double electron2X = XOffsetAngle + X - cosAng - Y - sinAng;
            double electron2Y = YOffsetAngle + X - sinAng + Y - cosAng;
            double electron3X = XOffsetAngle + X2 - cosAng - Y2 - sinAng;
            double electron3Y = YOffsetAngle + X2 - sinAng + Y2 - cosAng;


            //drawing tracers
            add(drawPath(electron1X, electron1Y));
            add(drawPath(electron2X, electron2Y));
            add(drawPath(electron3X, electron3Y));

            electron1.setLocation(electron1X, electron1Y);
            electron2.setLocation(electron2X, electron2Y);
            electron3.setLocation(electron3X, electron3Y);

        }

    }

}
