package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Subtr;
import eu.unipv.projectk.functions.number.NumberFactory;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Sqr2;

/**
 * Represents the following function: f(x) = asin(g(x))
 */
public final class Asin extends UnaryMathFunction {

    public Asin(MathFunction argument) {
        super(Math::asin, argument, "asin");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Div(NumberFactory.ONE, new Sqr2(new Subtr(NumberFactory.ONE, new Pow2(arg)))), arg.derivative());
    }
}
