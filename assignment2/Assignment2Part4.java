package com.shpp.p2p.cs.vshevchuk.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Write a program which will draw a flag, I chose flag of Mali. The size
 * of the flag is regulated by constants, it should be centered in the
 * middle of the window. The inscription "Flag of..." should be at the bottom right.
 */

public class Assignment2Part4 extends WindowProgram {
    int zal = 0;
    private static final double X_RECT = 77;
    private static final double Y_RECT = 163;
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 800;


    public void run() {
        /* initialize our flagList
        If you want more flags, you need create new class which extend clas Flag
         */
        List<Flag> flagList = new ArrayList<>();
        flagList.add(new Belgium());
        flagList.add(new Romania());
        flagList.add(new Latvia());
        // in this cycle we change flag with interval 3 sec
        for (Flag flag : flagList) {
            cleanBackground();
            drawFlag(flag);
            createLabel(flag);
            // this a "live" block
            try {
                // ping 3 sec
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //this method check flagPosition, and draw vertical, or horizontal flag
    private void drawFlag(Flag Country) {

        if (Objects.equals(Country.flagPosition, "VERTICAL")) {
            buildVerticalFlag(Country.colorList);
        } else {
            buildHorizontalFlag(Country.colorList);
        }
    }

    private void createLabel(Flag Country) {
        GLabel label = new GLabel("flag of  " + Country.name);
        label.setFont("verdana-20");
        // Label ever be in lower right position
        add(label, getWidth() - label.getWidth(), getHeight() - label.getDescent());
    }

    private void buildHorizontalFlag(List<Color> colorList) {
        double flagHorizontalSize = X_RECT * 3;
        // is necessary to keep the aspect ratio
        double flagVertSize = X_RECT / ((X_RECT * 3) / Y_RECT);
        double horizontalOffset = flagVertSize / 2d;

        for (int i = 0; i < 3; i++) {
            double xPosition = getWidth() / 2d - flagHorizontalSize / 2;
            double yPosition = getHeight() / 2d + horizontalOffset;
            GRect row = new GRect(xPosition, yPosition, flagHorizontalSize, flagVertSize);
            row.setColor(Color.BLACK);
            row.setFilled(true);
            row.setColor(colorList.get(i));
            add(row);
            System.out.println();
            horizontalOffset -= flagVertSize;
        }
    }

    private void buildVerticalFlag(List<Color> colorList) {
        double xOffset = X_RECT / 2d;

        for (int i = 0; i < 3; i++) {
            double xPosition = getWidth() / 2d + xOffset;
            double yPosition = getHeight() / 2d - Y_RECT / 2d;
            GRect row = new GRect(xPosition, yPosition, X_RECT, Y_RECT);
            row.setColor(Color.BLACK);
            row.setFilled(true);
            row.setColor(colorList.get(i));
            add(row);
            System.out.println();
            xOffset -= X_RECT;
        }
    }

    // method for clean Background
    private void cleanBackground() {
        GRect clear = new GRect(0, 0, getWidth(), getHeight());
        clear.setColor(Color.WHITE);
        clear.setFilled(true);
        add(clear);
    }

    // each flag has name, colorList, and flagPosition. all countries extend class Flag
    public abstract static class Flag {
        String name = setName();
        List<Color> colorList = setColorList();
        String flagPosition = setFlagPosition();

        abstract List<Color> setColorList();

        abstract String setFlagPosition();

        abstract String setName();
    }

    public static class Belgium extends Flag {
        @Override
        List<Color> setColorList() {
            List<Color> colorList = new ArrayList<>();
            colorList.add(Color.YELLOW);
            colorList.add(Color.BLACK);
            colorList.add(Color.RED);
            return colorList;
        }

        @Override
        String setFlagPosition() {
            return "VERTICAL";
        }

        @Override
        String setName() {
            return "Belgium";
        }

    }

    public static class Romania extends Flag {
        @Override
        List<Color> setColorList() {
            List<Color> colorList = new ArrayList<>();
            colorList.add(Color.BLUE);
            colorList.add(Color.YELLOW);
            colorList.add(Color.RED);
            return colorList;
        }

        @Override
        String setFlagPosition() {
            return "VERTICAL";
        }

        @Override
        String setName() {
            return "Romania";
        }

    }

    public static class Latvia extends Flag {
        @Override
        List<Color> setColorList() {
            List<Color> colorList = new ArrayList<>();
            colorList.add(Color.ORANGE);
            colorList.add(Color.GREEN);
            colorList.add(Color.RED);
            return colorList;
        }

        @Override
        String setFlagPosition() {
            return "HORIZONTAL";
        }

        @Override
        String setName() {
            return "Latvia";
        }

    }


}

