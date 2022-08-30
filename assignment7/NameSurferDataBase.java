package com.shpp.p2p.cs.vshevchuk.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class NameSurferDataBase implements NameSurferConstants {
    private final Map<String,NameSurferEntry> entryByName = new HashMap<>();


    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
            for (String entryLine : bufferReader(filename)) {
                NameSurferEntry entry = new NameSurferEntry(entryLine);
                entryByName.put(entry.getName().toLowerCase(),entry);
            }
        } catch (IOException e) {
            System.out.println("You are inputted invalid filename");
            new NameSurfer().exit();
        }

    }


    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return entryByName.get(name.toLowerCase());
    }

    /**
     * This method is a parser
     * @param filename - name our`s file
     * @return List with all our values
     *
     */
    private LinkedList<String> bufferReader(String filename) throws IOException {
        LinkedList<String> stringArrayList = new LinkedList<>();
        BufferedReader bufferedReader;
        String line;
        bufferedReader = new BufferedReader(new FileReader(filename));
        while ((line = Objects.requireNonNull(bufferedReader).readLine()) != null) {
            stringArrayList.add(line);
        }
        return stringArrayList;
    }
}

