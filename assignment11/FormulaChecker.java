package com.shpp.p2p.cs.vshevchuk.assignment11;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class created for check formula, and given it for handlers
 */
public class FormulaChecker {
    static HashMap<String, Double> variables = new HashMap<>();
    static File path = new File("src/com/shpp/p2p/cs/vshevchuk/assignment11/formula.txt");


    /**
     * point of enter the program
     */
    public String getFormula(String [] args) throws IOException {
        String checkedFormula ;
        LinkedList<String> listWithFormula = readText();


        try {
            args[0] = replaceHardOperatorToLetter(args[0]);
            formulaChecker(args);
            checkedFormula = replaceLetterToNumbers(args);
            return checkedFormula.replace(" ","");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "Indefinite exception!";
    }

    private static String replaceHardOperatorToLetter(String formula){
        for(HardOperator o:HardOperator.values()){
            formula = formula.replace(o.NAME_REFERENCE,o.SYMBOL);
        }
        return formula;
    }

    private static LinkedList<String> readText() throws IOException {
        LinkedList<String> lines = new LinkedList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = bufferedReader.readLine();
        while (line != null) {
            lines.add(line);
            line = bufferedReader.readLine();
        }
        return lines;
    }

    /**
     *
     * @param formula formula from args, which be written to txt
     */
    private static void writeToTxt(String formula) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(formula + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method replaced variables to numbers
     *
     * @return replaced formula
     */


    private static String replaceLetterToNumbers(String[] args) {
        String form = args[0];
        for (int i = 1; i < args.length; i++) {
            String[] params = args[i].replace(" ", "").split("=");
            form = form.replace(params[0], params[1]);
        }
        return form;
    }

    /**
     * This method checked variables error
     *
     * @param args - arguments from parameters
     */
    private static void variablesError(String[] args) {
        String variablesCheck ;
        for (int i = 1; i < args.length; i++) {
            variablesCheck = args[i].replace(" ", "");
            int indexOfEquals = variablesCheck.indexOf('=');
            if (indexOfEquals == -1) {
                throw new RuntimeException("variables error");
            }
            String values = variablesCheck.substring(indexOfEquals + 1);
            variables.put(String.valueOf(variablesCheck.toCharArray()[0]), Double.parseDouble(values));
        }
        char[] symbolsWithReplacedVariables = replaceLetterToNumbers(args).toCharArray();
        for (char symbol : symbolsWithReplacedVariables) {
            if (symbol >= 'a' && symbol <= 'z') {
                throw new RuntimeException("variables error");
            }
        }
    }

    /**
     * In this method checked formula for syntax`s error
     * If syntax`s error are present, it throws exception
     */
    private static void formulaChecker(String[] args) {
        String formula = args[0].replace(" ", "");
        isNonPairBrackets(formula);
        variablesError(args);
        isNonAdequateOperator(formula);
        writeToTxt(args[0].replace(" ", ""));
    }

    /**
     * This method checked for NonPairBrackets
     * @param formula - formula from class arguments
     */
    private static void isNonPairBrackets(String formula) {
        char[] symbols = formula.toCharArray();
        int changedOperator = 1;
        int bracketsCounter = 0;
        boolean closeBracketsIsAvailable = false;

        for (int i = 0; i < formula.length(); i += changedOperator) {
            if (i < 0) {
                throw new RuntimeException("Bracket`s error");
            }
            if (symbols[i] == ')') {
                symbols[i] = ' ';
                bracketsCounter++;
                changedOperator = -changedOperator;
                closeBracketsIsAvailable = true;
            }
            if (symbols[i] == '(' && closeBracketsIsAvailable) {
                symbols[i] = ' ';
                bracketsCounter--;
                changedOperator = -changedOperator;
                closeBracketsIsAvailable = false;
            }

        }
        if (bracketsCounter != 0) {
            throw new RuntimeException("Bracket`s error");
        }
    }

    /**
     * This method checked for Non-normal adequate operator
     * @param formula - formula from class arguments
     */
    private static void isNonAdequateOperator(String formula) {
        char[] formulaAtChar = formula.toCharArray();
        for (Operator operator : Operator.values()) {
            boolean isEquals = false;
            if (operator == Operator.Minus || operator == Operator.OpenBrackets
                    || operator == Operator.CloseBrackets) {
                continue;
            }
            for (char symbols : formulaAtChar) {
                boolean isCurrentSymbolOperator = Operator.valueOf(symbols) == operator;
                if (isEquals && isCurrentSymbolOperator) {
                    throw new RuntimeException("Operator error");
                }
                isEquals = isCurrentSymbolOperator;
            }
        }
    }
}
