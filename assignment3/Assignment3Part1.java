package com.shpp.p2p.cs.vshevchuk.assignment3;


import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;
import java.util.List;

/**In this task we create a program that determines the amount of time spent at the activity
 */

public class Assignment3Part1 extends TextProgram {

    public static final int DESIRED_COUNT_OF_CARDIO_DAYS = 5;
    public static final int DESIRED_COUNT_OF_BLOOD_PRESSURE_DAY = 3;
    public static final int REQUIRED_NUMBER_OF_MINUTES_FOR_CARDIO = 30;
    public static final int REQUIRED_NUMBER_OF_MINUTES_FOR_BLOOD_PRESSURE = 40;

    @Override
    public void run() {
        sendAnswer();
    }


    //In this method we input information about our Week activity
    //And put info in handlers
    public void sendAnswer() {
        int countOfCardioDays = 0;
        int countBloodPressureDays = 0;
        for (Integer i : request()) {
            if (i >= REQUIRED_NUMBER_OF_MINUTES_FOR_CARDIO) {
                countOfCardioDays++;
                if (i >= REQUIRED_NUMBER_OF_MINUTES_FOR_BLOOD_PRESSURE) {
                    countBloodPressureDays++;
                }
            }
        }
        cardioDaysCheker(countOfCardioDays);
        statusPressureBlood(countBloodPressureDays);
    }
    //The method handles check for sufficient number of aerobic for BloodPressureHealth
    private void statusPressureBlood(int countBloodPressureDays) {
        if (countBloodPressureDays >= DESIRED_COUNT_OF_BLOOD_PRESSURE_DAY) {
            println("Blood pressure :");
            println("Great job! You've done enough exercise for cardiovascular health");
        } else {
            println("Blood pressure :");
            println("You needed to train hard for at least" + " " + (DESIRED_COUNT_OF_BLOOD_PRESSURE_DAY - countBloodPressureDays) + " " + "more day(s) a week!");
        }
    }
    //The method handles check for sufficient number of aerobic for CardioHealth
    private void cardioDaysCheker(int countOfCardioDays) {
        if (countOfCardioDays >= DESIRED_COUNT_OF_CARDIO_DAYS) {
            println("Cardiovascular health :");
            println("Great job! You've done enough exercise for cardiovascular health");
        } else {
            println("Cardiovascular health :");
            println("You needed to train hard for at least" + " " + (DESIRED_COUNT_OF_CARDIO_DAYS - countOfCardioDays) + " " + "more day(s) a week!");
        }
    }

    // this method request us how many minutes we are was trained during last week
    public List<Integer> request() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            print("How many minutes did you do on day " + i + "?" + " ");
            integerList.add(readInt());
        }
        return integerList;
    }
}

