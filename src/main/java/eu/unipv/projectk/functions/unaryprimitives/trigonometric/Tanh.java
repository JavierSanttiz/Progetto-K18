package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.miscellanea.KFPlusH;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

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
        return new Mult(new KFPlusH(-1, new Pow2(new Tanh(arg)), 1), arg.derivative());
    }
}
