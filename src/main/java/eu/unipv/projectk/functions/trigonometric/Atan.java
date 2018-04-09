package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Add;
import eu.unipv.projectk.functions.arithmetic.Div;
import eu.unipv.projectk.functions.miscellanea.Pow2;

/**
 * Represents the following function: f(x) = atan(g(x))
 */
public final class Atan extends UnaryMathFunction {

    /**
     * f(x) = atan(g(x))
     * @param arg  g(x)
     */
    public Atan(MathFunction arg) {
        super(Math::atan, arg, "atan");
    }

    @Override
    public MathFunction derivative() {
        return new Div(arg.derivative(), new Add(1, new Pow2(arg)));
    }
}
