package eu.unipv.projectk.functions.binaryprimitives;

import eu.unipv.projectk.functions.MathFunction;

import java.util.function.BiFunction;

public abstract class BinaryMathFunction implements MathFunction {
    private final String symbol;

    private final BiFunction<Double, Double, Double> f;
    protected final MathFunction arg1, arg2;

    public BinaryMathFunction(BiFunction<Double, Double, Double> f, MathFunction argument1, MathFunction argument2, String symbol) {
        this.f = f;
        this.arg1 = argument1;
        this.arg2 = argument2;
        this.symbol = symbol;
    }

    @Override
    public double evaluate(double x) {
        return f.apply(arg1.evaluate(x), arg2.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + arg1.toString() + " " + symbol + " " + arg2.toString() + ")";
    }

}
