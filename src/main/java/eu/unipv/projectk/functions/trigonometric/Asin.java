package eu.unipv.projectk.functions.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.UnaryMathFunction;
import eu.unipv.projectk.functions.arithmetic.Div;
import eu.unipv.projectk.functions.arithmetic.Sub;
import eu.unipv.projectk.functions.miscellanea.Pow2;
import eu.unipv.projectk.functions.miscellanea.Sqr2;

/**
 * Represents the following function: f(x) = asin(g(x))
 */
public final class Asin extends UnaryMathFunction {

    /**
     * f(x) = asin(g(x))
     * @param arg  g(x)
     */
    public Asin(MathFunction arg) {
        super(Math::asin, arg, "asin");
    }

    @Override
    public MathFunction derivative() {
        return new Div(arg.derivative(), new Sqr2(new Sub(1, new Pow2(arg))));
    }
}
