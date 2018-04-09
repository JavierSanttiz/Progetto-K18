package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Add;
import eu.unipv.projectk.functions.arithmetic.Mlt;
import eu.unipv.projectk.functions.miscellanea.Pow2;

/**
 * Represents the following function: f(x) = tan(g(x))
 */
public final class Tan extends UnaryMathFunction {

    /**
     * f(x) = tan(g(x))
     * @param arg  g(x)
     */
    public Tan(MathFunction arg) {
        super(Math::tan, arg, "tan");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(new Add(1, new Pow2(new Tan(arg))), arg.derivative());
    }
}
