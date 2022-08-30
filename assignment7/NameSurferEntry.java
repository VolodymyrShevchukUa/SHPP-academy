package com.shpp.p2p.cs.vshevchuk.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NameSurferEntry implements NameSurferConstants {
    private final List<Integer> rankArray ;
    private final String name;

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {

        ArrayList<Integer> rankArray = new ArrayList<>();

        String[] splittedStrings = line.split(" ");
        //init "name" from splittedStrings
        name = splittedStrings[0];
        // init rank
        for (int i = 1;i<splittedStrings.length;i++){
            rankArray.add(Integer.parseInt(splittedStrings[i]));
        }
        this.rankArray = Collections.unmodifiableList(rankArray);
    }


    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return rankArray.get(decade);
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return name + " " + rankArray;
    }

}
