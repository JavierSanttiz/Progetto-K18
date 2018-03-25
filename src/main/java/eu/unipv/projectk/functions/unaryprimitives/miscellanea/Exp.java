package eu.unipv.projectk.functions.unaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

import java.util.function.Function;

public final class Exp extends UnaryMathFunction {
    private static final Function<Double, Double> f = x -> Math.pow(Math.E, x);

    public Exp(MathFunction argument) {
        super(f, argument,  "e ^");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Exp(arg), arg.derivative());
    }
}
