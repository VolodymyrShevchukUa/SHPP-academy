package com.shpp.p2p.cs.vshevchuk.assignment10;

import java.util.HashMap;

/**
 * This class created for check formula, and given it to Assignment10Part1(Formula handler)
 */
public class FormulaReader {
    static Assignment10Part1 assignment10Part1 = new Assignment10Part1();
    static HashMap<String, Double> variables = new HashMap<>();

    /**
     * point of enter the program
     */
    public static void main(String[] args) {
        String checkedFormula = "";
        try {
            formulaChecker(args);
            checkedFormula = replaceLetterToNumbers(args);
            assignment10Part1.run(checkedFormula.replace(" ", ""));
        } catch (Exception e) {
            System.out.println("Syntax`s error");
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
        String variablesCheck = "";
        for (int i = 1; i < args.length; i++) {
            variablesCheck = args[i].replace(" ", "");
            int indexOfEquals = variablesCheck.indexOf('=');
            if (indexOfEquals == -1) {
                throw new RuntimeException("Syntax`s error");
//                System.out.println("Variables Syntax's error");
//                return true;
            }
            String values = variablesCheck.substring(indexOfEquals + 1);
            variables.put(String.valueOf(variablesCheck.toCharArray()[0]), Double.parseDouble(values));
        }
        char[] symbolsWithReplacedVariables = replaceLetterToNumbers(args).toCharArray();
        for (char symbol : symbolsWithReplacedVariables) {
            if (symbol >= 'a' && symbol <= 'z') {
                throw new RuntimeException("Syntax`s error");
                //      return true;
            }
        }
        //       return false;
    }


    private static void formulaChecker(String[] args) {
        String formula = args[0].replace(" ", "");
        isNonPairBrackets(formula);
        variablesError(args);
        isNonAdequateOperator(formula);
    }

    /**
     * This method checked for NonPairBrackets
     *
     * @param formula - formula from class arguments
     */
    private static void isNonPairBrackets(String formula) {
        char[] symbols = formula.toCharArray();
        int changedOperator = 1;
        int bracketsCounter = 0;
        boolean closeBracketsIsAvailable = false;

        for (int i = 0; i < formula.length(); i += changedOperator) {
            if (i < 0) {
                throw new RuntimeException("Syntax`s error");
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
            throw new RuntimeException("Syntax`s error");
        }
    }

    /**
     * This method checked for Non-normal adequate operator
     *
     * @param formula - formula from class arguments
     */
    private static void isNonAdequateOperator(String formula) {
        char[] formulaAtChar = formula.toCharArray();
        for (Assignment10Part1.Operator operator : Assignment10Part1.Operator.values()) {
            boolean isEquals = false;
            if (operator == Assignment10Part1.Operator.Minus || operator == Assignment10Part1.Operator.OpenBrackets
                    || operator == Assignment10Part1.Operator.CloseBrackets) {
                continue;
            }
            for (char symbols : formulaAtChar) {
                boolean isCurrentSymbolOperator = Assignment10Part1.Operator.valueOf(symbols) == operator;
                if (isEquals && isCurrentSymbolOperator) {
                    throw new RuntimeException("Syntax`s error");
                }
                isEquals = isCurrentSymbolOperator;
            }
        }
    }
}
