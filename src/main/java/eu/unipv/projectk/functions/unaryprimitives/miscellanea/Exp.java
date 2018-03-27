package eu.unipv.projectk.functions.unaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

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
        return new Mult(new Exp(arg), arg.derivative());
    }
}
