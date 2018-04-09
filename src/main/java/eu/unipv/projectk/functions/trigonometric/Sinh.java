package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Mlt;

/**
 * Represents the following function: f(x) = sinh(g(x))
 */
public final class Sinh extends UnaryMathFunction {

    /**
     * f(x) = sinh(g(x))
     * @param arg  g(x)
     */
    public Sinh(MathFunction arg) {
        super(Math::sinh, arg, "sinh");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(new Cosh(arg), arg.derivative());
    }
}
