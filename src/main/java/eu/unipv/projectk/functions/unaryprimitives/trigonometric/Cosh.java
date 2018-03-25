package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

/**
 * Represents the following function: f(x) = cosh(g(x))
 */
public final class Cosh extends UnaryMathFunction {

    public Cosh(MathFunction argument) {
        super(Math::cosh, argument, "cosh");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Sinh(arg), arg.derivative());
    }
}
