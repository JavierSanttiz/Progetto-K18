package eu.unipv.projectk.functions;

import eu.unipv.projectk.functions.number.NumberFactory;

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
        return NumberFactory.ONE;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
