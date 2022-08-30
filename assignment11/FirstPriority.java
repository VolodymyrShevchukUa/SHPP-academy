package com.shpp.p2p.cs.vshevchuk.assignment11;

/**
 * This clas was created from handlers more difficult formula
 */
public class FirstPriority {
    SimpleOperationHandler simpleOperationHandler = new SimpleOperationHandler();

    /**
     * This is getter from handled formula
     */
    public String getFormula(String formula) {
        String bracketsHandled = bracketsHandler(formula);
        return hardOperatorHandler(bracketsHandled);
    }

    /**
     * this method handlers Cos,Sin,Tan,Atan, etc...
     *
     * @return determinate formula
     */
    public String hardOperatorHandler(String formula) {

        char[] formulaAtChar = formula.toCharArray();
        String number;
        double result;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = formulaAtChar.length - 1; i >= 0; i--) {
            HardOperator operator = HardOperator.valueOf(formulaAtChar[i]);
            if (operator != null) {
                number = stringBuilder.reverse().toString();
                stringBuilder.delete(0, stringBuilder.length());
                result = operator.FUNCTION.apply(Double.parseDouble(number));
                formula = formula.replace(operator.SYMBOL + number, "" + result);
            } else {
                Operator operator1 = Operator.valueOf(formulaAtChar[i]);
                if (operator1 == null || operator1 == Operator.Minus) {
                    stringBuilder.append(formulaAtChar[i]);
                }
            }
        }
        return formula;
    }

    private String bracketsHandler(String formula) {

        String returnedFormula = "";
        if (formula.contains("(")) {
            while (formula.contains("(")) {
                int firstClose = formula.indexOf(")");
                int open = formula.substring(0, firstClose).lastIndexOf("(");
                String middleFormula = hardOperatorHandler(formula.substring(open + 1, firstClose)) ;

                returnedFormula = formula.replace(formula.substring(open, firstClose + 1), simpleOperationHandler.run(middleFormula));
                formula = returnedFormula;
            }
        } else {
            return formula;
        }

        return returnedFormula;

    }
}



