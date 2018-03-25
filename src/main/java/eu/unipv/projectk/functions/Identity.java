package eu.unipv.projectk.functions;

import eu.unipv.projectk.functions.number.NumberFactory;

public final class Identity implements MathFunction {

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
        return "x";
    };
}
