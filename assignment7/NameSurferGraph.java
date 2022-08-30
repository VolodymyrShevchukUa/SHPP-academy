package com.shpp.p2p.cs.vshevchuk.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.util.List;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    // This is color array, that we use in our task
    private final static Color[] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
    // The  entry list which is displayed
    private final List<NameSurferEntry> entries = new ArrayList<>();
    // variables for init needed color
    private final HashMap<String, Integer> colorByName = new HashMap<>();
    private int colorIndex;
    // variables for correct displayed our graph
    private int bottomLinePosition = 0;
    private double cof = 0;


    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     * This method just clear our`s list`s and update
     */
    public void clear() {
        colorByName.clear();
        entries.clear();
        colorIndex = 0;
        update();
        // You fill this in //
    }



    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (colorByName.containsKey(entry.getName())) {
            return;
        }
        if (colorIndex == 4) {
            colorIndex = 0;
        }
        colorByName.put(entry.getName(), colorIndex++);
        entries.add(entry);
        update();
        // You fill this in //
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        chartGrid();
        drawGraph();

        // You fill this in //
    }

    /**
     * This method add oll lines on display
     */
    private void drawGraph() {
        //variables for correct displayed
        String labelName;
        int entryRank;
        int secondRank;
        int rank;
        double firstLineYPosition;
        double secondLineYPosition;
        double XOffset = getWidth() / (double) NDECADES;
        // foreach for our`s entry
        for (NameSurferEntry entry : entries) {
            // init current color by name
            String entryName = entry.getName();
            Color currentColor = colors[colorByName.get(entryName)];
            // start X position, which will increase
            double firstXPosition = 0;
            // cycle for all Lines
            for (int i = 0; i < NDECADES - 1; i++) {

                entryRank = entry.getRank(i);
                // check our rank for null
                if (entryRank == 0) {
                    labelName = entryName + "**";
                    firstLineYPosition = bottomLinePosition;
                } else {
                    firstLineYPosition = Math.abs(GRAPH_MARGIN_SIZE + entryRank * cof);
                    labelName = entryName + " " + entryRank;
                }
                secondRank = entry.getRank(i + 1);
                //check second point for null
                secondLineYPosition = secondRank == 0 ? bottomLinePosition : Math.abs(GRAPH_MARGIN_SIZE + secondRank * cof);
                // init Label
                drawLabel(labelName,firstXPosition,firstLineYPosition,currentColor);
                // init Line
                drawLine(firstXPosition,firstLineYPosition,firstXPosition+= XOffset,secondLineYPosition,currentColor);

            }
            // init last label,and check it for null
            rank = entry.getRank(NDECADES - 1);
            if (rank == 0) {
                labelName = entryName + "**";
                firstLineYPosition = bottomLinePosition;
            } else {
                firstLineYPosition = Math.abs(GRAPH_MARGIN_SIZE + rank * cof);
                labelName = entryName + " " + rank;
            }
            drawLabel(labelName,firstXPosition,firstLineYPosition,currentColor);

        }
    }


    /**
     * This method add at display our grid
     */
    private void chartGrid() {
        // variables for drawing grid
        int bottomLinePosition = getHeight() - GRAPH_MARGIN_SIZE;
        int topLinePosition = getHeight() - bottomLinePosition;
        double XOffset = getWidth() / (double) NDECADES;
        double X_Position = 0;
        int decadeInt = START_DECADE;
        // cycle for draw needed count vertical lines
        for (int i = 0; i < NDECADES; i++) {
            drawLine(X_Position,getHeight(),X_Position,0,Color.BLACK);
            GLabel decade = new GLabel("" + decadeInt);
            decadeInt += 10;
            add(decade, X_Position, getHeight());
            X_Position += XOffset;
        }
        // Add two horizontal lines
        drawLine(X_Position,topLinePosition,0,topLinePosition,Color.black);
        drawLine(X_Position, getHeight() - GRAPH_MARGIN_SIZE, 0, getHeight() - GRAPH_MARGIN_SIZE,Color.black);
    }

    private void drawLabel(String labelName,double firstXPosition, double firstLineYPosition, Color color){
        GLabel gLabel = new GLabel(labelName,firstXPosition,firstLineYPosition);
        gLabel.setColor(color);
        add(gLabel);

    }
    private void drawLine(double X_Position,double Height,double secondXPosition,double secondLineYPosition,Color color){
        GLine gLine = new GLine(X_Position,Height,secondXPosition,secondLineYPosition);
        gLine.setColor(color);
        add(gLine);
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        // update our variables each time, when we resize window
        int height = getHeight();
        bottomLinePosition = height - GRAPH_MARGIN_SIZE;
        int topLinePosition = height - bottomLinePosition;
        double drawField = height - GRAPH_MARGIN_SIZE - topLinePosition;
        cof = drawField / MAX_RANK;
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
