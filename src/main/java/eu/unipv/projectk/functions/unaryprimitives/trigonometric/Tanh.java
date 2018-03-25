package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Div;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Subtr;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;
import eu.unipv.projectk.functions.unaryprimitives.miscellanea.Pow2;

public final class Tanh extends UnaryMathFunction {

    public Tanh(MathFunction argument) {
        super(Math::tanh, argument, "tanh");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Div(new Subtr(new Pow2(new Cosh(arg)), new Pow2(new Sinh(arg))), new Pow2(new Cosh(arg))), arg.derivative());
    }
}
