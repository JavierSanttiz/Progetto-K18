package eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(f(x), g(x)) = f(x) - g(x)
 */
public final class Subtr extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (x, y) -> x - y;

    public Subtr(MathFunction argument1, MathFunction argument2) {
        super(f, argument1, argument2, "-");
    }

    @Override
    public MathFunction derivative() {
        return new Subtr(arg1.derivative(), arg2.derivative());
    }
}
