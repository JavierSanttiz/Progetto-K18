package eu.unipv.projectk.functions.unaryprimitives;

import eu.unipv.projectk.functions.MathFunction;

import java.util.function.Function;

/**
 * Represents a function that takes one parameter and
 * performs some calculations
 */
public abstract class UnaryMathFunction implements MathFunction {
    protected final String symbol;

    private final Function<Double, Double> f;
    protected final MathFunction arg;

    public UnaryMathFunction(Function<Double, Double> f, MathFunction argument, String symbol) {
        this.f = f;
        this.arg = argument;
        this.symbol = symbol;
    }

    @Override
    public double evaluate(double x) {
        return f.apply(arg.evaluate(x));
    }

    @Override
    public String toString() {
        return symbol + "(" + arg.toString() + ")";
    }

}
