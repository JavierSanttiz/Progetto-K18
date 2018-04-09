package eu.unipv.projectk.functions.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Div;
import eu.unipv.projectk.functions.arithmetic.Mlt;

/**
 * Represents the following function: f(x) = |g(x)|
 */
public final class Abs extends UnaryMathFunction {

    /**
     * f(x) = |g(x)|<br>
     * f(x) = abs(g(x))
     * @param arg  g(x)
     */
    public Abs(MathFunction arg) {
        super(Math::abs, arg, "abs");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(new Div(arg, new Abs(arg)), arg.derivative());
    }
}
