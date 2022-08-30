package com.shpp.p2p.cs.vshevchuk.assignment10;


import java.util.Objects;
import java.util.Stack;
import java.util.function.Function;

public class Assignment10Part1 {
    private final Stack<String> numberStack = new Stack<>();
    private final Stack<Operator> operatorStack = new Stack<>();
    String result ;



    // start handler
    void run(String formula) {
        formulaHandler(formula.replace(",", "."));
        while (!this.operatorStack.empty()) {
            calculate();
        }
        result = numberStack.pop();
        if(Objects.equals(result, "Infinity")){
            result = "Div null";
        }
        System.out.println(result);
    }

    /**
     * This method is calculating our formula
     * It takes two number, and make some operation with them depending on the symbol
     */
    private void calculate() {
        double secondNumber = Double.parseDouble(numberStack.pop());
        double firstNumber = Double.parseDouble(numberStack.pop());
        Pair currentPair = new Pair(firstNumber, secondNumber);
        numberStack.add(operatorStack.pop().function.apply(currentPair).toString());
    }


    /**
     * This method read our formula, and filling Stacks with symbols
     */
    private void formulaHandler(String formula) {

        Operator currentOperator;
        char[] characters = formula.toCharArray();


        StringBuilder returnedNumber = new StringBuilder();
        // variable for unary minus
        boolean previousIsOperator = false;


        if (characters[0] == Operator.Minus.SYMBOL) {
            numberStack.add("0");
        }

        // cycle for all formula
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

                //Handler for closed brackets
                if (currentOperator.equals(Operator.CloseBrackets)) {
                    closeBracketsHandler();
                    previousIsOperator = false;
                    continue;
                }
                //Operator handler
                if (!operatorStack.isEmpty() && !operatorStack.peek().equals(Operator.OpenBrackets) && currentOperator.PRIORITY <= operatorStack.peek().PRIORITY) {
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals(Operator.OpenBrackets))
                        calculate();
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
            numberStack.add(returnedNumber.toString());
        }
    }

    /**
     * This method get StringBuilder, added it to number stack, and clear String builder
     */
    private void addAndClearStringBuilder(StringBuilder returnedNumber) {
        if (returnedNumber.isEmpty()) {
            return;
        }
        numberStack.add(returnedNumber.toString());
        returnedNumber.delete(0, returnedNumber.length());
    }

    /**
     * Handler for close Brackets,
     * It is removed Last OpenBrackets in stack
     */
    private void closeBracketsHandler() {
        int lastIndexOf = operatorStack.lastIndexOf(Operator.OpenBrackets);
        operatorStack.remove(lastIndexOf);
        while (lastIndexOf != operatorStack.size()) {
            calculate();
        }

    }

    /**
     * Enum with all Operator
     */
    enum Operator {
        Plus(1, '+', (b) -> b.firstNumber + b.secondNumber),
        Minus(1, '-', (b) -> b.firstNumber - b.secondNumber),
        Division(2, '/', (b) -> b.firstNumber / b.secondNumber),
        Multiply(2, '*', (b) -> b.firstNumber * b.secondNumber),
        RaiseToPower(3, '^', (b) -> Math.pow(b.firstNumber, b.secondNumber)),
        OpenBrackets(4, '('),
        CloseBrackets(4, ')');


        private final int PRIORITY;
        private final char SYMBOL;
        private Function<Pair, Double> function;

        //Check if symbol is Operator
        public static Operator valueOf(char symbol) {
            for (Operator o : Operator.values()) {
                if (o.SYMBOL == symbol) {
                    return o;
                }
            }
            return null;
        }

        // Constructor for Operator
        Operator(int PRIORITY, char SYMBOL, Function<Pair, Double> function) {
            this.function = function;
            this.PRIORITY = PRIORITY;
            this.SYMBOL = SYMBOL;
        }

        // Constructor for Brackets
        Operator(int PRIORITY, char SYMBOL) {
            this.PRIORITY = PRIORITY;
            this.SYMBOL = SYMBOL;
        }
    }

    /**
     * Inner class with two parameters
     */
    static class Pair {
        Double firstNumber;
        Double secondNumber;

        Pair(double firstNumber, double secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }
    }
}
