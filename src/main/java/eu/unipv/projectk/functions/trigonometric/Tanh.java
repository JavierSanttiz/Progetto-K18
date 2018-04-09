package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Mlt;
import eu.unipv.projectk.functions.arithmetic.Sub;
import eu.unipv.projectk.functions.miscellanea.Pow2;

/**
 * Represents the following function: f(x) = tanh(g(x))
 */
public final class Tanh extends UnaryMathFunction {

    /**
     * f(x) = tanh(g(x))
     * @param arg  g(x)
     */
    public Tanh(MathFunction arg) {
        super(Math::tanh, arg, "tanh");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(new Sub(1, new Pow2(new Tanh(arg))), arg.derivative());
    }
}
