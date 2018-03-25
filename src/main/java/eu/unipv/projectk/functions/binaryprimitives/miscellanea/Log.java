package eu.unipv.projectk.functions.binaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;

import java.util.function.BiFunction;

public final class Log extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (base, n) -> Math.log(n) / Math.log(base);

    public Log(MathFunction argument1, MathFunction argument2) {
        super(f, argument1, argument2, "log");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Div(NumberFactory.ONE, new Mult(arg2, new Log(NumberFactory.E, arg1))), arg2.derivative());
    }
}
