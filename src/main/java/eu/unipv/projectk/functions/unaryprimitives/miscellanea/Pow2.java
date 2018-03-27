package eu.unipv.projectk.functions.unaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.miscellanea.KFPlusH;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

import java.util.function.Function;

/**
 * Represents the following function: f(x) = g(x) ^ 2
 */
public final class Pow2 extends UnaryMathFunction {
    private static final Function<Double, Double> f = x -> Math.pow(x, 2);

    public Pow2(MathFunction argument) {
        super(f, argument, "^ 2");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new KFPlusH(2, arg), arg.derivative());
    }

    @Override
    public String toString() {
        return "(" + arg + " " + symbol + ")";
    }
}
