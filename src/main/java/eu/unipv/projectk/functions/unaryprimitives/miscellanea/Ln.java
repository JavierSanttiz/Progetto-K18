package eu.unipv.projectk.functions.unaryprimitives.miscellanea;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

/**
 * Represents the following function: f(x) = ln(g(x)) (base e)
 */
public final class Ln extends UnaryMathFunction {

    public Ln(MathFunction argument) {
        super(Math::log, argument, "ln");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Div(NumberFactory.ONE, arg), arg.derivative());
    }
}
