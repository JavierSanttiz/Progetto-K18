package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Mlt;

/**
 * Represents the following function: f(x) = sin(g(x))
 */
public final class Sin extends UnaryMathFunction {

    /**
     * f(x) = sin(g(x))
     * @param arg  g(x)
     */
    public Sin(MathFunction arg) {
        super(Math::sin, arg, "sin");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(new Cos(arg), arg.derivative());
    }

}
