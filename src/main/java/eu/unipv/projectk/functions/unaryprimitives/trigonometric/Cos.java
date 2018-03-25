package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

/**
 * Represents the following function: f(x) = cos(g(x))
 */
public final class Cos extends UnaryMathFunction {

    public Cos(MathFunction argument) {
        super(Math::cos, argument, "cos");
    }

    @Override
    public MathFunction derivative() {
        return Mult.chain(NumberFactory.MINUS_ONE, new Sin(arg), arg.derivative());
    }
}
