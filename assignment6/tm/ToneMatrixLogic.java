package com.shpp.p2p.cs.vshevchuk.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */

    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] soundsSum = new double[ToneMatrixConstants.sampleSize()];
        double mostBigValue = 0;
        for (int row = 0; row < samples.length; row++) {
            if (toneMatrix[row][column]) {
                for (int col = 0; col < soundsSum.length; col++) {
                    soundsSum[col] += samples[row][col];
                }
            }
        }
        // Normalize Songs
        normalize(soundsSum, mostBigValue);
        return soundsSum;
    }

    private static void normalize(double[] soundsSum, double mostBigValue) {
        for (double bb: soundsSum){
            if(mostBigValue < Math.abs(bb)){
                mostBigValue = Math.abs(bb);
            }
        }
        // check for null-normalize
        if(mostBigValue >0) {
            for (int i = 0; i < soundsSum.length; i++) {
                soundsSum[i] = soundsSum[i] / mostBigValue;
            }
        }
    }
}
