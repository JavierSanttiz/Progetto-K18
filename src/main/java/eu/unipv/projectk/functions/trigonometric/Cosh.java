package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Mlt;

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
        return new Mlt(new Sinh(arg), arg.derivative());
    }
}
