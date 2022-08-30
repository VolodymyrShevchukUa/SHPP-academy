package com.shpp.p2p.cs.vshevchuk.assignment11;

import java.util.function.Function;


public enum HardOperator {
    Sin("sin", "С", Math::sin),
    Cos("cos", "К", Math::cos),
    Tan("tan", "Т", Math::tan),
    Atan("atan", "А", Math::atan),
    Log10("log10", "Л", Math::log10),
    Log2("log2", "Б", x -> Math.log(x)/Math.log(2)),
    Sqrt("sqrt", "В", Math::sqrt);


    public final String NAME_REFERENCE;
    public final String SYMBOL;
    public final Function<Double, Double> FUNCTION;

    /**
     * @param symbol that we gave to method for check
     * @return Operator, or null if this symbol is number
     */
    public static HardOperator valueOf(char symbol) {
        for (HardOperator o : HardOperator.values()) {
            if (o.SYMBOL.charAt(0) == symbol) {
                return o;
            }
        }
        return null;
    }

    HardOperator(String NAME_REFERENCE, String SYMBOL, Function<Double, Double> FUNCTION) {
        this.NAME_REFERENCE = NAME_REFERENCE;
        this.FUNCTION = FUNCTION;
        this.SYMBOL = SYMBOL;
    }
}
