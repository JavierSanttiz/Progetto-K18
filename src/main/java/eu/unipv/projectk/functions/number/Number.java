package eu.unipv.projectk.functions.number;

import eu.unipv.projectk.functions.MathFunction;

public class Number implements MathFunction {
    private final double n;

    Number(double n) {
        this.n = n;
    }

    public double getValue() {
        return n;
    }

    @Override
    public double evaluate(double x) {
        return n;
    }

    @Override
    public MathFunction derivative() {
        return new Number(0);
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }
}
