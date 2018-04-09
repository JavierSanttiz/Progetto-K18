package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Mlt;

import java.util.function.Function;

/**
 * Represents the following function: f(x) = g(x) ^ 2
 */
public final class Pow2 extends UnaryMathFunction {
    private static final Function<Double, Double> f = x -> Math.pow(x, 2);

    /**
     * f(x) = g(x) ^ 2
     * @param arg  g(x)
     */
    public Pow2(MathFunction arg) {
        super(f, arg, "^ 2");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(2, arg, arg.derivative());
    }

    @Override
    public String toString() {
        return "(" + arg + " " + symbol + ")";
    }
}
