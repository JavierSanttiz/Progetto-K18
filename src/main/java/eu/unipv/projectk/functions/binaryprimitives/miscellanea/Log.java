package eu.unipv.projectk.functions.binaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.BinaryMathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Ln;

import java.util.function.BiFunction;

/**
 * Represents the following function: z(base, f(x)) = log(base, f(x))
 */
public final class Log extends BinaryMathFunction {
    private static final BiFunction<Double, Double, Double> f = (base, n) -> Math.log(n) / Math.log(base);

    public Log(MathFunction argument1, MathFunction argument2) {
        super(f, argument1, argument2, "log");
    }

    @Override
    public MathFunction derivative() {
        return new Div(arg2.derivative(), new Mult(new Ln(arg1), arg2));
    }
}
