package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.miscellanea.KFPlusH;
import eu.unipv.projectk.functions.miscellanea.KOnFPlusH;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

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
        return new Mult(new KOnFPlusH(1, new KFPlusH(1, new Pow2(arg), 1)), arg.derivative());
    }
}
