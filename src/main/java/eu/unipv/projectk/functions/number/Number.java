package eu.unipv.projectk.functions.number;

import eu.unipv.projectk.functions.MathFunction;

/**
 * Represents the following function: f(x) = k
 */
public enum Number implements MathFunction {
    ZERO(0.0),
    ONE(1.0);

    private double value;

    Number(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public MathFunction derivative() {
        return Number.ZERO;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}