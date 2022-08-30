package com.shpp.p2p.cs.vshevchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Assignment5Part3 extends TextProgram {
    // List with all words
    List<String> totalAnswer = new ArrayList<>();
    int counterWords = 0;
    public List<String> test = new ArrayList<>();

    @Override
    public void run() {
            CalifornianGame(readLine("Pleas enter 3 symbols"));
    }

    public void CalifornianGame(String symbols) {
        if(symbols.length() != 3){
            CalifornianGame(readLine("Pleas enter ONLY!!! 3 symbols"));
        }
        symbols = symbols.toLowerCase();
        for (String s : getStrings()) {
            if (s == null) {
                break;
            }
            // these numbers are index on which present our symbols.
            int firstIndex = s.indexOf(symbols.charAt(0));
            int secondIndex = s.indexOf(symbols.charAt(1), firstIndex + 1);
            int thirdIndex = s.indexOf(symbols.charAt(2), secondIndex + 1);

            if (firstIndex != -1 && secondIndex != -1 && thirdIndex != -1) {
                counterWords(s);
            }
        }
        test.addAll(totalAnswer);
        for (String word : totalAnswer) {
            println(word.toUpperCase());
        }

        println("you can have " + counterWords + " words:");
        counterWords = 0;
        totalAnswer.clear();
    }

    /**
     * this method creating List String form txt file
      */
    private LinkedList<String> getStrings() {
        LinkedList<String> stringArrayList = new LinkedList<>();
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader("src/en-dictionary.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((line = Objects.requireNonNull(br).readLine()) != null) {
                stringArrayList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringArrayList;
    }

    //this method counting total words, and add current word in our List
    private void counterWords(String word) {
        ++counterWords;
        totalAnswer.add(word);
    }
}





