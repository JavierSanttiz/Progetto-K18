package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.miscellanea.KFPlusH;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

/**
 * Represents the following function: f(x) = tan(g(x))
 */
public final class Tan extends UnaryMathFunction {

    public Tan(MathFunction argument) {
        super(Math::tan, argument, "tan");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new KFPlusH(1, new Pow2(new Tan(arg)), 1), arg.derivative());
    }
}
