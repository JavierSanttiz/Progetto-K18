package eu.unipv.projectk.functions.identity;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.number.Number;

/**
 * Represents the following function: f(x) = x
 */
public enum Identity implements MathFunction {
    X("x");

    private String symbol;

    Identity(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public MathFunction derivative() {
        return Number.ONE;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
