package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Mlt;

/**
 * Represents the following function: f(x) = cos(g(x))
 */
public final class Cos extends UnaryMathFunction {

    /**
     * f(x) = cos(g(x))
     * @param arg  g(x)
     */
    public Cos(MathFunction arg) {
        super(Math::cos, arg, "cos");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(-1, new Sin(arg), arg.derivative());
    }
}
