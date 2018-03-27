package eu.unipv.projectk.functions.unaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

/**
 * Represents the following function: f(x) = ln(g(x)) (base e)
 */
public final class Ln extends UnaryMathFunction {

    /**
     * f(x) = ln(g(x))
     * @param arg  g(x)
     */
    public Ln(MathFunction arg) {
        super(Math::log, arg, "ln");
    }

    @Override
    public MathFunction derivative() {
        return new Div(arg.derivative(), arg);
    }
}
