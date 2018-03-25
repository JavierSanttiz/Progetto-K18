package eu.unipv.projectk.functions.unaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

/**
 * Represents the following function: f(x) = |g(x)|
 */
public final class Abs extends UnaryMathFunction {

    public Abs(MathFunction argument) {
        super(Math::abs, argument, "abs");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Div(arg, new Abs(arg)), arg.derivative());
    }

}
