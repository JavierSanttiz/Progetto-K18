package eu.unipv.projectk.functions.binaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;

import java.util.function.BiFunction;

public final class SqrN extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (x, n) -> Math.pow(x, 1 / n);

    public SqrN(MathFunction argument1, MathFunction argument2) {
        super(f, argument1, argument2, "sqrN");
    }

    // TODO: to be implemented
    @Override
    public MathFunction derivative() {
        return null;
    }
}
