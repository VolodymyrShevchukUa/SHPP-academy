package com.shpp.p2p.cs.vshevchuk.assignment6.sg;

import acm.graphics.GImage;

public class SteganographyLogic {
    /**
     * Get a GImage containing a hidden message, finds the hidden message contained within it and returns a boolean array containing that message.
     * A message has been hidden in the input image as follows.
     * For each pixel in the image, if that pixel has a red component that is an even number, the message value at that pixel is false.
     * If the red component is an odd number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {

        int[][] pixels = source.getPixelArray();
        boolean[][] booleans = new boolean[pixels.length][pixels[0].length];
        // cycle for our pictures in pixels
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                int red = GImage.getRed(pixels[row][col]);
                if (red % 2 != 0) {
                    booleans[row][col] = true;
                }
            }
        }
        return booleans;
    }

    /**
     * This method Hides the given message inside the specified image.
     * The image was to you as a GImage of some size, and the message will be specified as a boolean array of pixels,
     * where each white pixel is denoted false and each black pixel is denoted true.
     * <p/>
     * The message was hidden in the image by adjusting the red channel of all  the pixels in the original image.
     * For each pixel in the original image, I made the red channel an even number if the message color is white at that position, and odd otherwise.
     * <p/>
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {

        int[][] pixels = source.getPixelArray();
        // cycle for our pictures in pixels
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                // this is all our colors
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);
                int red = GImage.getRed(pixels[row][col]);
                // check for pair, or non-pair red Int
                if (!message[row][col] && red % 2 != 0)
                    red--;
                else if (message[row][col] && red % 2 == 0)
                    red++;
                    pixels[row][col] = GImage.createRGBPixel(red, green, blue);
                }


            }



        return new GImage(pixels);
    }
}
