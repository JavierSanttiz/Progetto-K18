package eu.unipv.projectk.functions.unaryprimitives.trigonometric;

import eu.unipv.projectk.functions.MathFunction;
import eu.unipv.projectk.functions.binaryprimitives.arithmeticoperators.Mult;
import eu.unipv.projectk.functions.unaryprimitives.UnaryMathFunction;

public final class Sinh extends UnaryMathFunction {

    public Sinh(MathFunction argument) {
        super(Math::sinh, argument, "sinh");
    }

    @Override
    public MathFunction derivative() {
        return new Mult(new Cosh(arg), arg.derivative());
    }
}
