package eu.unipv.projectk.functions.unaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

import java.util.function.Function;

public final class Sqr2 extends UnaryMathFunction {
    private static final Function<Double, Double> f = x -> Math.pow(x, 1 / 2.0);

    public Sqr2(MathFunction argument) {
        super(f, argument, "sqr2");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Div(NumberFactory.ONE, new Mult(NumberFactory.valueOf(2), new Sqr2(arg))), arg.derivative());
    }
}
