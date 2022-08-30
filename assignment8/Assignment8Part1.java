package com.shpp.p2p.cs.vshevchuk.assignment8;

import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Assignment8Part1 extends WindowProgram {

    private static final double PAUSE_TIME = 1000.0 / 50;

    // horizontal vector
    private static final double VECTOR_X = 1.0;

    // gravity speed
    private static final double GRAVITY = 0.4;
    //basic size ball
    private static final double BASIC_SIZE = 10;
    // coefficient for calculate bounce
    private static final double ELASTICITY = 0.75;
    // Basic color
    private static final double BASIC_COLOR = 250;

    long time;

    /**
     * handler for mouse Pressed
     * @param me the event to be processed
     */
    public void mousePressed(MouseEvent me) {
        time = System.currentTimeMillis();
    }

    /**
     * handler for mouse Clicked
     * @param me the event to be processed
     */
    public void mouseClicked(MouseEvent me) {
        // Choose our ball
        GObject comp = getElementAt(me.getX(), me.getY());
        // Change gravity
        if (comp != null) {
            if (comp instanceof Ball ball) {
                ball.isDown = !ball.isDown;
                ball.yVector = 0;
            }
        // creating ball, and set his size, and color
        } else {
            long timeOut = (System.currentTimeMillis() - time);
            if (timeOut < 1000) {
                timeOut = 1000;
            }
            //set color
            int v = (int) (BASIC_COLOR * (1000d / timeOut));
            float[] rgBtoHSB = Color.RGBtoHSB(v, v, v, null);
            //create ball
            Ball ball = createBall(me.getX(), me.getY(), BASIC_SIZE * timeOut / 1000,
                    Color.getHSBColor(rgBtoHSB[0], rgBtoHSB[1], rgBtoHSB[2]));
            add(ball);
            //start handlers
            new Thread(() -> bounceBall(ball)).start();
        }
    }


    @Override
    public void run() {
        addMouseListeners();
    }

    /**
     * This method creating ball
     * @param x x coordinate
     * @param y y coordinate
     * @param radius ball
     * @param color ball
     * @return ball
     */
    private Ball createBall(int x, int y, double radius, Color color) {
        Ball ball = new Ball(x - radius, y - radius, radius * 2, radius * 2);
        ball.setFilled(true);
        ball.setColor(color);
        return ball;
    }

    /**
     *
     * @param ball it is ball which we are handlers
     */
    private void bounceBall(Ball ball) {
        double yVector;
        while (true) {

            yVector = ball.yVector;
            //handler for standard gravity
            if (ball.isDown) {
                yVector += GRAVITY;
                if (ballBelowFloor(ball) && yVector > 0) {
                    yVector *= -ELASTICITY;
                }
                // handler for inversion gravity
            } else {
                yVector -= GRAVITY;
                if (ballUpperTop(ball) && yVector < 0) {
                    yVector *= -ELASTICITY;
                }
            }
            ball.yVector = yVector;
            ball.move(VECTOR_X, yVector);
            pause(PAUSE_TIME);
        }
    }
    // check for ball is bellow floor
    private boolean ballBelowFloor(GObject ball) {
        return ball.getY() + ball.getHeight() >= getHeight();
    }
    // check for ball is upper Top
    private boolean ballUpperTop(GObject ball) {
        return ball.getY() <= 0;
    }
    // This is just GOval ,but with two parameters
    static class Ball extends GOval {
        // switch
        boolean isDown = true;
        // Y vector
        private  double yVector = 0;

        public Ball(double v, double v1, double v2, double v3) {

            super(v, v1, v2, v3);
        }
    }
}




