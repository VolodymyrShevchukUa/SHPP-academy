package com.shpp.p2p.cs.vshevchuk.assignment12;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

import java.util.*;

/**
 * The first parameter to be sent to the input of the program is the name of the file. For example: 'test.jpg'.
 * If there are no parameters, the first parameter is assumed to be 'test.jpg'.
 * The program should display on the console the number of silhouettes of large objects in the picture.
 */

public class Assignment12Part1 extends WindowProgram {
    private Pixel[][] pixels;

    public static void main(String[] args) {
        if(args.length == 0) {
            args = new String[]{"test.jpg"};
        }
        new Assignment12Part1().run(args);
    }

    public void run(String[] args) {
        imageToConditionList(args[0]);
        handler();
    }

    /**
     *This method is finding init list with Conditions, from pixelArray
     */
    private void imageToConditionList(String filename) {
        // GImage need for create 3D array with int`s
        GImage gImage = new GImage(filename);
        int[][] pixelArray = gImage.getPixelArray();
        //Pixel Array
        pixels = new Pixel[pixelArray.length][pixelArray[0].length];

        int importantPixel = findImportantPixel(pixelArray);
        // Cycle for init condition list
        for (int row = 0; row < pixelArray.length; row++) {
            for (int calls = 0; calls < pixelArray[0].length; calls++) {
                pixels[row][calls] = new Pixel(calls, row, pixelArray[row][calls] != importantPixel ?
                        Condition.isNotVisited
                        : Condition.isNonContrast);
            }
        }
    }

    /**
     *
     * @param pixelArray int array, from that we are finding important pixel
     * @return int,which is the most important pixel
     */
    private int findImportantPixel(int[][] pixelArray) {
        List<Integer> sortedInts = ArraysToArray(pixelArray).stream().toList();
        //HashMap need for return important pixel for the key. Key is count of the same pixel
        HashMap<Integer,Integer> integerHashMap = new HashMap<>();
        //HashSet need for save only 1 exemplar ints
        HashSet<Integer> hashSet = new HashSet<>(sortedInts);
        // Array with count each pixel
        int[] resultCountEachPixel = new int[hashSet.size()];

        int index = 0;
        // Cycle for determine count of each pixel
        for(int ints:hashSet){
            int comparePixel = comparePixel(ints, sortedInts);
            integerHashMap.put(comparePixel,ints);
            resultCountEachPixel[index++] = comparePixel;
        }
        // Sort array
        resultCountEachPixel = Arrays.stream(resultCountEachPixel).sorted().toArray();
        return integerHashMap.get(resultCountEachPixel[resultCountEachPixel.length-1]);

    }

    /**
     * @param i ints from HashSet, That is exclusive ints
     * @param list with all ints
     * @return count of each pixel
     */
    private int comparePixel (int i,List<Integer> list){
        int count = 0;
        for (int ints:list){
            if(i == ints){
                count++;
            }
        }
        return count;
    }

    /**
     * Method which create Arrays from 3d Arrays
     */
    private ArrayList<Integer> ArraysToArray(int[][] arrays) {
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int[] i:arrays){
            for (int ints:i){
                newArray.add(ints);
                }
            }
        return newArray;
    }


    private void handler() {
        int totalSilhouettesCount = 0;
        for (Pixel[] innerArray : pixels) {
            for (Pixel pixel : innerArray) {
                if (pixel.condition == Condition.isNotVisited) {
                    int dfsr = DFSR(pixel);
                    if(dfsr>1000) {
                        totalSilhouettesCount++;
                    }
                }
            }
        }
        System.out.println("Total count of Silhouettes = : " +  totalSilhouettesCount);
    }

    /**
     * Depth First Search Recursion
     * @param pixelPop - checkedPixel
     * @return total count of Silhouettes
     */
    private int DFSR(Pixel pixelPop) {
        pixelPop.condition = Condition.isVisited;
        return DFSRS(pixelPop.getBottom()) + DFSRS(pixelPop.getRight()) + DFSRS(pixelPop.getLeft()) + DFSRS(pixelPop.getTop());
    }

    /**
     * Depth First Search Recursion Second :)
     * @param optionalPixel Pixel
     */
    private int DFSRS(Optional<Pixel> optionalPixel) {
        int count = 0;
        //The same - if pixel != null
        if (optionalPixel.isPresent()) {
            count = 1;
            Pixel pixel = optionalPixel.get();
            count += DFSR(pixel);
        }
        return count;
    }

    enum Condition {
        isNonContrast,
        isNotVisited,
        isVisited

    }

    /**
     * class with 3 parameters
     */
    class Pixel {
        final int X_COORDINATE;
        final int Y_COORDINATE;
        Condition condition;

        Pixel(int x_coordinate, int y_coordinate, Condition condition) {
            X_COORDINATE = x_coordinate;
            Y_COORDINATE = y_coordinate;
            this.condition = condition;
        }
        //Methods which determine Right,Left,Top,Bottom pixels
        Optional<Pixel> getRight() {
            return getNotVisited(X_COORDINATE + 1, Y_COORDINATE);
        }

        Optional<Pixel> getLeft() {
            return getNotVisited(X_COORDINATE - 1, Y_COORDINATE);
        }

        Optional<Pixel> getTop() {
            return getNotVisited(X_COORDINATE, Y_COORDINATE - 1);
        }

        Optional<Pixel> getBottom() {
            return getNotVisited(X_COORDINATE, Y_COORDINATE + 1);
        }

        /**
         * @param x coordinate
         * @param y coordinate
         * @return not visited pixel
         */
        Optional<Pixel> getNotVisited(int x, int y) {
            Pixel pixel = null;
            if ((x >= 0 && x < pixels[0].length) && (y >= 0 && y < pixels.length)) {
                pixel = pixels[y][x];
                pixel = pixel.condition != Condition.isNotVisited ? null : pixel;
            }
            return Optional.ofNullable(pixel);
        }
    }

}

