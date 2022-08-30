package com.shpp.p2p.cs.vshevchuk.assignment12;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class MyVariant extends WindowProgram {
    private static String FILE_NAME = "src/com/shpp/p2p/cs/vshevchuk/assignment12/test5.png";
    private final Stack<Pixel> pixelsStack = new Stack<>();
    private Pixel[][] pixels;

    @Override
    public void run() {
        imageToConditionList();
        handler();
    }

    private void imageToConditionList() {
        AtomicInteger totalBlackCount = new AtomicInteger();
        GImage gImage = new GImage(FILE_NAME);
        int[][] pixelArray = gImage.getPixelArray();
        pixels = new Pixel[pixelArray.length][pixelArray[0].length];


        for (int row = 0; row < pixelArray.length; row++) {
            for (int calls = 0; calls < pixelArray[0].length; calls++) {
                pixels[row][calls] = new Pixel(calls, row, pixelArray[row][calls] != -1 ?
                        Condition.isNotVisited
                        : Condition.isNonContrast);
            }
        }
        for (Pixel[] pixels1:pixels){
            for (Pixel pixel1:pixels1){
                initNeighbor(pixel1);
            }
        }
        // це для Іс нот вісітед всіх.
        for (Pixel[] pixels1:pixels) {
            Arrays.stream(pixels1).filter((s) -> s.condition == Condition.isNotVisited).forEach((s) -> totalBlackCount.getAndIncrement());
        }
        System.out.println(totalBlackCount);
    }


    private void handler() {
        for (Pixel[] innerArray : pixels) {
            for (Pixel pixel : innerArray) {
                if (pixel.condition == Condition.isNotVisited) {
                    pixelsStack.add(pixel);
                    int dfs = DFS();
                    if(dfs >500)
                        System.out.println(dfs);
                }
            }
        }
    }

    private int DFS() {
        int counter = 0;
        while (!pixelsStack.isEmpty()) {
            Pixel pixelPop = pixelsStack.pop();
            pixelPop.condition = Condition.isVisited;
            addPath(pixelPop);
            counter++;
        }
        return counter;
    }



    private void addPath (Pixel pixel){
        if(pixel.leftPixel != null && pixel.leftPixel.condition == Condition.isNotVisited){
            pixelsStack.add(pixel.leftPixel);
        }
        if(pixel.rightPixel != null && pixel.rightPixel.condition == Condition.isNotVisited){
            pixelsStack.add(pixel.rightPixel);
        }
        if(pixel.bottomPixel != null && pixel.bottomPixel.condition == Condition.isNotVisited){
            pixelsStack.add(pixel.bottomPixel);
        }
        if(pixel.upperPixel != null && pixel.upperPixel.condition == Condition.isNotVisited){
            pixelsStack.add(pixel.upperPixel);
        }
    }

    private void initNeighbor(Pixel pixel){
        int leftCoordinate = pixel.X_COORDINATE - 1;
        int rightCoordinate = pixel.X_COORDINATE + 1;
        int bottomCoordinate = pixel.Y_COORDINATE + 1;
        int upperCoordinate = pixel.Y_COORDINATE - 1;

        if (leftCoordinate < pixels[0].length && leftCoordinate >= 0) {
            pixel.leftPixel = pixels[pixel.Y_COORDINATE][leftCoordinate];
        }
        if (rightCoordinate < pixels[0].length && rightCoordinate >= 0) {
            pixel.rightPixel = pixels[pixel.Y_COORDINATE][rightCoordinate];
        }
        if (bottomCoordinate < pixels.length && bottomCoordinate >= 0) {
            pixel.bottomPixel = pixels[bottomCoordinate][pixel.X_COORDINATE];
        }
        if (upperCoordinate < pixels.length && upperCoordinate >= 0) {
            pixel.upperPixel = pixels[upperCoordinate][pixel.X_COORDINATE];
        }
    }

    class Pixel {
        final int X_COORDINATE;
        final int Y_COORDINATE;
        Pixel upperPixel;
        Pixel bottomPixel;
        Pixel leftPixel;
        Pixel rightPixel;
        Condition condition;

        Pixel(int x_coordinate, int y_coordinate, Condition condition) {
            X_COORDINATE = x_coordinate;
            Y_COORDINATE = y_coordinate;
            this.condition = condition;

        }
    }
    enum Condition {
        isNonContrast,
        isNotVisited,
        isVisited;
    }
}

