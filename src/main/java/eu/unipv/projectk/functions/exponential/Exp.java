package eu.unipv.projectk.functions.exponential;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Mlt;

import java.util.function.Function;

/**
 * Represents the following function: f(x) = e ^ g(x)
 */
public final class Exp extends UnaryMathFunction {
    private static final Function<Double, Double> f = x -> Math.pow(Math.E, x);

    /**
     * f(x) = e ^ g(x)
     * @param arg  g(x)
     */
    public Exp(MathFunction arg) {
        super(f, arg,  "e ^");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(new Exp(arg), arg.derivative());
    }
}
