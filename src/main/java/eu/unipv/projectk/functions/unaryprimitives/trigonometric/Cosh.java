package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

/**
 * Represents the following function: f(x) = cosh(g(x))
 */
public final class Cosh extends UnaryMathFunction {

    /**
     * f(x) = cosh(g(x))
     * @param arg  g(x)
     */
    public Cosh(MathFunction arg) {
        super(Math::cosh, arg, "cosh");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Sinh(arg), arg.derivative());
    }
}
