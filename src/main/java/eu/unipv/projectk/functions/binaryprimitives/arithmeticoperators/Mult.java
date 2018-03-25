package eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;

import java.util.function.BiFunction;

public final class Mult extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (x, y) -> x * y;

    public Mult(MathFunction argument1, MathFunction argument2) {
        super(f, argument1, argument2, "*");
    }

    @Override
    public MathFunction derivative() {
        return new Add(new Mult(arg1.derivative(), arg2), new Mult(arg1, arg2.derivative()));
    }
}
