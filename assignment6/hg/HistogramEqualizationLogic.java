package com.shpp.p2p.cs.vshevchuk.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;
    public static final int SIZE_HISTOGRAM = 256;

    /**
     * this method given the luminances of the pixels in an image, returns a histogram of the frequencies of those luminances.
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        int[] histogram = new int[SIZE_HISTOGRAM];
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[row].length; col++) {
                int i = luminances[row][col];
                ++histogram[i];
            }
        }
        return histogram;
    }

    /**
     *  Given a brightness histogram of an image, returns an array of cumulative frequencies of that image.
     *  Each entry of this array is equal to the sum of all entries
     *  of the array up to and including its index in the input histogram array.
     * <p/>
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int[] cumulativeSumFor = new int[SIZE_HISTOGRAM];
        int summ = 0;
        for (int colls = 0; colls < cumulativeSumFor.length; colls++) {
            summ += histogram[colls];
            cumulativeSumFor[colls] = summ;
        }
        return cumulativeSumFor;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        return luminances.length * luminances[0].length;//Given that our massive is Rect
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        int[] histogram = histogramFor(luminances);
        int[] cumulativePixels = cumulativeSumFor(histogram);
        int totalPixels = totalPixelsIn(luminances);

        double newLuminance;
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[row].length; col++) {
                int currentPixel = luminances[row][col];
                newLuminance = MAX_LUMINANCE * cumulativePixels[currentPixel] / (double) totalPixels;
                luminances[row][col] = (int) newLuminance;
            }
        }
        return luminances;
    }
}
