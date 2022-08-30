package com.shpp.p2p.cs.vshevchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * we must write a method which opens CSV file named filename, and returns all values that are in columnIndex column (0 for the first column,
 * 1 for the second, and so on) as an array.
 * If the file doesn't exist, the method returns null.
 */

public class Assignment5Part4 extends TextProgram {

    private static final int COLUMN_INDEX = 2;
    private static final String FILENAME = "src/com/shpp/p2p/cs/vshevchuk/assignment5/hardCSV.csv";
    private static final char QUOTES = '"';
    private static final char COMA = ',';


    @Override
    public void run() {
        try {
            System.out.println(extractColumn(FILENAME,COLUMN_INDEX));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename    - name of CSV file
     * @param columnIndex - current list index
     * @return required list
     */
    private List<String> extractColumn(String filename, int columnIndex) throws IOException {
        try {
            return getStrings(filename).stream().map(line -> fieldsIn(line).get(columnIndex)).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("File not find");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     *
     * @param line It`s String, that we must refactor
     * @return  List with current count elements
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> currentList = new ArrayList<>();
        boolean isQuotesOpen = false;
        boolean isIsolationQuotes = false;
        StringBuilder result = new StringBuilder();
        for (char character : line.toCharArray()) {
            if (character == QUOTES) {  // current character compares with "
                if (isQuotesOpen) {
                    if (isIsolationQuotes) { // check for double quotes
                        result.append(QUOTES);
                        isIsolationQuotes = false;
                    } else {
                        isIsolationQuotes = true;
                    }
                } else {
                    isQuotesOpen = true;
                }
                continue;
            } else if (isIsolationQuotes) {
                isQuotesOpen = false;
                isIsolationQuotes = false;
            }

            if (character == COMA) { // Check for coma
                if (isQuotesOpen) { //check for isolation quotes
                    result.append(COMA);
                } else {
                    currentList.add(result.toString());
                    result = new StringBuilder();
                }
            } else {
                result.append(character);
            }
        }
        currentList.add(result.toString());// finally add last element
        return currentList;
    }


    /**
     * This method read file and return List
     * @param filename - name our File
     * @return List String with all our Strnigs
     */
    private LinkedList<String> getStrings(String filename) throws IOException {
        LinkedList<String> stringArrayList = new LinkedList<>();
        BufferedReader br;
        String line;
        br = new BufferedReader(new FileReader(filename));
        while ((line = Objects.requireNonNull(br).readLine()) != null) {
            stringArrayList.add(line);
        }
        return stringArrayList;
    }
}


