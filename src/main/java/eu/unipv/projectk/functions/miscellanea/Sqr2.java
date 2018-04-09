package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Div;
import eu.unipv.projectk.functions.arithmetic.Mlt;

import java.util.function.Function;

/**
 * Represents the following function: f(x) = g(x) ^ (1 / 2)
 */
public final class Sqr2 extends UnaryMathFunction {
    private static final Function<Double, Double> f = x -> Math.pow(x, 1 / 2.0);

    /**
     * f(x) = g(x) ^ (1 / 2)
     * @param arg  g(x)
     */
    public Sqr2(MathFunction arg) {
        super(f, arg, "sqr2");
    }

    @Override
    public MathFunction derivative() {
        return new Div(arg.derivative(), new Mlt(2, new Sqr2(arg)));
    }
}
