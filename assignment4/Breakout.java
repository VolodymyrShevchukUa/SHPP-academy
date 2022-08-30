package com.shpp.p2p.cs.vshevchuk.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 20;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 16;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    private int generalCountOfBricks = NBRICK_ROWS * NBRICKS_PER_ROW;

    public GRect rocket;
    public GOval ball;
    /**
     * Speed
     */
    private double vectorX = 0;
    private double vectorY = 3;

    private double countOfLose = 0;
    private boolean isFirstLaunch = true;
    private boolean isStarted = false;


    @Override

    public void run() {
        createBrickWall();
        rocket = createGRect(getWidth() / 2d, getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
        ball = createBall();
        add(rocket);
        add(ball);
        addMouseListeners();

    }

    /**
     * method which handlers mouse CLick, and changed isStarted
     */
    @Override
    public void mouseClicked(MouseEvent me) {

        if (!isStarted) {
            vectorX = RandomGenerator.getInstance().nextDouble(1.0, 3.0);
            if (!isFirstLaunch) {
                vectorY = -3;
            }
            isFirstLaunch = false;
            new Thread(this::ballGravityHandler).start();
            isStarted = true;
        }
    }

    /**
     * This method handlers for mouse move.
     * In this method we are moved our Paddle
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        double newX = e.getX() - rocket.getWidth() / 2.0;
        double newY = getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET;
        if (!isStarted && !isFirstLaunch) {
            setBallStartPosition();
        }
        rocket.setLocation(newX, newY);

        if (isRocketBellowRightWall()) {
            rocket.setLocation(getWidth() - PADDLE_WIDTH, getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
        } else if (isRocketBellowLeftWall()) {
            rocket.setLocation(0.1, getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
        }
    }


    public GRect createGRect(double x, double y, double xSize, double YSize, Color color) {
        GRect gRect = new GRect(x, y, xSize, YSize);
        gRect.setColor(color);
        gRect.setFilled(true);
        return gRect;
    }

    /**
     * Most important method
     * This method handlers touch ball to somebody objects, and ball gravity
     */
    public void ballGravityHandler() {
        while (isStarted) {
            pause(5);
            ball.move(vectorX, vectorY);
            // These are four different points of the ball
            GObject leftBottomPoint = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
            GObject rightBottomPoint = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
            GObject leftUpperPoint = getElementAt(ball.getX(), ball.getY());
            GObject rightUpperPoint = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());


            if (loseGameHandler()) {
                return;
            } else {
                // handler for rocket, or bricks touch
                touchHandlers(leftBottomPoint, rightBottomPoint, leftUpperPoint, rightUpperPoint);
                touchWallHandlers();
            }
            //Win handler
            if (winGame()) {
                createLabel("WINNER", Color.BLUE);
                return;
            }

        }

    }

    private boolean loseGameHandler() {
        if (isBallUnderBottom()) {
            if (++countOfLose == NTURNS) {
                createLabel("YOU DIED", Color.RED);
                return true;
            }
            isStarted = false;
            vectorX = 0;
            vectorY = 0;
            setBallStartPosition();
            // handlers for touch edge window
        }
        return false;
    }

    /**
     * This method are handler for touch wall , or Top
     */
    private void touchWallHandlers() {
        if (isTopTouched()) {
            vectorY = -vectorY;
        } else if (isBallTouchedLeftWall() || isBallTouchedRightWall()) {
            vectorX = -vectorX;
        }
    }

    /**
     * This method is handlers for ball touch to Bricks or Paddle
     *
     * @param leftBottomPoint  check leftBottomPoint
     * @param rightBottomPoint check rightBottomPoint
     * @param leftUpperPoint   check leftUpperPoint
     * @param rightUpperPoint  check RightUpperPoint
     */
    private void touchHandlers(GObject leftBottomPoint, GObject rightBottomPoint, GObject leftUpperPoint, GObject rightUpperPoint) {
        if (leftBottomPoint == rocket || rightBottomPoint == rocket) {
            if (vectorY > 0) {
                vectorY = -vectorY;
            }
            //handler for bricks touch
        } else {
            Stream.of(leftBottomPoint, rightBottomPoint, leftUpperPoint, rightUpperPoint)
                    .filter(a -> a != null && a != rocket).findFirst().ifPresent(this::checkBricks);
        }
    }

    // this method are set ball start position
    private void setBallStartPosition() {
        ball.setLocation(rocket.getX() + PADDLE_WIDTH / 3.5, rocket.getY() - PADDLE_HEIGHT * 2);
    }

    // create final label
    private void createLabel(String label, Color color) {
        GLabel gLabel = new GLabel(label, getWidth() / 2d, getHeight() / 2d);
        gLabel.setFont("verdana-" + (int) (100 * getWidth() / 1200d));
        gLabel.setColor(color);
        add(gLabel, HEIGHT / 2d - gLabel.getWidth(), getHeight() / 2d);
    }

    //Counter bricks count
    private void checkBricks(GObject comp) {
        remove(comp);
        vectorY = -vectorY;
        generalCountOfBricks--;

    }

    private GRect createGRect(double x, double y) {
        GRect gRect = new GRect(x, y, Breakout.PADDLE_WIDTH, Breakout.PADDLE_HEIGHT);
        gRect.setFilled(true);
        return gRect;
    }

    private GOval createBall() {

        GOval gOval = new GOval(getWidth() / 2d - BALL_RADIUS, getHeight() / 2d - BALL_RADIUS, BALL_RADIUS * 2d, BALL_RADIUS * 2d);
        gOval.setColor(Color.blue);
        gOval.setFilled(true);
        return gOval;
    }

    /**
     * Bricks wall have different color per each 2 rows
     *
     * @return Bricks wall
     */
    private List<List<GRect>> gRectList() {

        double y = BRICK_Y_OFFSET;
        int colorCounter = 0;  // counter for color Bricks Row
        List<Color> colorList = colorList();
        List<List<GRect>> gRect2dList = new ArrayList<>();
        List<GRect> gRectList = new ArrayList<>();
        for (int i = 0; i < NBRICK_ROWS; i++) {

            double x = 0;
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                // Handlers for ArrayList indexOfBounce
                try {
                    gRectList.add(createGRect(x, y, BRICK_WIDTH, BRICK_HEIGHT, colorList.get(colorCounter / 2)));
                } catch (Exception e) {
                    colorCounter = 0;
                    gRectList.add(createGRect(x, y, BRICK_WIDTH, BRICK_HEIGHT, colorList.get(0)));
                }

                x += BRICK_WIDTH + BRICK_SEP;
            }
            colorCounter++;
            gRect2dList.add(gRectList);
            y += BRICK_SEP + BRICK_HEIGHT;

        }
        return gRect2dList;
    }

    /**
     * this method creating BricksWall
     */
    private void createBrickWall() {
        for (List<GRect> lb : gRectList()) {
            for (GRect g : lb) {
                add(g);
            }
        }
    }

    /**
     * @return collor list for our brick
     */
    private List<Color> colorList() {
        List<Color> colorList = new ArrayList<>();
        colorList.add(Color.RED);
        colorList.add(Color.ORANGE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GREEN);
        colorList.add(Color.CYAN);
        return colorList;
    }


    /**
     * All this method are returned boolean.
     */
    private boolean winGame() {
        return generalCountOfBricks == 0;
    }

    private boolean isBallTouchedLeftWall() {
        return ball.getX() < 0;
    }

    private boolean isBallTouchedRightWall() {
        return ball.getX() + BALL_RADIUS
                > getWidth();
    }

    private boolean isTopTouched() {
        return ball.getY() < 0;
    }

    private boolean isRocketBellowRightWall() {
        double rightWallCoordinate = getWidth() - PADDLE_WIDTH;
        return rocket.getLocation().getX() > rightWallCoordinate;
    }

    private boolean isRocketBellowLeftWall() {
        return rocket.getLocation().getX() < 0.1;
    }

    private boolean isBallUnderBottom() {
        return ball.getY() > getHeight();
    }
}
