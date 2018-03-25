package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Subtr;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

/**
 * Represents the following function: f(x) = tanh(g(x))
 */
public final class Tanh extends UnaryMathFunction {

    public Tanh(MathFunction argument) {
        super(Math::tanh, argument, "tanh");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Subtr(NumberFactory.ONE, new Pow2(new Tanh(arg))), arg.derivative());
    }
}
