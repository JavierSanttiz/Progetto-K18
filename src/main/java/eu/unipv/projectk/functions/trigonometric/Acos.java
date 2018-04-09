package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Div;
import eu.unipv.projectk.functions.arithmetic.Mlt;
import eu.unipv.projectk.functions.arithmetic.Sub;
import eu.unipv.projectk.functions.miscellanea.Pow2;
import eu.unipv.projectk.functions.miscellanea.Sqr2;

/**
 * Represents the following function: f(x) = arcos(g(x))
 */
public final class Acos extends UnaryMathFunction {

    /**
     * f(x) = arcos(g(x))
     * @param arg  g(x)
     */
    public Acos(MathFunction arg) {
        super(Math::acos, arg, "acos");
    }

    @Override
    public MathFunction derivative() {
        return new Mlt(new Div(-1, new Sqr2(new Sub(1, new Pow2(arg)))), arg.derivative());
    }
}
