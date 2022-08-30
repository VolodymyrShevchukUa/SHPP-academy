package com.shpp.p2p.cs.vshevchuk.assignment11;

import java.io.*;
import java.util.LinkedList;
import java.util.Stack;

public class PolishInvertNotation {
    private Stack<Operator> operatorStack = new Stack<>();
    private StringBuilder output = new StringBuilder();

    /**
     * This method refactor formula into polish invert notation
     *
     * @return polish invert notation
     */
    private String getPolishInvertNotation(String formula) {
        Operator currentOperator;
        char[] characters = formula.toCharArray();
        StringBuilder returnedNumber = new StringBuilder();
        // variable for unary minus
        boolean previousIsOperator = false;
        if (characters[0] == Operator.Minus.SYMBOL) {
            output.append("-");
            characters = formula.replaceFirst("-", "").toCharArray();
        }
        for (char symbol : characters) {
            currentOperator = Operator.valueOf(symbol);
            // If current operator is null, then symbols append to string builder
            if (currentOperator != null) {
                //Handler for first time operator add to operator stack
                if (currentOperator == Operator.Minus && previousIsOperator) {
                    returnedNumber.append(symbol);
                    previousIsOperator = false;
                    continue;
                }
                addAndClearStringBuilder(returnedNumber);

                //Operator handler
                while (!operatorStack.isEmpty() && currentOperator.PRIORITY <= operatorStack.peek().PRIORITY) {
                    output.append(operatorStack.pop().SYMBOL).append(" ");
                }
                operatorStack.add(currentOperator);
                previousIsOperator = true;
            } else {
                previousIsOperator = false;
                returnedNumber.append(symbol);
            }
        }
        // final add to stack
        if (!returnedNumber.isEmpty()) {
            output.append(returnedNumber);
            while (!operatorStack.isEmpty()) {
                output.append(" ").append(operatorStack.pop().SYMBOL);
            }
        }
        writeToTxt(output.toString());

        return output.toString();
    }

    /**
     * This method handled polish inverted notation
     *
     * @return calculating result
     */
    public String getResult(String formula) {
        String solution = getPolishInvertNotation(formula);
        Stack<String> stringStack = new Stack<>();
        String[] symbols = solution.split(" ");
        if (!solution.contains(" ")) {
            return solution;
        }

        for (String s : symbols) {
            if (s.length() > 1) {
                stringStack.add(s);
                continue;
            }
            Operator currentOperator = Operator.valueOf(s.charAt(0));
            if (currentOperator != null) {
                double secondNumber = Double.parseDouble(stringStack.pop());
                double firstNumber = Double.parseDouble(stringStack.pop());
                Double partOfSolution = currentOperator.function.apply(new Operator.Pair
                        (firstNumber, secondNumber));
                stringStack.add(partOfSolution + "");
            } else
                stringStack.add(s);
        }
        return stringStack.pop();
    }

    private void addAndClearStringBuilder(StringBuilder returnedNumber) {
        if (returnedNumber.isEmpty()) {
            return;
        }
        output.append(returnedNumber).append(" ");
        returnedNumber.delete(0, returnedNumber.length());
    }


    private static void writeToTxt(String formula) {
        try {
            FileWriter fileWriter = new FileWriter(FormulaChecker.path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(formula + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


