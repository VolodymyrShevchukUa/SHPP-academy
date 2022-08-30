package com.shpp.p2p.cs.vshevchuk.assignment7;

/*
  we write a program NameSurfer, which reflects the popularity of children's names in the U.S.
  over the last century
 */

import com.shpp.cs.a.simple.SimpleProgram;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private static final NameSurferDataBase nameSurferDataBase = new NameSurferDataBase(NAMES_DATA_FILE);
    private final NameSurferGraph graph = new NameSurferGraph();
    private JTextField jTextField;


    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        // we are init all JLabel, JButton and action listeners
        add(graph);
        jTextField = new JTextField(20);
        jTextField.setActionCommand("EnterPressed");
        jTextField.addActionListener(this);
        // we are added all JLabel, JButton and action listeners
        this.add(new JLabel("Name:"), "North");
        this.add(jTextField, "North");
        this.add(new JButton("Graph"), "North");
        this.add(new JButton("Clear"), "North");
        this.addActionListeners();

    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        // Clear Button handler
        if (Objects.equals(actionCommand, "Clear")) {
            graph.clear();

        } else if (Objects.equals(actionCommand, "Graph") || actionCommand.equals("EnterPressed")) {
            // GraphButton Handler
            NameSurferEntry entry = nameSurferDataBase.findEntry(jTextField.getText().toLowerCase());
            // check for Null
            if (entry == null) {
                return;
            }
            graph.addEntry(entry);
        }
    }
}
